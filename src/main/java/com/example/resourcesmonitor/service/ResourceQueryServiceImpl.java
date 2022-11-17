package com.example.resourcesmonitor.service;

import java.time.Instant;
import java.util.Collection;

import com.example.resourcesmonitor.entity.CpuInfo;
import com.example.resourcesmonitor.entity.MemoryInfo;
import com.example.resourcesmonitor.repository.QueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceQueryServiceImpl implements ResourceQueryService {
    private final QueryRepository<CpuInfo> cpuRepository;
    private final QueryRepository<MemoryInfo> memoryRepository;

    @Autowired
    public ResourceQueryServiceImpl(QueryRepository<CpuInfo> cpuRepository, QueryRepository<MemoryInfo> memoryRepository) {
        this.cpuRepository = cpuRepository;
        this.memoryRepository = memoryRepository;
    }

    @Override
    public Collection<CpuInfo> getCpuInfo(Instant from, Instant to) {
        validateParams(from, to);
        return cpuRepository.findByTimestampBetween(from, to);
    }

    @Override
    public CpuInfo getCurrentCpuInfo() {
        var bean =
                (com.sun.management.OperatingSystemMXBean)
                        java.lang.management.ManagementFactory.getOperatingSystemMXBean();
        return new CpuInfo(Instant.now(), bean.getAvailableProcessors(), bean.getProcessCpuLoad(), bean.getProcessCpuTime(), bean.getSystemCpuLoad());
    }

    @Override
    public MemoryInfo getCurrentMemoryInfo() {
        var bean =
                (com.sun.management.OperatingSystemMXBean)
                        java.lang.management.ManagementFactory.getOperatingSystemMXBean();
        long totalSize = bean.getTotalPhysicalMemorySize();
        long freeSize = bean.getFreePhysicalMemorySize();
        return new MemoryInfo(Instant.now(), freeSize, totalSize, totalSize - freeSize);
    }

    @Override
    public Collection<MemoryInfo> getMemoryInfo(Instant from, Instant to) {
        validateParams(from, to);
        return memoryRepository.findByTimestampBetween(from, to);
    }

    private void validateParams(Instant from, Instant to) {
        if (from == null || to == null) {
            throw new IllegalArgumentException("Please provide both from and to params");
        }
        if (from.isAfter(to)) {
            throw new IllegalArgumentException("from must be before to param");
        }
    }
}
