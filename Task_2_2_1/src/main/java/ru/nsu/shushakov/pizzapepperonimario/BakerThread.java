package ru.nsu.shushakov.pizzapepperonimario;

import ru.nsu.shushakov.pizzapepperonimario.PizzeriaData.Baker;
import ru.nsu.shushakov.pizzapepperonimario.PizzeriaData.Warehouse;
import ru.nsu.shushakov.pizzapepperonimario.PizzeriaData.Order;

import java.util.List;

public class BakerThread extends Thread {

    private final Baker baker;
    private final List<Order> orderQueue;
    private final Warehouse warehouse;

    private final PizzeriaData pizzeriaData;
    private boolean isBaking = false;


    public synchronized boolean isBaking() {
        return isBaking;
    }

    public synchronized void setBaking(boolean baking) {
        isBaking = baking;
    }

    public BakerThread(Baker baker, List<Order> orderQueue, Warehouse warehouse,
                       PizzeriaData pizzeriaData) {
        this.baker = baker;
        this.orderQueue = orderQueue;
        this.warehouse = warehouse;
        this.pizzeriaData = pizzeriaData;
    }

    public void interruptBakers() {
        for (int i = 0; i < pizzeriaData.getBakers().size(); i++) {
            if (Main.bakers[i].isAlive()) {
                Main.bakers[i].interrupt();
            }
        }
        Main.saveRemainingOrders(pizzeriaData);
    }

    @Override
    public void run() {
        while (!Main.pizzeriaOpen) {
            Order order;
            synchronized (orderQueue) {
                if (orderQueue.isEmpty()) {
                    Thread.currentThread().interrupt();
                    return;
                }
                order = orderQueue.remove(0);
            }
            System.out.println("Baker " + baker.getId() + " is preparing pizza for order " + order
                    .getId());
            try {
                setBaking(true);
                sleep(baker.getSpeed());
                setBaking(false);
            } catch (InterruptedException e) {
                System.out.println("it's always a chance to die while baking pizza");
                Thread.currentThread().interrupt();
                return;
            }
            synchronized (warehouse) {
                while (warehouse.isFull()) {
                    try {
                        warehouse.wait();
                    } catch (InterruptedException e) {
                        System.out.println("it's always a chance to die while baking pizza");
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                try {
                    warehouse.addPizza(order);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                pizzeriaData.incrementBakedOrders(1);
                System.out.println("    Pizza for order " + order.getId() + " is ready.");
                warehouse.notifyAll();

            }
            if (pizzeriaData.getBakedOrders() == pizzeriaData.getAllOrders()
                    || Main.isPizzeriaOpen()) {
                interruptBakers();
                break;
            }
        }
    }
}

