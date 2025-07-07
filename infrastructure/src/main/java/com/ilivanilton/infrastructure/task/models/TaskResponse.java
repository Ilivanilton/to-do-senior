package com.ilivanilton.infrastructure.task.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record TaskResponse(
        @JsonProperty("id") String id,
        @JsonProperty("description") String description,
        @JsonProperty("complited") Boolean active,
        @JsonProperty("created_at") Instant createdAt
) {
}