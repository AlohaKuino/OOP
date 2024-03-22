package ru.nsu.shushakov.pizzapepperonimario;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;

/**
 * test class.
 */
public class TestPizzeria {
    protected static Thread[] bakers;
    protected static Thread[] couriers;
    protected static Thread timerThread;
    protected static volatile boolean pizzeriaClose = false;

    public static boolean isPizzeriaClose() {
        return pizzeriaClose;
    }

    /**
     * method to know if pizzeria closed.
     */
    public static void closePizzeria() {
        pizzeriaClose = true;
        System.out.println("Pizzeria should be closed");
    }

    private static void startThreads(Thread[] threads) {
        for (Thread thread : threads) {
            thread.start();
        }
    }

    private static void joinThreads(Thread[] threads) {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println("Failed to join thread");
            }
        }
    }

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

    protected static void saveRemainingOrders(PizzeriaData pizzeriaData, Operator operator) {
        pizzeriaData.setOrders(operator.getOrderQueueForOperator());
        try (FileWriter writer = new FileWriter("input.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(pizzeriaData, writer);
        } catch (IOException e) {
            System.out.println("Failed to save unfinished orders: " + e.getMessage());
        }
    }

    @Test
    public void idkWhatAndHowToTest() throws IOException, InterruptedException {
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

        if(pizzeriaData.getOrders().isEmpty()){
            System.out.println("nothing to do");
            System.exit(0);
        }

        startThreads(bakers);
        startThreads(couriers);

        timerThread = startTimer(pizzeriaData.getTime());

        timerThread.join();

        joinThreads(bakers);
        joinThreads(couriers);

        operatorThread.join();

        System.out.println("Pizzeria closed");
    }
}
