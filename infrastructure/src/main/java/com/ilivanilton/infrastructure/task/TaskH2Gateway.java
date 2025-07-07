package com.ilivanilton.infrastructure.task;

import com.ilivanilton.domain.task.Task;
import com.ilivanilton.domain.task.TaskGateway;
import com.ilivanilton.infrastructure.task.persistence.TaskJpaEntity;
import com.ilivanilton.infrastructure.task.persistence.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskH2Gateway implements TaskGateway {
    private final TaskRepository repository;

    public TaskH2Gateway(final TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Task> findById(final String anId) {
        return this.repository.findById(anId)
                .map(TaskJpaEntity::toAggregate);
    }
}
