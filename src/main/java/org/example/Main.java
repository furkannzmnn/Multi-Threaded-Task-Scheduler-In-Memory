package org.example;

public class Main {
    public static void main(String[] args){
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
