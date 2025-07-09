package com.ilivanilton.application.task.retrieve.get;

import com.ilivanilton.domain.exceptions.NotFoundException;
import com.ilivanilton.domain.task.Task;
import com.ilivanilton.domain.task.TaskGateway;
import com.ilivanilton.domain.task.TaskID;

import java.util.Objects;
import java.util.function.Supplier;

public class DefaultGetTaskByIdUseCase extends GetTaskByIdUseCase {

    private final TaskGateway taskGateway;

    public DefaultGetTaskByIdUseCase(final TaskGateway taskGateway) {
        this.taskGateway = Objects.requireNonNull(taskGateway);
    }

    @Override
    public TaskOutput execute(String anIn) {
        final var taskId = TaskID.from(anIn);
        return this.taskGateway.findById(taskId)
                .map(TaskOutput::from)
                .orElseThrow(notFound(taskId));
    }

    private Supplier<RuntimeException> notFound(final TaskID anId) {
        return () -> NotFoundException.with(Task.class,anId);
    }
}
