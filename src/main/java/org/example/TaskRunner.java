package org.example;

public class TaskRunner implements Runnable{
    private final TaskStore<ScheduledTask> taskStore;
    private boolean isRunning = true;

    public TaskRunner(TaskStore<ScheduledTask> taskStore) {
        this.taskStore = taskStore;
    }

    @Override
    public void run() {
        while (isRunning) {
            ScheduledTask scheduledTask = taskStore.poll();
            if (scheduledTask != null) {
                scheduledTask.execute();
                if (scheduledTask.isRecurring()) {
                    taskStore.add(scheduledTask.nextScheduledTask());
                }
            }else {
                break;
            }
        }
    }

    public void stop() {
        this.isRunning = false;
    }
}
