package org.example;

public class TaskRunner implements Runnable {
    private final TaskStore<ScheduledTask> taskStore;
    private boolean isRunning = true;

    public TaskRunner(TaskStore<ScheduledTask> taskStore) {
        this.taskStore = taskStore;
    }

    @Override
    public void run() {
        while (isRunning) {
            ScheduledTask scheduledTask = taskStore.poll();
            long nextExecutionTime = System.currentTimeMillis() +  scheduledTask.getNextExecutionTime();
            long currentTime = System.currentTimeMillis();
            if (nextExecutionTime > currentTime) {
                try {
                    Thread.sleep(nextExecutionTime - currentTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            scheduledTask.execute();
            if (scheduledTask.isRecurring()) {
                taskStore.add(scheduledTask.nextScheduledTask());
            }
        }
    }

    public void stop() {
        this.isRunning = false;
    }
}
