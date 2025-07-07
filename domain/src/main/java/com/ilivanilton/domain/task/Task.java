package com.ilivanilton.domain.task;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class Task {

    private String id;
    private String description;
    private boolean completed;
    private Instant createdAt;
    private Instant updatedAt;

    private Task(final String anId, final String aDescription,
                 final boolean aCompleted, final Instant aCreatedAt,
                 final Instant aUpdatedAt
    ){
        id = Objects.requireNonNull(anId, "id cannot be null");
        description = Objects.requireNonNull(aDescription,"'description' must not be null");
        completed = Objects.requireNonNull(aCompleted,"'Completed' must not be null");
        createdAt = Objects.requireNonNull(aCreatedAt,"'CreatedAt' must not be null");
        updatedAt = Objects.requireNonNull(aUpdatedAt,"'updatedAt' must not be null");
    }

    public static Task create(final String aDescription, final boolean aCompleted) {
        final String id = UUID.randomUUID().toString();
        final Instant now = Instant.now();
        return new Task(id,aDescription,aCompleted,now,now);
    }

    public Task conclude() {
        this.completed = true;
        this.updatedAt = Instant.now();
        return this;
    }

    public Task reopen(){
        this.completed = false;
        this.updatedAt = Instant.now();
        return this;
    }

    public Task update(final String aDescription, final boolean aCompleted) {
        this.description = aDescription;
        this.completed = aCompleted;
        this.updatedAt = Instant.now();
        return this;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        final Task task = (Task) o;
        return completed == task.completed && Objects.equals(id, task.id) &&
                Objects.equals(description, task.description) &&
                Objects.equals(createdAt, task.createdAt) &&
                Objects.equals(updatedAt, task.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, completed, createdAt, updatedAt);
    }

}
