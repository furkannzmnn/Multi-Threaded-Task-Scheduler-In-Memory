package org.example;

public class RecurringTask extends ScheduledTask{
    private final long executionTime;
    private final long interval;

    public RecurringTask(ExecutionContext executionContext, long executionTime, long interval) {
        super(executionContext);
        this.executionTime = executionTime;
        this.interval = interval;
    }

    @Override
    ScheduledTask nextScheduledTask() {
        return new RecurringTask(executionContext, executionTime + interval, interval);
    }

    @Override
    long getNextExecutionTime() {
        return executionTime;
    }

    @Override
    boolean isRecurring() {
        return true;
    }
}
