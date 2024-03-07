package ru.nsu.shushakov.pizzapepperonimario;

import ru.nsu.shushakov.pizzapepperonimario.PizzeriaData.Courier;
import ru.nsu.shushakov.pizzapepperonimario.PizzeriaData.Warehouse;
import ru.nsu.shushakov.pizzapepperonimario.PizzeriaData.Order;

import java.util.ArrayList;
import java.util.List;

public class CourierThread extends Thread {

    private final PizzeriaData pizzeriaData;
    private final Courier courier;
    private final Warehouse warehouse;
    private List<Order> pizzasTaken;

    public CourierThread(Courier courier, Warehouse warehouse, PizzeriaData pizzeriaData) {
        this.courier = courier;
        this.warehouse = warehouse;
        this.pizzeriaData = pizzeriaData;
        this.pizzasTaken = new ArrayList<>();
    }

//    @Override
//    public void run() {
//        while (true) {
//            synchronized (warehouse) {
//                while (warehouse.isEmpty()) {
//                    try {
//                        warehouse.wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                try {
//                    Order pizza = warehouse.takePizza();
//                    System.out.println("Courier " + courier.getId() + " is delivering pizza " + pizza.getId());
//
//                    sleep(courier.getSpeed());
//
//                    System.out.println("Pizza " + pizza.getId() + " is delivered by courier " + courier.getId());
//
//                    pizzeriaData.incrementCompletedOrders();
//                    if (pizzeriaData.getCompletedOrders() == pizzeriaData.getAllOrders()) {
//                        System.out.println("All orders completed. Exiting program.");
//                        System.exit(0);
//                    }
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                warehouse.notifyAll();
//            }
//        }
//    }
    @Override
    public void run() {
        while (true) {
            synchronized (warehouse) {
                while (warehouse.isEmpty() || courier.getCapacity() <= 0) {
                    try {
                        warehouse.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                int pizzasToTake = Math.min(courier.getCapacity(), warehouse.getCurrentOccupancy());

                if (pizzasToTake == 0) {
                    continue;
                }

                List<Order> pizzasToDeliver = new ArrayList<>();
                for (int i = 0; i < pizzasToTake; i++) {
                    try {
                        pizzasToDeliver.add(warehouse.takePizza());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println("Courier " + courier.getId() + " going to deliver " + pizzasToDeliver.size() + " pizzas");
                for (Order pizza : pizzasToDeliver) {
                    try {
                        System.out.println("Courier " + courier.getId() + " is delivering pizza " + pizza.getId());
                        Thread.sleep(courier.getSpeed());
                        System.out.println("Pizza " + pizza.getId() + " is delivered by courier " + courier.getId());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                pizzeriaData.incrementCompletedOrders(pizzasToTake);

                if (pizzeriaData.getCompletedOrders() == pizzeriaData.getAllOrders()) {
                    System.out.println("All orders completed. Closing Pizzeria.");
                    System.exit(0);
                }

                warehouse.notifyAll();
            }
        }
    }
}

