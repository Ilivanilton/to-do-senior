package com.ilivanilton.application.task.retrieve.get;

import com.ilivanilton.domain.task.TaskGateway;

import java.util.Objects;
import java.util.function.Supplier;

public class DefaultGetTaskByIdUseCase extends GetTaskByIdUseCase {

    private final TaskGateway taskGateway;

    public DefaultGetTaskByIdUseCase(final TaskGateway taskGateway) {
        this.taskGateway = Objects.requireNonNull(taskGateway);
    }

    @Override
    public TaskOutput execute(String anIn) {
        return this.taskGateway.findById(anIn)
                .map(TaskOutput::from)
                .orElseThrow(notFound(anIn));
    }

    private Supplier<RuntimeException> notFound(String anIn) {
        return () -> new RuntimeException(anIn + " not found");
    }
}
