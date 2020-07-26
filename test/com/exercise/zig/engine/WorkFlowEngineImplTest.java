package com.exercise.zig.engine;

import com.exercise.zig.work.*;
import com.exercise.zig.workflow.SequentialFlow;
import com.exercise.zig.workflow.WorkFlow;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Map;
import java.util.Set;

import static com.exercise.zig.engine.WorkFlowEngineBuilder.aNewWorkFlowEngine;

public class WorkFlowEngineImplTest {
    private WorkFlowEngine workFlowEngine = new WorkFlowEngineImpl();
    @Test
    public void run() {
        // given
        WorkFlow workFlow = Mockito.mock(WorkFlow.class);
        WorkContext workContext = Mockito.mock(WorkContext.class);

        // when
        workFlowEngine.run(workFlow,workContext);

        // then
        Mockito.verify(workFlow).call(workContext);
    }

    @Test
    public void useWorkContextToPassInitialParametersAndShareDataBetweenWorkUnits() {
        TeamLead leadObject = new TeamLead("Raghav");
        SequentialFlow sequentialFlow = SequentialFlow.Builder.aNewSequentialFlow()
                .named("Team Lead Building")
                .execute(leadObject)
                .then(leadObject)
                .build();


        WorkContext workContext = new WorkContext();
        workContext.put("empId", 1234);
        workContext.put("fname", "Raghavendra");
        workContext.put("secName", "M J");

        WorkFlowEngine workFlowEngine = aNewWorkFlowEngine().build();
        System.out.println("Lead Object Before:"+ leadObject);
        workFlowEngine.run(sequentialFlow, workContext);
        System.out.println("Lead Object After:"+ leadObject);
    }

    static class TeamLead implements Work {

        private String name;
        private int empId;
        private String fname;
        private String secName;

        public TeamLead(String name) {
            this.name = name;
        }

        public void setEmpId(int empId) {
            this.empId = empId;
        }

        public void setFname(String fname) {
            this.fname = fname;
        }

        public void setSecName(String secName) {
            this.secName = secName;
        }

        @Override
        public String toString() {
            return "TeamLead{" +
                    "name='" + name + '\'' +
                    ", empId=" + empId +
                    ", fname='" + fname + '\'' +
                    ", secName='" + secName + '\'' +
                    '}';
        }

        @Override
        public String getName() {
            return "Team Lead Test";
        }

        @Override
        public WorkReport call(WorkContext workContext) {
            Set<Map.Entry<String, Object>> entrySet = workContext.getEntrySet();
            for (Map.Entry<String, Object> entry : entrySet) {

                if(entry.getKey().equalsIgnoreCase("empId"))
                    this.setEmpId((Integer) entry.getValue());

                else  if(entry.getKey().equalsIgnoreCase("fname"))
                    this.setFname((String) entry.getValue());

                else  if(entry.getKey().equalsIgnoreCase("secName"))
                    this.setSecName((String) entry.getValue());


               // System.out.println(this);

            }
            return new DefaultWorkReport(WorkStatus.COMPLETED, workContext);
        }
    }
}
