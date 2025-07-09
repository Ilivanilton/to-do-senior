package com.ilivanilton.infrastructure.task.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record CreateTaskResponse(
        @JsonProperty("id") String id,
        @JsonProperty("description") String description,
        @JsonProperty("completed") Boolean completed,
        @JsonProperty("created_at") Instant createdAt
) { }
