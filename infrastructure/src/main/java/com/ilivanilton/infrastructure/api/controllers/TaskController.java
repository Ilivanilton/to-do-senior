package com.ilivanilton.infrastructure.api.controllers;

import com.ilivanilton.application.task.create.CreateTaskCommand;
import com.ilivanilton.application.task.create.CreateTaskOutput;
import com.ilivanilton.application.task.create.CreateTaskUseCase;
import com.ilivanilton.application.task.delete.DeleteTaskUseCase;
import com.ilivanilton.application.task.retrieve.get.GetTaskByIdUseCase;
import com.ilivanilton.application.task.retrieve.list.ListTaskUseCase;
import com.ilivanilton.domain.pagination.Pagination;
import com.ilivanilton.domain.pagination.SearchQuery;
import com.ilivanilton.infrastructure.api.TaskAPI;
import com.ilivanilton.infrastructure.task.models.*;
import com.ilivanilton.infrastructure.task.presenters.TaskApiPresenter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class TaskController implements TaskAPI {

    private final GetTaskByIdUseCase getTaskByIdUseCase;
    private final ListTaskUseCase listTaskUseCase;
    private final DeleteTaskUseCase deleteTaskUseCase;
    private final CreateTaskUseCase createTaskUseCase;

    public TaskController(
            final GetTaskByIdUseCase getTaskByIdUseCase,
            final ListTaskUseCase listTaskUseCase,
            final DeleteTaskUseCase deleteTaskUseCase,
            final CreateTaskUseCase createTaskUseCase
    ) {
        this.getTaskByIdUseCase = Objects.requireNonNull(getTaskByIdUseCase);
        this.listTaskUseCase = Objects.requireNonNull(listTaskUseCase);
        this.deleteTaskUseCase = Objects.requireNonNull(deleteTaskUseCase);
        this.createTaskUseCase = Objects.requireNonNull(createTaskUseCase);
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

    @Override
    public void deleteById(final String id) {
        deleteTaskUseCase.execute(id);
    }

    @Override
    public ResponseEntity<CreateTaskResponse> create(CreateTaskRequest input) {
        final CreateTaskCommand command = CreateTaskCommand.with(
                input.description(),input.completed());
        final CreateTaskOutput output = createTaskUseCase.execute(command);
        final var viewModel = TaskApiPresenter.present(output);
        return ResponseEntity.ok(viewModel);
    }

}
