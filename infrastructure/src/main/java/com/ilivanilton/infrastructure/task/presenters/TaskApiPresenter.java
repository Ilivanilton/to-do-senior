package com.ilivanilton.infrastructure.task.presenters;

import com.ilivanilton.application.task.retrieve.get.TaskOutput;
import com.ilivanilton.application.task.retrieve.list.TaskListOutput;
import com.ilivanilton.infrastructure.task.models.TaskListResponse;
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
    static TaskListResponse present(final TaskListOutput output) {
        return new TaskListResponse(
                output.id().getValue(),
                output.description(),
                output.completed(),
                output.createdAt()
        );
    }

}
