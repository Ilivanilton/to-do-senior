package com.ilivanilton.infrastructure.api.controllers;

import com.ilivanilton.application.task.retrieve.get.GetTaskByIdUseCase;
import com.ilivanilton.infrastructure.api.TaskAPI;
import com.ilivanilton.infrastructure.task.models.*;
import com.ilivanilton.infrastructure.task.presenters.TaskApiPresenter;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class TaskController implements TaskAPI {

    private final GetTaskByIdUseCase getTaskByIdUseCase;

    public TaskController(
            final GetTaskByIdUseCase getTaskByIdUseCase
    ) {
        this.getTaskByIdUseCase = Objects.requireNonNull(getTaskByIdUseCase);
    }

    @Override
    public TaskResponse getById(final String id) {
        return TaskApiPresenter.present(this.getTaskByIdUseCase.execute(id));
    }

}
