package ru.nsu.shushakov.pizzapepperonimario;

import java.util.List;

import ru.nsu.shushakov.pizzapepperonimario.PizzeriaData.Baker;
import ru.nsu.shushakov.pizzapepperonimario.PizzeriaData.Order;
import ru.nsu.shushakov.pizzapepperonimario.PizzeriaData.Warehouse;

/**
 * Class that represents bakers behaviour in thread.
 */
public class BakerThread extends Thread {
    private final Baker baker;
    private final List<Order> orderQueue;
    private final Warehouse warehouse;

    private final PizzeriaData pizzeriaData;
    private boolean isBaking = false;


    /**
     * flag to know if I can interrupt baker correctly.
     *
     * @return true if baker is baking.
     */
    public synchronized boolean isBaking() {
        return isBaking;
    }

    /**
     * simple setter to update baker status.
     *
     * @param baking baker status.
     */
    public synchronized void setBaking(boolean baking) {
        isBaking = baking;
    }

    /**
     * class that represents baker.
     *
     * @param baker        baker from json.
     * @param orderQueue   order list.
     * @param pizzeriaData all data from json.
     */
    public BakerThread(Baker baker, List<Order> orderQueue, PizzeriaData pizzeriaData) {
        this.baker = baker;
        this.orderQueue = orderQueue;
        this.warehouse = pizzeriaData.getWarehouse();
        this.pizzeriaData = pizzeriaData;
    }

    /**
     * method that will interrupt all bakers correctly when I need.
     */
    public void interruptBakers() {
        for (int i = 0; i < pizzeriaData.getBakers().size(); i++) {
            if (Main.bakers[i].isAlive() && !Main.bakers[i].isInterrupted()
                    && !((BakerThread) Main.bakers[i]).isBaking()) {
                Main.bakers[i].interrupt();
            }
        }
    }

    /**
     * <p>
     * override method for a baker.
     * if order list is empty than work is over.
     * when pizza is ready baker tries to put it in a warehouse.
     * if all orders were completed or pizzeria should be closed then interrupt all threads
     * </p>
     */
    @Override
    public void run() {
        while (!Main.isPizzeriaClose()) {
            Order order;
            synchronized (orderQueue) {
                if (orderQueue.isEmpty()) {
                    try {
                        orderQueue.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                order = orderQueue.remove(0);
            }

            setBaking(true);
            System.out.println("Baker " + baker.getId() + " is preparing pizza for order " + order
                    .getId());
            try {
                //"baking" pizza
                sleep(baker.getSpeed());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            synchronized (warehouse) {
                //trying to put pizza in a warehouse
                while (warehouse.isFull()) {
                    try {
                        warehouse.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }

                try {
                    warehouse.addPizza(order);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                setBaking(false);

                pizzeriaData.incrementBakedOrders(1);
                System.out.println("    Pizza for order " + order.getId() + " is ready.");
                warehouse.notifyAll();

            }
            try {
                System.out.println("perekur");
                sleep(1000);
            } catch (InterruptedException ignored) {
                System.out.println("I was perekurival");
            }
            //condition for the end of the work
            if (Main.isPizzeriaClose()) {
                interruptBakers();
                return;
            }
        }
    }
}

