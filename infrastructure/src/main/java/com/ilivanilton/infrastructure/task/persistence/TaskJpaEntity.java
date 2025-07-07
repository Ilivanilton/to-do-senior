package com.ilivanilton.infrastructure.task.persistence;


import com.ilivanilton.domain.task.Task;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "task")
public class TaskJpaEntity {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "description",nullable = false, length = 4000)
    private String description;

    @Column(name = "completed", nullable = false)
    private boolean completed;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant updatedAt;

    public TaskJpaEntity() {}

    private TaskJpaEntity(
            final String id,
            final String description,
            final boolean completed,
            final Instant createdAt,
            final Instant updatedAt
    ) {
        this.id = id;
        this.description = description;
        this.completed = completed;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static TaskJpaEntity from(final Task aTask) {
        return new TaskJpaEntity(
                aTask.getId(),
                aTask.getDescription(),
                aTask.isCompleted(),
                aTask.getCreatedAt(),
                aTask.getUpdatedAt()
        );
    }

    public Task toAggregate() {
        return Task.with(
                getId(),
                getDescription(),
                getCompleted(),
                getCreatedAt(),
                getUpdatedAt()
        );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

}