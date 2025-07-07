package com.ilivanilton.infrastructure.api;


import com.ilivanilton.infrastructure.task.models.TaskResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "v1/tasks")
public interface TaskAPI {

    @GetMapping(
            value = "{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    TaskResponse getById(@PathVariable(name = "id") String id);
}
