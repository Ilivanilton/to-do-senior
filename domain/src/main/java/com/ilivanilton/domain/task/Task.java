package com.ilivanilton.domain.task;

import com.ilivanilton.domain.AggregateRoot;
import com.ilivanilton.domain.validation.ValidationHandler;

import java.time.Instant;
import java.util.Objects;

public class Task extends AggregateRoot<TaskID> {

    private String description;
    private boolean completed;
    private Instant createdAt;
    private Instant updatedAt;

    private Task(final TaskID anId, final String aDescription,
                 final boolean aCompleted, final Instant aCreatedAt,
                 final Instant aUpdatedAt
    ){
        super(anId);
        description = Objects.requireNonNull(aDescription,"'description' must not be null");
        completed = Objects.requireNonNull(aCompleted,"'Completed' must not be null");
        createdAt = Objects.requireNonNull(aCreatedAt,"'CreatedAt' must not be null");
        updatedAt = Objects.requireNonNull(aUpdatedAt,"'updatedAt' must not be null");
    }

    public static Task create(final String aDescription, final boolean aCompleted) {
        final TaskID id = TaskID.unique();
        final Instant now = Instant.now();
        return new Task(id,aDescription,aCompleted,now,now);
    }

    public static Task with(
            final TaskID anId,
            final String aDescription,
            final boolean completed,
            final Instant createdAt,
            final Instant updatedAt
    ){
        return new Task(anId, aDescription, completed, createdAt, updatedAt);
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

    @Override
    public void validate(ValidationHandler handler) { }

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
