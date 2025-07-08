package com.ilivanilton.application.task.retrieve.list;

import com.ilivanilton.domain.task.Task;
import com.ilivanilton.domain.task.TaskID;

import java.time.Instant;

public record TaskListOutput(
        TaskID id,
        String description,
        boolean completed,
        Instant createdAt
) {

    public static TaskListOutput from(final Task aTask) {
        return new TaskListOutput(
                aTask.getId(),
                aTask.getDescription(),
                aTask.isCompleted(),
                aTask.getCreatedAt()
        );
    }
}
