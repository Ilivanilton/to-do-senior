package com.ilivanilton.domain.task;

import com.ilivanilton.domain.pagination.Pagination;
import com.ilivanilton.domain.pagination.SearchQuery;

import java.util.Optional;

public interface TaskGateway {
    Optional<Task> findById(TaskID id);
    Pagination<Task> findAll(SearchQuery aQuery);
    void deleteById(TaskID id);
    Task create(Task task);
    Task update(Task task);
}
