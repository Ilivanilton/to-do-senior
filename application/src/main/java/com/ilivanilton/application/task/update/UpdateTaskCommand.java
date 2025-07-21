package com.ilivanilton.application.task.update;

import com.ilivanilton.domain.task.Task;

public record UpdateTaskCommand(
        String id,
        String description,
        Boolean completed
) {
    public static UpdateTaskCommand with(
            final String id,
            final String description,
            final Boolean completed) {
        return new UpdateTaskCommand(id, description, completed);
    }
}

