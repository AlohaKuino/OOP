package ru.nsu.shushakov.pizzapepperonimario;

import java.util.ArrayList;
import java.util.List;

/**
 * class to read json correctly and then use its data.
 */
public class PizzeriaData {

    List<Courier> couriers = new ArrayList<>();
    List<Baker> bakers = new ArrayList<>();
    List<Order> orders = new ArrayList<>();
    transient int allOrders;
    Warehouse warehouse;
    int time;

    /**
     * @return time that pizzeria should work.
     *
     * simple getter.
     */
    public int getTime() {
        return time;
    }

    /**
     * @return arrayList of objects type Baker read from json.
     *
     * simple getter.
     */
    public List<Baker> getBakers() {
        return bakers;
    }

    /**
     * @return arrayList of objects type Courier read from json.
     *
     * simple getter.
     */
    public List<Courier> getCouriers() {
        return couriers;
    }

    /**
     * @return warehouse with capacity.
     *
     * simple getter.
     */
    public Warehouse getWarehouse() {
        return this.warehouse;
    }

    /**
     * @return number of all orders that pizzeria has.
     *
     * simple getter.
     */
    public int getAllOrders() {
        return allOrders;
    }

    /**
     * @param allOrders number of all orders that pizzeria has.
     *
     * simple getter.
     */
    public void setAllOrders(int allOrders) {
        this.allOrders = allOrders;
    }

    /**
     * @return arrayList of objects type Order from json.
     *
     * simple getter.
     */
    public List<Order> getOrders() {
        return orders;
    }

    /**
     * @return number of order that was already baked.
     *
     * baked orders they are orders that was made by bakers but not by couriers.
     */
    public int getBakedOrders() {
        return bakedOrders;
    }

    private transient int completedOrders = 0;
    private transient int bakedOrders = 0;

    /**
     * @param added amount of orders that courier has delivered.
     *
     * simple adder.
     */
    public synchronized void incrementCompletedOrders(int added) {
        completedOrders += added;
    }

    /**
     * @param added one because every baker can bake one pizza in a moment.
     *
     * adds one every time when baker puts pizza to a warehouse.
     */
    public synchronized void incrementBakedOrders(int added) {
        bakedOrders += added;
    }

    /**
     * @return orders that was baked and delivered.
     *
     * simple getter.
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
         * @return id of a baker.
         *
         * simple getter.
         */
        public int getId() {
            return id;
        }

        /**
         * @return baker's speed in milliseconds that he will sleep while baking.
         *
         * simple getter.
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
         * @return id of a courier.
         *
         * simple getter.
         */
        public int getId() {
            return id;
        }

        /**
         * @return number of pizzas courier can deliver in one ride.
         *
         * simple getter.
         */
        public int getCapacity() {
            return capacity;
        }

        /**
         * @return milliseconds that courier will be sleeping while delivering an order.
         *
         * simple getter.
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
         * @param pizza order from order list.
         * @throws InterruptedException wait want to throw InterruptedException.
         * <p>
         *    method that tries to add pizza in a warehouse storage for couriers can take it.
         *    it waits for a place in a warehouse.
         *    only bakers can add pizzas.
         * </p>
         */
        public synchronized void addPizza(Order pizza) throws InterruptedException {
            while (isFull()) {
                wait();
            }
            this.pizzaList.add(pizza);
            notifyAll();
        }

        /**
         * @return order from warehouse's storage.
         * @throws InterruptedException wait want to throw InterruptedException.
         * <p>
         *     method tries to take pizza from warehouse's storage.
         *     it waits for any pizza that can appear in a storage.
         *     only couriers can take orders.
         * </p>
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
         * @return bool if warehouse's storage is full
         *
         * simple getter.
         */
        public synchronized boolean isFull() {
            return this.pizzaList.size() >= this.capacity;
        }

        /**
         * @return capacity of a warehouse.
         *
         * simple getter.
         */
        public int getCapacity() {
            return this.capacity;
        }

        /**
         * @return bool if warehouse is empty.
         *
         * simple getter.
         */
        public boolean isEmpty() {
            return this.pizzaList.isEmpty();
        }

        /**
         * @return how many pizzas are already in a warehouse.
         *
         * simple getter.
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
     * order class.
     */
    public static class Order {
        int id;

        /**
         * @return order id.
         *
         * simple getter.
         */
        public int getId() {
            return id;
        }
    }
}
