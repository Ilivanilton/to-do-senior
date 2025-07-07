package com.ilivanilton.infrastructure.task.presenters;

import com.ilivanilton.application.task.retrieve.get.TaskOutput;
import com.ilivanilton.infrastructure.task.models.TaskResponse;

public interface TaskApiPresenter {

    static TaskResponse present(final TaskOutput output) {
        return new TaskResponse(
                output.id(),
                output.description(),
                output.completed(),
                output.createdAt()
        );
    }
}
