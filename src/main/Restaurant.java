package main;

import java.io.*;

public class Restaurant {

    public static void main(String[] args) {

        try {
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter file name to test: ");
            String fileName = userInput.readLine();

            BufferedReader fileReader = new BufferedReader(new FileReader(fileName));

            int numWaiters = Integer.parseInt(fileReader.readLine());
            for (int i = 0; i < numWaiters; i++) {
                String line = fileReader.readLine();
                String[] inputs = line.split(" ");

                String waiterName = inputs[0];

                int numCustomers = Integer.parseInt(inputs[1]);

                Table[] tables = new Table[numCustomers];
                String[] customerNames = new String[numCustomers];

                String[][] courses = new String[numCustomers][3];
                for (int j = 0; j < numCustomers; j++) {
                    tables[j] = new Table();
                    customerNames[j] = inputs[2 + 4*j];
                    courses[j] = new String[]{inputs[3 + 4*j], inputs[4 + 4*j], inputs[5 + 4*j]};
                }
                new Thread(new Waiter(tables, waiterName, customerNames, courses)).start();
                for (int k = 0; k < tables.length; k++)
                    new Thread(new Customer(tables[k], customerNames[k])).start();
            }
        } catch (IOException e) {
            System.err.println("Error reading input file");
        }
    }
}
