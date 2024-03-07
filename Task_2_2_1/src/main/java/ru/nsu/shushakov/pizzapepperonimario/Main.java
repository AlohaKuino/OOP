package ru.nsu.shushakov.pizzapepperonimario;

import com.google.gson.Gson;

import java.io.FileReader;

public class Main {
    public static void main(String[] args) {

        try {
            String filePath = "input.json";

            Gson gson = new Gson();

            FileReader reader = new FileReader(filePath);

            PizzeriaData pizzeriaData = gson.fromJson(reader, PizzeriaData.class);

            System.out.println("Number of bakers: " + pizzeriaData.getBakers().size());
            System.out.println("Number of couriers: " + pizzeriaData.getCouriers().size());
            System.out.println("Maximum pizzas in warehouse: " + pizzeriaData.getWarehouse().getCapacity());
            System.out.println("Orders:" + pizzeriaData.getOrders().size());
            reader.close();

            pizzeriaData.setAllOrders(pizzeriaData.getOrders().size());
            pizzeriaData.getWarehouse().setPizzaList();


            for (PizzeriaData.Baker baker : pizzeriaData.getBakers()) {
                Thread bakerThread = new BakerThread(baker, pizzeriaData.getOrders(), pizzeriaData.getWarehouse());
                bakerThread.start();
            }
            for (PizzeriaData.Courier courier : pizzeriaData.getCouriers()) {
                Thread courierThread = new CourierThread(courier, pizzeriaData.getWarehouse(), pizzeriaData);
                courierThread.start();
            }
        } catch (Exception e) {
            System.out.println("oops");
        }
    }
}
