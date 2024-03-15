package ru.nsu.shushakov.pizzapepperonimario;

import java.util.ArrayList;
import java.util.List;
import ru.nsu.shushakov.pizzapepperonimario.PizzeriaData.Courier;
import ru.nsu.shushakov.pizzapepperonimario.PizzeriaData.Order;
import ru.nsu.shushakov.pizzapepperonimario.PizzeriaData.Warehouse;

/**
 * class that represents courier behaviour in thread.
 */
public class CourierThread extends Thread {
    private final PizzeriaData pizzeriaData;
    private final Courier courier;
    private final Warehouse warehouse;
    private boolean isDelivering = true;


    /**
     * @return bool if courier is delivering.
     *
     * simple getter.
     */
    public synchronized boolean isDelivering() {
        return isDelivering;
    }

    /**
     * @param delivering sets true if courier is delivering.
     *
     *  simple setter.
     */
    public synchronized void setDelivering(boolean delivering) {
        isDelivering = delivering;
    }

    /**
     * @param courier courier from json.
     * @param warehouse warehouse with data from json.
     * @param pizzeriaData class full of json data.
     */
    public CourierThread(Courier courier, Warehouse warehouse, PizzeriaData pizzeriaData) {
        this.courier = courier;
        this.warehouse = warehouse;
        this.pizzeriaData = pizzeriaData;
    }

    /**
     * method that will interrupt couriers when i need to.
     */
    public void interruptCouriers() {
        for (int i = 0; i < pizzeriaData.getCouriers().size(); i++) {
            if (Main.couriers[i].isAlive() && !Main.couriers[i].isInterrupted()
                    && !((CourierThread) Main.couriers[i]).isDelivering()) {
                Main.couriers[i].interrupt();
            }
        }
    }

    /**
     * <p>
     *     override method for a courier.
     *     if warehouse is empty than he need to wait.
     *     when pizza is ready courier tries to take it from a warehouse.
     *     if all orders were completed or pizzeria should be closed then interrupt all threads
     * </p>
     */
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