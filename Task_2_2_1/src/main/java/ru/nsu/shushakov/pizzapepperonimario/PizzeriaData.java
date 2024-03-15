package ru.nsu.shushakov.pizzapepperonimario;

import java.util.*;

public class PizzeriaData {

    List<Courier> couriers = new ArrayList<>();
    List<Baker> bakers = new ArrayList<>();
    List<Order> orders = new ArrayList<>();
    transient int allOrders;
    Warehouse warehouse;

    int time;

    public void setTime(int time) {
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    public List<Baker> getBakers() {
        return bakers;
    }

    public List<Courier> getCouriers() {
        return couriers;
    }

    public Warehouse getWarehouse() {
        return this.warehouse;
    }

    public int getAllOrders() {
        return allOrders;
    }

    public void setAllOrders(int allOrders) {
        this.allOrders = allOrders;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public int getBakedOrders() {
        return bakedOrders;
    }

    private transient int completedOrders = 0;
    private transient int bakedOrders = 0;

    public synchronized void incrementCompletedOrders(int added) {
        completedOrders += added;
    }

    public synchronized void incrementBakedOrders(int added) {
        bakedOrders += added;
    }

    public synchronized int getCompletedOrders() {
        return completedOrders;
    }

    public class Baker {
        int id;
        int speed;

        public void setCapacityAndId(int id, int speed) {
            this.id = id;
            this.speed = speed;
            bakers.add(this);
        }

        public int getId() {
            return id;
        }

        public int getSpeed() {
            return speed;
        }
    }

    public class Courier {

        int id;
        int capacity;
        int speed;

        public void setCapacityAndIdAndSpeed(int id, int capacity, int speed) {
            this.id = id;
            this.capacity = capacity;
            this.speed = speed;
            couriers.add(this);
        }

        public int getId() {
            return id;
        }

        public int getCapacity() {
            return capacity;
        }

        public int getSpeed() {
            return speed;
        }
    }

    public class Warehouse {
        private int capacity;

        private transient List<Order> pizzaList;


        public synchronized void addPizza(Order pizza) throws InterruptedException {
            while (isFull()) {
                wait();
            }
            this.pizzaList.add(pizza);
            notifyAll();
        }

        public synchronized Order takePizza() throws InterruptedException {
            while (this.pizzaList.isEmpty()) {
                wait();
            }
            Order pizza = this.pizzaList.remove(0);
            notifyAll();
            return pizza;
        }

        public synchronized boolean isFull() {
            return this.pizzaList.size() >= this.capacity;
        }

        public int getCapacity() {
            return this.capacity;
        }

        public boolean isEmpty() {
            return this.pizzaList.isEmpty();
        }

        public int getCurrentOccupancy() {
            return this.pizzaList.size();
        }

        public void setPizzaList() {
            this.pizzaList = new ArrayList<>();
        }
    }

    public class Order {
        int id;

        public void setOrder(int id) {
            this.id = id;
            orders.add(this);
        }

        public int getId() {
            return id;
        }
    }
}
