package com.ilivanilton.domain.task;

import java.util.Optional;

public interface TaskGateway {
    Optional<Task> findById(String id);
}
