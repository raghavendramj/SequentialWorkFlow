
package com.exercise.zig.engine;

public class WorkFlowEngineBuilder {

    public static WorkFlowEngineBuilder aNewWorkFlowEngine() {
        return new WorkFlowEngineBuilder();
    }

    private WorkFlowEngineBuilder() {
    }

    public WorkFlowEngine build() {
        return new WorkFlowEngineImpl();
    }
}
