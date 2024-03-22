package ru.nsu.shushakov.pizzapepperonimario;

import java.util.ArrayList;
import java.util.List;

/**
 * class to read json correctly and then use its data.
 */
public class PizzeriaData {

    List<Courier> couriers = new ArrayList<>();
    List<Baker> bakers = new ArrayList<>();
    public List<Order> orders = new ArrayList<>();
    transient int allOrders;
    Warehouse warehouse;
    int time;

    /**
     * simple getter.
     *
     * @return time that pizzeria should work.
     */
    public int getTime() {
        return time;
    }

    /**
     * simple getter.
     *
     * @return arrayList of objects type Baker read from json.
     */
    public List<Baker> getBakers() {
        return bakers;
    }

    /**
     * simple getter.
     *
     * @return arrayList of objects type Courier read from json.
     */
    public List<Courier> getCouriers() {
        return couriers;
    }

    /**
     * simple getter.
     *
     * @return warehouse with capacity.
     */
    public Warehouse getWarehouse() {
        return this.warehouse;
    }

    /**
     * simple getter.
     *
     * @return number of all orders that pizzeria has.
     */
    public int getAllOrders() {
        return allOrders;
    }

    /**
     * simple getter.
     *
     * @param allOrders number of all orders that pizzeria has.
     */
    public void setAllOrders(int allOrders) {
        this.allOrders = allOrders;
    }

    /**
     * simple getter.
     *
     * @return arrayList of objects type Order from json.
     */
    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    /**
     * baked orders they are orders that was made by bakers but not by couriers.
     *
     * @return number of order that was already baked.
     */
    public int getBakedOrders() {
        return bakedOrders;
    }

    private transient int completedOrders = 0;
    private transient int bakedOrders = 0;

    /**
     * simple adder.
     *
     * @param added amount of orders that courier has delivered.
     */
    public synchronized void incrementCompletedOrders(int added) {
        completedOrders += added;
    }

    /**
     * adds one every time when baker puts pizza to a warehouse.
     *
     * @param added one because every baker can bake one pizza in a moment.
     */
    public synchronized void incrementBakedOrders(int added) {
        bakedOrders += added;
    }

    /**
     * simple getter.
     *
     * @return orders that was baked and delivered.
     */
    public synchronized int getCompletedOrders() {
        return completedOrders;
    }

    /**
     * Baker class with attributes that we read from json.
     */
    public static class Baker {
        int id;
        int speed;

        /**
         * simple getter.
         *
         * @return id of a baker.
         */
        public int getId() {
            return id;
        }

        /**
         * simple getter.
         *
         * @return baker's speed in milliseconds that he will sleep while baking.
         */
        public int getSpeed() {
            return speed;
        }
    }

    /**
     * Courier class with attributes that we read from json.
     */
    public static class Courier {

        int id;
        int capacity;
        int speed;

        /**
         * simple getter.
         *
         * @return id of a courier.
         */
        public int getId() {
            return id;
        }

        /**
         * simple getter.
         *
         * @return number of pizzas courier can deliver in one ride.
         */
        public int getCapacity() {
            return capacity;
        }

        /**
         * simple getter.
         *
         * @return milliseconds that courier will be sleeping while delivering an order.
         */
        public int getSpeed() {
            return speed;
        }
    }

    /**
     * warehouse class with attributes that we read from json.
     */
    public static class Warehouse {
        private int capacity;
        private transient List<Order> pizzaList;


        /**
         * <p>
         *    method that tries to add pizza in a warehouse storage for couriers can take it.
         *    it waits for a place in a warehouse.
         *    only bakers can add pizzas.
         * </p>
         *
         * @param pizza order from order list.
         * @throws InterruptedException wait want to throw InterruptedException.
         */
        public synchronized void addPizza(Order pizza) throws InterruptedException {
            while (isFull()) {
                wait();
            }
            this.pizzaList.add(pizza);
            notifyAll();
        }

        /**
         * <p>
         *    method tries to take pizza from warehouse's storage.
         *    it waits for any pizza that can appear in a storage.
         *    only couriers can take orders.
         * </p>
         *
         * @return order from warehouse's storage.
         * @throws InterruptedException wait want to throw InterruptedException.
         */
        public synchronized Order takePizza() throws InterruptedException {
            while (this.pizzaList.isEmpty()) {
                wait();
            }
            Order pizza = this.pizzaList.remove(0);
            notifyAll();
            return pizza;
        }

        /**
         * simple getter.
         *
         * @return bool if warehouse's storage is full
         */
        public synchronized boolean isFull() {
            return this.pizzaList.size() >= this.capacity;
        }

        /**
         * simple getter.
         *
         * @return capacity of a warehouse.
         */
        public int getCapacity() {
            return this.capacity;
        }

        /**
         * simple getter.
         *
         * @return bool if warehouse is empty.
         */
        public boolean isEmpty() {
            return this.pizzaList.isEmpty();
        }

        /**
         * simple getter.
         *
         * @return how many pizzas are already in a warehouse.
         */
        public int getCurrentOccupancy() {
            return this.pizzaList.size();
        }

        /**
         * initializes a pizzaList idk it cant initialize by itself(((.
         */
        public void setPizzaList() {
            this.pizzaList = new ArrayList<>();
        }
    }

    /**
     * class for order.
     */

    public static class Order {
        int id;

        /**
         * simple getter.
         *
         * @return order id.
         */
        public int getId() {
            return id;
        }
    }
}
