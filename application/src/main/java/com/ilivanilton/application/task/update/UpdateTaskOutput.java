package com.ilivanilton.application.task.update;

import com.ilivanilton.domain.task.Task;

import java.time.Instant;

public record UpdateTaskOutput(
        String id,
        String description,
        Boolean completed,
        Instant createdAt
) {
    public static UpdateTaskOutput from(Task task) {
        return new UpdateTaskOutput(
                task.getId().getValue(),
                task.getDescription(),
                task.isCompleted(),
                task.getCreatedAt());
    }
}

