package com.exercise.zig.work;

import java.util.UUID;

public interface Work {

    default String getName() {
        return UUID.randomUUID().toString();
    }

    WorkReport call(WorkContext workContext);
}
