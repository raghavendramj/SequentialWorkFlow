package com.exercise.zig.workflow;

import com.exercise.zig.work.Work;
import com.exercise.zig.work.WorkContext;
import com.exercise.zig.work.WorkReport;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import static com.exercise.zig.work.WorkStatus.FAILED;


public class SequentialFlow extends AbstractWorkFlow {
    private static final Logger LOGGER = Logger.getLogger(SequentialFlow.class.getName());

    private List<Work> works = new ArrayList<>();

    SequentialFlow(String name, List<Work> works) {
        super(name);
        this.works.addAll(works);
    }

    public WorkReport call(WorkContext workContext) {
        WorkReport workReport = null;
        for (Work work : works) {
            workReport = work.call(workContext);
            if (workReport != null && FAILED.equals(workReport.getStatus())) {
                break;
            }
        }
        return workReport;
    }

    public static class Builder {

        private String name;
        private List<Work> works;

        private Builder() {
            this.name = UUID.randomUUID().toString();
            this.works = new ArrayList<>();
        }

        public static SequentialFlow.Builder aNewSequentialFlow() {
            return new SequentialFlow.Builder();
        }

        public SequentialFlow.Builder named(String name) {
            this.name = name;
            return this;
        }

        public SequentialFlow.Builder execute(Work work) {
            this.works.add(work);
            return this;
        }

        public SequentialFlow.Builder then(Work work) {
            this.works.add(work);
            return this;
        }

        public SequentialFlow build() {
            return new SequentialFlow(name, works);
        }
    }
}
