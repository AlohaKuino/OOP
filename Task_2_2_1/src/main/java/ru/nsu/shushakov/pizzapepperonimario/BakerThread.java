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
     * constructor.
     *
     * @param baker baker from json with id and speed.
     * @param orderQueue list of orders for bakers to do.
     * @param warehouse storage for bakers to put pizza there when its ready.
     * @param pizzeriaData class with json data.
     */
    public BakerThread(Baker baker, List<Order> orderQueue, Warehouse warehouse,
                       PizzeriaData pizzeriaData) {
        this.baker = baker;
        this.orderQueue = orderQueue;
        this.warehouse = warehouse;
        this.pizzeriaData = pizzeriaData;
    }

    /**
     * method that will interrupt all bakers correctly when I need.
     */
    public void interruptBakers() {
        for (int i = 0; i < pizzeriaData.getBakers().size(); i++) {
            if (Main.bakers[i].isAlive() && !((BakerThread)Main.bakers[i]).isBaking()) {
                Main.bakers[i].interrupt();
            }
        }
        Main.saveRemainingOrders(pizzeriaData);
    }

    /**
     * <p>
     *     override method for a baker.
     *     if order list is empty than work is over.
     *     when pizza is ready baker tries to put it in a warehouse.
     *     if all orders were completed or pizzeria should be closed then interrupt all threads
     * </p>
     */
    @Override
    public void run() {
        while (!Main.pizzeriaOpen) {
            Order order;
            synchronized (orderQueue) {
                if (orderQueue.isEmpty()) {
                    Thread.currentThread().interrupt();
                    return;
                }
                //get order
                order = orderQueue.remove(0);
            }
            System.out.println("Baker " + baker.getId() + " is preparing pizza for order " + order
                    .getId());
            try {
                setBaking(true);
                //"baking" pizza
                sleep(baker.getSpeed());
            } catch (InterruptedException e) {
                System.out.println("it's always a chance to die while baking pizza");
                Thread.currentThread().interrupt();
                return;
            }
            synchronized (warehouse) {
                //trying to put pizza in a warehouse
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

                setBaking(false);

                pizzeriaData.incrementBakedOrders(1);
                System.out.println("    Pizza for order " + order.getId() + " is ready.");
                warehouse.notifyAll();

            }
            //condition for the end of the work
            if (pizzeriaData.getBakedOrders() == pizzeriaData.getAllOrders()
                    || Main.isPizzeriaOpen()) {
                interruptBakers();
                break;
            }
        }
    }
}

