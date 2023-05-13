package org.example;

import java.util.ArrayList;
import java.util.List;

public class TaskScheduler {
    private final List<Runnable> threads;
    private final TaskStore<ScheduledTask> taskStore;

    public TaskScheduler(ExecutorConfig executorConfig, TaskStore<ScheduledTask> taskStore) {
        this.threads = new ArrayList<>();
        this.taskStore = taskStore;

        for (int i = 0; i < executorConfig.getNumThreads(); i++) {
            TaskRunner taskRunner = new TaskRunner(taskStore);
            Thread thread = new Thread(taskRunner);
            thread.start();
            threads.add(thread);
        }
    }
}
