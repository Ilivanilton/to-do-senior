package com.ilivanilton.application.task.create;

import com.ilivanilton.domain.task.Task;

import java.time.Instant;

public record CreateTaskOutput(
        String id,
        String description,
        boolean completed,
        Instant createdAt
) {
    public static CreateTaskOutput from(final Task anTask) {
        return new CreateTaskOutput(
                anTask.getId().getValue(),
                anTask.getDescription(),
                anTask.isCompleted(),
                anTask.getCreatedAt()
        );
    }
}
