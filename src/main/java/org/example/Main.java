package org.example;

import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TaskStore<ScheduledTask> taskStore = new PriorityBlockingQueueTaskStore(new Comparator<ScheduledTask>() {
            @Override
            public int compare(ScheduledTask o1, ScheduledTask o2) {
                return 0;
            }
        }, 10);


        ScheduledTask recurring = new RecurringTask(() -> System.out.println("Executing recurring task"), 9, 9);


        taskStore.add(recurring);

        new TaskScheduler(new ExecutorConfig(999), taskStore);


        System.out.println("Stopping task scheduler");

    }
}