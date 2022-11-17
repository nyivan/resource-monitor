package com.example.resourcesmonitor.service;

import java.time.Instant;
import java.util.Collection;

import com.example.resourcesmonitor.entity.CpuInfo;
import com.example.resourcesmonitor.entity.MemoryInfo;

public interface ResourceQueryService {
    Collection<CpuInfo> getCpuInfo(Instant from, Instant to);

    CpuInfo getCurrentCpuInfo();

    Collection<MemoryInfo> getMemoryInfo(Instant from, Instant to);

    MemoryInfo getCurrentMemoryInfo();
}
