package main;

import java.util.Random;

public class Customer implements Runnable {
    private final static int MAX_CUSTOMER_MILLIS = 4000; // must wait for between 0 and 4 seconds.
    private final Table table; // Table object that this Customer sits at
    private final String customerName; // name of this Customer

    public Customer( Table table, String customerName ) {
        this.table = table;
        this.customerName = customerName;
    }

    /*
    For each customer, a thread on this Customer object eats the three courses in the correct order
    by calling the eat() method in the corresponding Table, prints out what course this Customer is eating,
    and sleeps for a random time between 0 & 4 seconds to mimic time taken to eat.
     */
    public void run() {
        for (int i = 0; i < 3; i++) {
            Random rng = new Random();
            try {
                String course = table.eat();
                System.out.println(customerName + " is eating: " + course);
                Thread.sleep(rng.nextInt(MAX_CUSTOMER_MILLIS));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
