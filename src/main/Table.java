package main;

public class Table {
    private String course; // the name of the course
    private boolean isEmpty; // a flag used to see if the table is empty or is not empty (i.e., has an unfinished course)

    public Table() {
        course = null;
        isEmpty = true;
    }

    // implements the Waiter serving a course
    public synchronized void serve( String course ) {
        while (!isEmpty) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.course = course;
        isEmpty = false;
        notify();
    }

    // implements the Customer eating a course
    public synchronized String eat() {
        while (isEmpty) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isEmpty = true;
        notify();
        return course;

    }

}