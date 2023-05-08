package org.example;

public interface TaskStore<T extends ScheduledTask> {
    void add(T task);
    T poll();
    boolean isEmpty();
    T peek();
    boolean remove(T task);
}
