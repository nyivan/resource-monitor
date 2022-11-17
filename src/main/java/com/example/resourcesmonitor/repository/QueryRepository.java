package com.example.resourcesmonitor.repository;

import java.time.Instant;
import java.util.Collection;

public interface QueryRepository<T> {
    Collection<T> findByTimestampBetween(Instant from, Instant to);
}
