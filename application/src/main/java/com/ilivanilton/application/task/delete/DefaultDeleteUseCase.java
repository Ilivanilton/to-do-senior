package com.ilivanilton.application.task.delete;

import com.ilivanilton.domain.task.TaskGateway;
import com.ilivanilton.domain.task.TaskID;

import java.util.Objects;

public class DefaultDeleteUseCase extends DeleteTaskUseCase {

    private final TaskGateway taskGateway;

    public DefaultDeleteUseCase(TaskGateway taskGateway) {
        this.taskGateway = Objects.requireNonNull(taskGateway);
    }

    @Override
    public void execute(String anIn) {
        final TaskID taskId = TaskID.from(anIn);
        taskGateway.deleteById(taskId);
    }
}
