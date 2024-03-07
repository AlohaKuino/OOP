package ru.nsu.shushakov.pizzapepperonimario;

import ru.nsu.shushakov.pizzapepperonimario.PizzeriaData.Baker;
import ru.nsu.shushakov.pizzapepperonimario.PizzeriaData.Warehouse;
import ru.nsu.shushakov.pizzapepperonimario.PizzeriaData.Order;

import java.util.List;
public class BakerThread extends Thread {

    private final Baker baker;
    private final List<Order> orderQueue;
    private final Warehouse warehouse;
    public BakerThread(Baker baker, List<Order> orderQueue, Warehouse warehouse) {
        this.baker = baker;
        this.orderQueue = orderQueue;
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        while (true) {
            Order order;
            synchronized (orderQueue) {
                while (orderQueue.isEmpty()) {
                    try {
                        orderQueue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                order = orderQueue.remove(0);
            }
            System.out.println("Baker " + baker.getId() + " is preparing pizza for order " + order.getId());

            try {
                sleep(baker.getSpeed());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            synchronized (warehouse) {
                while (warehouse.isFull()) {
                    try {
                        warehouse.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    warehouse.addPizza(order);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Pizza for order " + order.getId() + " is ready.");
                warehouse.notifyAll();
            }
        }
    }
}

