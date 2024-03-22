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
    private final Operator operator;
    private boolean isDelivering = false;
    int ha;

    /**
     * simple getter.
     *
     * @return bool if courier is delivering.
     */
    public synchronized boolean isDelivering() {
        return isDelivering;
    }

    /**
     * simple setter.
     *
     * @param delivering sets true if courier is delivering.
     */
    public synchronized void setDelivering(boolean delivering) {
        isDelivering = delivering;
    }

    /**
     * constructor.
     *
     * @param courier courier from json.
     * @param pizzeriaData class full of json data.
     */
    public CourierThread(Courier courier, PizzeriaData pizzeriaData, Operator operator) {
        this.courier = courier;
        this.warehouse = pizzeriaData.getWarehouse();
        this.pizzeriaData = pizzeriaData;
        this.operator = operator;
        this.ha = operator.getOrders().size() + pizzeriaData.getOrders().size();
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
        while (!(pizzeriaData.getBakedOrders() == pizzeriaData.getCompletedOrders())) {
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
                    } catch (InterruptedException e) {
                        return;
                    }
                }
                setDelivering(false);
                operator.getOrderQueueForOperator().remove(0);
                pizzeriaData.incrementCompletedOrders(pizzasToTake);
                warehouse.notifyAll();
            }
            if (pizzeriaData.getBakedOrders() == pizzeriaData.getCompletedOrders()) {
                if (Main.timerThread.isAlive()) {
                    Main.timerThread.interrupt();
                    Main.closePizzeria();
                }
                Main.saveRemainingOrders(pizzeriaData, operator);
                interruptCouriers();
                return;
            }
        }
    }
}