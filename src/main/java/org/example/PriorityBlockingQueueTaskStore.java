package org.example;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueueTaskStore implements TaskStore<ScheduledTask> {

    private final PriorityBlockingQueue<ScheduledTask> taskQueue;
    private final Set<ScheduledTask> tasks;

    public PriorityBlockingQueueTaskStore(Comparator<ScheduledTask> comparator, Integer queueSize) {
        this.taskQueue = new PriorityBlockingQueue<>(queueSize, comparator);
        this.tasks = new HashSet<>();
    }

    @Override
    public void add(ScheduledTask task) {
        if (tasks.add(task)) {
            taskQueue.add(task);
        }
    }

    @Override
    public ScheduledTask poll() {
        ScheduledTask task = taskQueue.poll();
        if (task != null) {
            tasks.remove(task);
        }
        return task;
    }

    @Override
    public boolean isEmpty() {
        return taskQueue.isEmpty();
    }

    @Override
    public ScheduledTask peek() {
        return taskQueue.peek();
    }

    @Override
    public boolean remove(ScheduledTask task) {
        if (tasks.contains(task)) {
            return taskQueue.remove(task);
        } else {
            return false;
        }
    }
}
