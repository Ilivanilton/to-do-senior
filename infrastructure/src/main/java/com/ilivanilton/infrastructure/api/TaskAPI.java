package com.ilivanilton.infrastructure.api;


import com.ilivanilton.domain.pagination.Pagination;
import com.ilivanilton.infrastructure.task.models.TaskListResponse;
import com.ilivanilton.infrastructure.task.models.TaskResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping(value = "v1/tasks")
public interface TaskAPI {

    @GetMapping(
            value = "{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    TaskResponse getById(@PathVariable(name = "id") String id);

    @GetMapping
    Pagination<TaskListResponse> listAll(
            @RequestParam(name = "search", required = false, defaultValue = "") final String search,
            @RequestParam(name = "page", required = false, defaultValue = "0") final int page,
            @RequestParam(name = "perPage", required = false, defaultValue = "10") final int perPage,
            @RequestParam(name = "sort", required = false, defaultValue = "description") final String sort,
            @RequestParam(name = "dir", required = false, defaultValue = "asc") final String direction
    );
}
