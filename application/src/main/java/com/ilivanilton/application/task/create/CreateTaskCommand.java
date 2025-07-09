package com.ilivanilton.application.task.create;

public record CreateTaskCommand(
        String description,
        boolean completed
) {
    public static CreateTaskCommand with(
            final String description,
            final boolean completed)
    {
        return new CreateTaskCommand(description, completed);
    }
}
