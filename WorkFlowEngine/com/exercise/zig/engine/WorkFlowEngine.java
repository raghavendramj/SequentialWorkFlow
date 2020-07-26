package com.exercise.zig.engine;

import com.exercise.zig.work.WorkContext;
import com.exercise.zig.work.WorkReport;
import com.exercise.zig.workflow.WorkFlow;

public interface WorkFlowEngine {
    WorkReport run(WorkFlow workFlow, WorkContext workContext);
}
