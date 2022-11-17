/*
 * Copyright (c) 2019 VMware, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, without warranties or
 * conditions of any kind, EITHER EXPRESS OR IMPLIED.  See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.example.resourcesmonitor.service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.example.resourcesmonitor.entity.CpuInfo;
import com.example.resourcesmonitor.entity.MemoryInfo;
import com.example.resourcesmonitor.repository.CpuRepository;
import com.example.resourcesmonitor.repository.MemoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ResourcesGatheringService {
    private static final Long INITIAL_DELAY = 10_000L;
    private static final Long DELAY = 30_000L;
    private final ScheduledExecutorService scheduledExecutorService;
    private final CpuRepository cpuRepository;
    private final MemoryRepository memoryRepository;
    private final ResourceQueryService resourceQueryService;

    @Autowired
    public ResourcesGatheringService(CpuRepository cpuRepository, MemoryRepository memoryRepository,
                                     ResourceQueryService resourceQueryService) {
        this(Executors.newScheduledThreadPool(2), cpuRepository, memoryRepository, resourceQueryService);
    }

    public ResourcesGatheringService(ScheduledExecutorService scheduledExecutorService, CpuRepository cpuRepository, MemoryRepository memoryRepository, ResourceQueryService resourceQueryService) {
        this.scheduledExecutorService = scheduledExecutorService;
        this.scheduledExecutorService.scheduleWithFixedDelay(this::collectCpuInfo, INITIAL_DELAY, DELAY, TimeUnit.MILLISECONDS);
        this.scheduledExecutorService.scheduleWithFixedDelay(this::collectMemoryInfo, INITIAL_DELAY, DELAY, TimeUnit.MILLISECONDS);
        this.cpuRepository = cpuRepository;
        this.memoryRepository = memoryRepository;
        this.resourceQueryService = resourceQueryService;
    }

    private void collectCpuInfo() {
        runSafe(() -> {
            CpuInfo cpuInfo = resourceQueryService.getCurrentCpuInfo();
            log.info("CPU info collected: {}", cpuInfo);
            cpuRepository.save(cpuInfo);
            log.debug("CPU info persisted: {}", cpuInfo);
        }, "collect_cpu_info");
    }

    private void collectMemoryInfo() {
        runSafe(() -> {
            MemoryInfo memoryInfo = resourceQueryService.getCurrentMemoryInfo();
            log.info("Memory info collected: {}", memoryInfo);
            memoryRepository.save(memoryInfo);
            log.debug("Memory info persisted: {}", memoryInfo);
        }, "collect_memory_info");
    }

    private void runSafe(Runnable runnable, String operation) {
        try {
            runnable.run();
        } catch (Exception ex) {
            log.error("Error during execution of {}", operation, ex);
        }
    }
}
