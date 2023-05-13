package org.example;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Comparator;

import static javax.print.attribute.standard.MediaSizeName.A;

public class Main {
    public static void main(String[] args) throws InterruptedException {
       run();
    }


    private static void run() {
        TaskStore<ScheduledTask> taskStore = new PriorityBlockingQueueTaskStore((o1, o2) -> 0, 10);


        ScheduledTask recurring = new RecurringTask(() -> System.out.println("Executing recurring task"), 1000, 1000);

        OneTimeTask oneTimeTask = new OneTimeTask(() -> System.out.println("Executing one time task"), 1);

        taskStore.add(oneTimeTask);
        taskStore.add(recurring);

        new TaskScheduler(new ExecutorConfig(1), taskStore);


        System.out.println("Stopping task scheduler");
    }
}
