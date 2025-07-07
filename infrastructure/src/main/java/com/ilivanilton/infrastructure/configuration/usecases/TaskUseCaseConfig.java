package com.ilivanilton.infrastructure.configuration.usecases;

import com.ilivanilton.application.task.retrieve.get.DefaultGetTaskByIdUseCase;
import com.ilivanilton.application.task.retrieve.get.GetTaskByIdUseCase;
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

}
