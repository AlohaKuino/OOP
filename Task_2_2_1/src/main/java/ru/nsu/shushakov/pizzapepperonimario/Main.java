package ru.nsu.shushakov.pizzapepperonimario;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * main class.
 */
public class Main {

    protected static Thread[] bakers;
    protected static Thread[] couriers;
    protected static Thread timerThread;
    protected static volatile boolean pizzeriaClose = false;

    /**
     * getter.
     *
     * @return flag function to know if pizzeria closed.
     */
    public static boolean isPizzeriaClose() {
        return pizzeriaClose;
    }

    /**
     * main function it reads json anp put it in a class also it starts all thread and timer thread.
     *
     * @param args default params.
     */
    public static void main(String[] args) {
        try {
            String filePath = "input.json";
            Gson gson = new Gson();
            FileReader reader = new FileReader(filePath);
            PizzeriaData pizzeriaData = gson.fromJson(reader, PizzeriaData.class);

            Operator operator = new Operator(pizzeriaData.getOrders());
            Thread operatorThread = new Thread(operator);
            operatorThread.start();

            System.out.println("Time before closing: " + pizzeriaData.getTime());
            System.out.println("Number of bakers: " + pizzeriaData.getBakers().size());
            System.out.println("Number of couriers: " + pizzeriaData.getCouriers().size());
            System.out.println("Maximum pizzas in warehouse: " + pizzeriaData.getWarehouse()
                    .getCapacity());
            System.out.println("Orders:" + pizzeriaData.getOrders().size());
            reader.close();

            pizzeriaData.setAllOrders(pizzeriaData.getOrders().size());
            pizzeriaData.getWarehouse().setPizzaList();

            bakers = new Thread[pizzeriaData.getBakers().size()];
            couriers = new Thread[pizzeriaData.getCouriers().size()];

            for (int i = 0; i < pizzeriaData.getBakers().size(); i++) {
                bakers[i] = new BakerThread(pizzeriaData.getBakers().get(i),
                        operator.getOrders(), pizzeriaData);
            }
            for (int i = 0; i < pizzeriaData.getCouriers().size(); i++) {
                couriers[i] = new CourierThread(pizzeriaData.getCouriers().get(i),
                        pizzeriaData, operator);
            }

            startThreads(bakers);
            startThreads(couriers);

            timerThread = startTimer(pizzeriaData.getTime());

            timerThread.join();

            joinThreads(bakers);
            joinThreads(couriers);

            operatorThread.join();

            System.out.println("Pizzeria closed");

        } catch (Exception e) {
            System.out.println("oops");
        }
    }

    /**
     * setter that closing pizzeria.
     */
    public static void closePizzeria() {
        pizzeriaClose = true;
        System.out.println("Pizzeria should be closed");
    }

    /**
     * starts all threads in array.
     *
     * @param threads array of threads to start.
     */
    private static void startThreads(Thread[] threads) {
        for (Thread thread : threads) {
            thread.start();
        }
    }

    /**
     * join all the array's threads.
     *
     * @param threads array of threads to join.
     */
    private static void joinThreads(Thread[] threads) {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println("Failed to join thread");
            }
        }
    }

    /**
     * starts a thread that will be sleeping for a working time.
     *
     * @param timeLimit time that pizzeria should work.
     * @return thread that will be responsible for closing pizzeria in time.
     */
    protected static Thread startTimer(long timeLimit) {
        Thread timerThread = new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(timeLimit);
                closePizzeria();
            } catch (InterruptedException e) {
                System.out.println("They are really good at baking pizza");
            }
        });
        timerThread.start();
        return timerThread;
    }

    /**
     * saves unfinished orders back to order list in inputJSON.
     *
     * @param pizzeriaData object where we get all pizzeria data from.
     */
    protected static void saveRemainingOrders(PizzeriaData pizzeriaData, Operator operator) {
        pizzeriaData.setOrders(operator.getOrderQueueForOperator());
        try (FileWriter writer = new FileWriter("input.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(pizzeriaData, writer);
        } catch (IOException e) {
            System.out.println("Failed to save unfinished orders: " + e.getMessage());
        }
    }
}
