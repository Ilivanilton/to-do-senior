package com.ilivanilton.application.task.retrieve.get;

import com.ilivanilton.domain.task.Task;

import java.time.Instant;

public record TaskOutput(
        String id,
        String description,
        boolean completed,
        Instant createdAt,
        Instant updatedAt
) {

    public static TaskOutput from(final Task aTask){
        return new TaskOutput(
                aTask.getId(),
                aTask.getDescription(),
                aTask.isCompleted(),
                aTask.getCreatedAt(),
                aTask.getUpdatedAt()
        );
    }
}

