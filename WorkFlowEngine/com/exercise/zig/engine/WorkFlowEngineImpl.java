package com.exercise.zig.engine;

import com.exercise.zig.work.WorkContext;
import com.exercise.zig.work.WorkReport;
import com.exercise.zig.workflow.WorkFlow;

import java.util.logging.Level;
import java.util.logging.Logger;

class WorkFlowEngineImpl implements WorkFlowEngine {

    private static final Logger LOGGER = Logger.getLogger(WorkFlowEngineImpl.class.getName());

    public WorkReport run(WorkFlow workFlow, WorkContext workContext) {
        LOGGER.log(Level.INFO, "Running workflow ''{0}''", workFlow.getName());
        return workFlow.call(workContext);
    }
}
