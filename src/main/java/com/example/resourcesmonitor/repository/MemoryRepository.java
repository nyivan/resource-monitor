package com.example.resourcesmonitor.repository;

import java.time.Instant;

import com.example.resourcesmonitor.entity.MemoryInfo;
import org.springframework.data.repository.CrudRepository;

public interface MemoryRepository extends CrudRepository<MemoryInfo, Instant>, QueryRepository<MemoryInfo> {
}
