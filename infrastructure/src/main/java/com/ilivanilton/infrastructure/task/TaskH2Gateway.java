package com.ilivanilton.infrastructure.task;

import com.ilivanilton.domain.pagination.Pagination;
import com.ilivanilton.domain.pagination.SearchQuery;
import com.ilivanilton.domain.task.Task;
import com.ilivanilton.domain.task.TaskGateway;
import com.ilivanilton.domain.task.TaskID;
import com.ilivanilton.infrastructure.task.persistence.TaskJpaEntity;
import com.ilivanilton.infrastructure.task.persistence.TaskRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.ilivanilton.infrastructure.utils.SpecificationUtils.like;

@Service
public class TaskH2Gateway implements TaskGateway {
    private final TaskRepository repository;

    public TaskH2Gateway(final TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Task> findById(final TaskID anId) {
        return this.repository.findById(anId.getValue())
                .map(TaskJpaEntity::toAggregate);
    }

    @Override
    public Pagination<Task> findAll(final SearchQuery aQuery) {
        // Paginação
        final var page = PageRequest.of(
                aQuery.page(),
                aQuery.perPage(),
                Sort.by(Sort.Direction.fromString(aQuery.direction()), aQuery.sort())
        );

        // Busca dinamica pelo criterio terms (name ou description)
        final var specifications = Optional.ofNullable(aQuery.terms())
                .filter(str -> !str.isBlank())
                .map(str -> {
                    final Specification<TaskJpaEntity> descriptionLike = like("description", str);
                    return descriptionLike;
                })
                .orElse(null);

        final var pageResult =
                this.repository.findAll(Specification.where(specifications), page);

        return new Pagination<>(
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalElements(),
                pageResult.map(TaskJpaEntity::toAggregate).toList()
        );
    }

    @Override
    public void deleteById(TaskID id) {
        final String anId = id.getValue();
        if (repository.existsById(anId)) {
            repository.deleteById(anId);
        }
    }

    @Override
    public Task create(Task task) {
        final TaskJpaEntity jpaEntity = TaskJpaEntity.from(task);
        final Task newTask = repository.save(jpaEntity).toAggregate();
        return newTask;
    }

}
