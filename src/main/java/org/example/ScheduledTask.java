package org.example;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class ScheduledTask {
    protected final ExecutionContext executionContext;
    public ScheduledTask(ExecutionContext executionContext) {
        this.executionContext = executionContext;
    }

    public void execute() {
        executionContext.execute();
    }


    abstract ScheduledTask nextScheduledTask();

    abstract long getNextExecutionTime();
    abstract boolean isRecurring();
}
