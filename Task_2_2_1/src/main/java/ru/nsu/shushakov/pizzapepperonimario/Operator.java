package ru.nsu.shushakov.pizzapepperonimario;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import ru.nsu.shushakov.pizzapepperonimario.PizzeriaData.Order;


/**
 * <p>
 *     class where i forgot to add notifyAll and waste ~2 hours of my life fixing code
 *     but it get some improvements but i dont want to do them anymore.
 * </p>
 */
public class Operator implements Runnable {

    private final List<Order> orderQueueForOperator;
    private final List<Order> orderQueue;

    private final Random random = new Random();


    /**
     * constructor.
     *
     * @param orderQueue list of orders.
     */
    public Operator(List<Order> orderQueue) {
        this.orderQueueForOperator = orderQueue;
        this.orderQueue = new ArrayList<>();
    }

    /**
     * method to get orders to bake.
     *
     * @return orders.
     */
    public List<Order> getOrders() {
        return this.orderQueue;
    }

    /**
     * gives all order.
     *
     * @return all orders.
     */
    public List<Order> getOrderQueueForOperator() {
        return orderQueueForOperator;
    }

    /**
     * thread for a operator/customer.
     */
    @Override
    public void run() {
        System.out.println("operator on");

        while (!Main.isPizzeriaClose()) {

            int numberOfOrders = random.nextInt(getOrderQueueForOperator().size() - 2) + 2;
            for (int i = 0; i < numberOfOrders; i++) {
                try {
                    Order order = this.orderQueueForOperator.remove(0);

                    synchronized (this.orderQueue) {
                        this.orderQueue.add(order);
                        System.out.println("                                           "
                                + " Operator added order: " + order.getId());
                        orderQueue.notifyAll();
                    }

                } catch (IndexOutOfBoundsException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("enough");
                    return;
                }
            }
            try {
                int interval = random.nextInt(5000) + 5000;
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                System.out.println("it's always a chance to die while operating pizza");
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}