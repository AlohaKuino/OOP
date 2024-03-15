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
    private boolean isDelivering = true;


    public synchronized boolean isDelivering() {
        return isDelivering;
    }

    public synchronized void setDelivering(boolean delivering) {
        isDelivering = delivering;
    }

    public CourierThread(Courier courier, Warehouse warehouse, PizzeriaData pizzeriaData) {
        this.courier = courier;
        this.warehouse = warehouse;
        this.pizzeriaData = pizzeriaData;
    }

    public void interruptCouriers() {
        for (int i = 0; i < pizzeriaData.getCouriers().size(); i++) {
            if (Main.couriers[i].isAlive() && !Main.couriers[i].isInterrupted()
                    && !((CourierThread) Main.couriers[i]).isDelivering) {
                Main.couriers[i].interrupt();
            }
        }
    }

    @Override
    public void run() {
        while (!Main.pizzeriaOpen) {
            synchronized (warehouse) {
                while ((warehouse.isEmpty() || courier.getCapacity() <= 0)) {
                    try {
                        warehouse.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }

                int pizzasToTake = Math.min(courier.getCapacity(), warehouse.getCurrentOccupancy());

                if (pizzasToTake == 0) {
                    continue;
                }

                setDelivering(true);

                List<Order> pizzasToDeliver = new ArrayList<>();

                for (int i = 0; i < pizzasToTake; i++) {
                    try {
                        pizzasToDeliver.add(warehouse.takePizza());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                System.out.println("        Courier " + courier.getId() + " going to deliver "
                        + pizzasToDeliver.size() + " pizzas");
                for (Order pizza : pizzasToDeliver) {
                    try {
                        System.out.println("            Courier " + courier.getId()
                                + " is delivering pizza " + pizza.getId());
                        Thread.sleep(courier.getSpeed());
                        System.out.println("                Pizza " + pizza.getId()
                                + " is delivered by courier " + courier.getId());
                        setDelivering(false);
                    } catch (InterruptedException e) {
                        System.out.println("it's always a chance to die while delivering pizza");
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                pizzeriaData.incrementCompletedOrders(pizzasToTake);
                warehouse.notifyAll();
            }
            if (pizzeriaData.getCompletedOrders() == pizzeriaData.getAllOrders()
                    || Main.isPizzeriaOpen()) {
                Main.timerThread.interrupt();
                interruptCouriers();
                break;
            }
        }
    }
}