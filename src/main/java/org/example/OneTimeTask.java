package org.example;

public class OneTimeTask extends ScheduledTask {
    private final long executionTime;


    public OneTimeTask(ExecutionContext executionContext, long executionTime) {
        super(executionContext);
        this.executionTime = executionTime;
    }

    @Override
    ScheduledTask nextScheduledTask() {
        return null;
    }

    @Override
    long getNextExecutionTime() {
        return executionTime;
    }

    @Override
    boolean isRecurring() {
        return false;
    }
}
