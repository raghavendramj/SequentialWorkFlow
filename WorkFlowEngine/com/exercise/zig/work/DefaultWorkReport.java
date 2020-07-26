package com.exercise.zig.work;

public class DefaultWorkReport implements WorkReport {

    private WorkStatus status;
    private WorkContext workContext;
    private Throwable error;


    public DefaultWorkReport(WorkStatus status, WorkContext workContext) {
        this.status = status;
        this.workContext = workContext;
    }

    public WorkStatus getStatus() {
        return status;
    }

    public Throwable getError() {
        return error;
    }

    @Override
    public WorkContext getWorkContext() {
        return workContext;
    }

    @Override
    public String toString() {
        return "DefaultWorkReport {" +
                "status=" + status +
                ", context=" + workContext +
                ", error=" + (error == null ? "''" : error) +
                '}';
    }
}
