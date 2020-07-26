package com.exercise.zig.workflow;

import com.exercise.zig.work.Work;
import com.exercise.zig.work.WorkContext;
import com.exercise.zig.work.WorkReport;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.util.Map;
import java.util.Set;


public class SequentialFlowTest {

    @Test
    public void call() {
        // given
        Work work1 = Mockito.mock(Work.class);
        Work work2 = Mockito.mock(Work.class);
        Work work3 = Mockito.mock(Work.class);
        WorkContext workContext = Mockito.mock(WorkContext.class);
        SequentialFlow sequentialFlow = SequentialFlow.Builder.aNewSequentialFlow()
                .named("Employee")
                .execute(work1)
                .then(work2)
                .then(work3)
                .then(null)
                .build();

        // when
        sequentialFlow.call(workContext);

        // then
        InOrder inOrder = Mockito.inOrder(work1, work2, work3);
        inOrder.verify(work1, Mockito.times(1)).call(workContext);
        inOrder.verify(work2, Mockito.times(1)).call(workContext);
        inOrder.verify(work3, Mockito.times(1)).call(workContext);
    }



}
