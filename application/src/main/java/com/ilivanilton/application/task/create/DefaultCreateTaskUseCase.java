package com.ilivanilton.application.task.create;

import com.ilivanilton.domain.exceptions.InternalErrorException;
import com.ilivanilton.domain.task.Task;
import com.ilivanilton.domain.task.TaskGateway;

import java.time.Instant;
import java.util.Objects;

public class DefaultCreateTaskUseCase extends CreateTaskUseCase {
    final TaskGateway taskGateway;

    public DefaultCreateTaskUseCase(final TaskGateway taskGateway) {
        this.taskGateway = Objects.requireNonNull(taskGateway);
    }

    @Override
    public CreateTaskOutput execute(CreateTaskCommand anIn) {
        Task task = Task.create(anIn.description(),anIn.completed());
        task = taskGateway.create(task);
        return CreateTaskOutput.from(task);
    }
}
