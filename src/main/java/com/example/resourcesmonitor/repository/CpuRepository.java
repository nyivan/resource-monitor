package com.example.resourcesmonitor.repository;

import java.time.Instant;

import com.example.resourcesmonitor.entity.CpuInfo;
import org.springframework.data.repository.CrudRepository;

public interface CpuRepository extends CrudRepository<CpuInfo, Instant>, QueryRepository<CpuInfo> {
}
