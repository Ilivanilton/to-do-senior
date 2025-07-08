package com.ilivanilton.infrastructure.api.controllers;

import com.ilivanilton.application.task.retrieve.get.GetTaskByIdUseCase;
import com.ilivanilton.application.task.retrieve.list.ListTaskUseCase;
import com.ilivanilton.domain.pagination.Pagination;
import com.ilivanilton.domain.pagination.SearchQuery;
import com.ilivanilton.infrastructure.api.TaskAPI;
import com.ilivanilton.infrastructure.task.models.*;
import com.ilivanilton.infrastructure.task.presenters.TaskApiPresenter;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class TaskController implements TaskAPI {

    private final GetTaskByIdUseCase getTaskByIdUseCase;
    private final ListTaskUseCase listTaskUseCase;

    public TaskController(
            final GetTaskByIdUseCase getTaskByIdUseCase,
            final ListTaskUseCase listTaskUseCase
    ) {
        this.getTaskByIdUseCase = Objects.requireNonNull(getTaskByIdUseCase);
        this.listTaskUseCase = Objects.requireNonNull(listTaskUseCase);
    }

    @Override
    public TaskResponse getById(final String id) {
        return TaskApiPresenter.present(this.getTaskByIdUseCase.execute(id));
    }

    @Override
    public Pagination<TaskListResponse> listAll(
            final String search,
            final int page,
            final int perPage,
            final String sort,
            final String direction
    ) {
        return listTaskUseCase
                .execute(new SearchQuery(page, perPage, search, sort, direction))
                .map(TaskApiPresenter::present);
    }

}
