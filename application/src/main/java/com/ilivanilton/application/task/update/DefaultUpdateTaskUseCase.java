package com.ilivanilton.application.task.update;

import com.ilivanilton.domain.task.TaskGateway;
import com.ilivanilton.domain.task.Task;
import com.ilivanilton.domain.task.TaskID;

import java.util.Objects;

public class DefaultUpdateTaskUseCase extends UpdateTaskUseCase {
    final TaskGateway taskGateway;

    public DefaultUpdateTaskUseCase(final TaskGateway taskGateway) {
        this.taskGateway = Objects.requireNonNull(taskGateway);
    }

    @Override
    // TODO: Implementar lib VAVR
    public UpdateTaskOutput execute(UpdateTaskCommand command) {
        Task task = taskGateway.findById(TaskID.from(command.id()))
             // TODO: Implementar Exception personalizada
            .orElseThrow(() -> new RuntimeException("Task not found"));

        if (command.description() == null || command.completed() == null) {
            // TODO: Implementar Exception personalizada
            throw new RuntimeException("Task description and completed are required");
        }

        task.update(command.description(), command.completed());
        return UpdateTaskOutput.from(taskGateway.update(task));
    }
}

