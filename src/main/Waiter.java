package main;

import java.util.Random;

public class Waiter implements Runnable{

    private final static int MAX_WAITER_MILLIS = 4000; // must wait for between 0 and 4 seconds.
    private final static int N_COURSES = 3; // number of courses is exactly three.

    private final Table[] tables; // array of Table objects this Waiter waits on
    private final String waiterName; // name of this Waiter
    private final String[] customerNames; // names of Customers served by Waiter
    private final String[][] courses; // multi-dimensional array of courses for each Customer of this Waiter.  (courses[i][j] has the j-th course for the i-th Customer of this Waiter)

    public Waiter( Table[] tables, String waiterName, String[] customerNames, String[][] courses ) {
        this.tables = tables;
        this.waiterName = waiterName;
        this.customerNames = customerNames;
        this.courses = courses;
    }

    private void serveTable(Table table, String customer, String[] courses) {
        for (String course : courses) {
            Random rng = new Random();
            try {
                table.serve(course);
                System.out.println(waiterName + " serves " + customer + " " + course);
                Thread.sleep(rng.nextInt(MAX_WAITER_MILLIS));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /*
    For each customer, a thread on this Waiter object serves the three courses in the correct order by calling the
    serve() method in the corresponding Table, prints out what course is served to which Customer, and sleeps for a
    random time between 0 & 4 seconds to mimic time taken in serving.
     */
    public void run() {
        for (int i = 0; i < tables.length; i++) {
            for (int j = 0; j < N_COURSES; j ++) {
                Random rng = new Random();
                try {
                    tables[i].serve(courses[i][j]);
                    System.out.println(waiterName + " serves " + customerNames[i] + " " + courses[i][j]);
                    Thread.sleep(rng.nextInt(MAX_WAITER_MILLIS));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
