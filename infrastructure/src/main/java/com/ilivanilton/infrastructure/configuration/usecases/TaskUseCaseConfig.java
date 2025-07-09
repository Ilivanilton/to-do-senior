package com.ilivanilton.infrastructure.configuration.usecases;

import com.ilivanilton.application.task.create.CreateTaskUseCase;
import com.ilivanilton.application.task.create.DefaultCreateTaskUseCase;
import com.ilivanilton.application.task.delete.DefaultDeleteUseCase;
import com.ilivanilton.application.task.delete.DeleteTaskUseCase;
import com.ilivanilton.application.task.retrieve.get.DefaultGetTaskByIdUseCase;
import com.ilivanilton.application.task.retrieve.get.GetTaskByIdUseCase;
import com.ilivanilton.application.task.retrieve.list.DefaultListTaskUseCase;
import com.ilivanilton.application.task.retrieve.list.ListTaskUseCase;
import com.ilivanilton.domain.task.TaskGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskUseCaseConfig {

    private final TaskGateway taskGateway;

    public TaskUseCaseConfig(final TaskGateway taskGateway) {
        this.taskGateway = taskGateway;
    }

    @Bean
    public GetTaskByIdUseCase getTaskByIdUseCase() {
        return new DefaultGetTaskByIdUseCase(taskGateway);
    }

    @Bean
    public ListTaskUseCase listTaskUseCase() { return new DefaultListTaskUseCase(taskGateway); }

    @Bean
    public DeleteTaskUseCase deleteTaskUseCase() { return new DefaultDeleteUseCase((taskGateway)); }

    @Bean
    public CreateTaskUseCase createTaskUseCase() { return new DefaultCreateTaskUseCase(taskGateway); }

}
