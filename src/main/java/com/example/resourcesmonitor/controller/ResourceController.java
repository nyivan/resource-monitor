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

package com.example.resourcesmonitor.controller;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;

import com.example.resourcesmonitor.dto.Resource;
import com.example.resourcesmonitor.entity.CpuInfo;
import com.example.resourcesmonitor.entity.MemoryInfo;
import com.example.resourcesmonitor.service.ResourceQueryService;
import com.example.resourcesmonitor.service.ResourcesGatheringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ResourceController {

    private final ResourceQueryService queryService;
    private final ResourcesGatheringService gatheringService;

    @Autowired
    public ResourceController(ResourceQueryService queryService, ResourcesGatheringService gatheringService) {
        this.queryService = queryService;
        this.gatheringService = gatheringService;
    }

    @GetMapping("/cpu")
    public ResponseEntity<Resource<CpuInfo>> getCpuInfo() {
        CpuInfo current = gatheringService.currentCpuInfo();
        Instant now = Instant.now();
        Collection<CpuInfo> historical = queryService.getCpuInfo(now.minus(24, ChronoUnit.HOURS), now);
        Resource<CpuInfo> cpuResource = new Resource<>(current, historical);
        return ResponseEntity.ok(cpuResource);
    }

    @GetMapping("/memory")
    public ResponseEntity<Resource<MemoryInfo>> getMemoryInfo() {
        MemoryInfo current = gatheringService.currentMemoryInfo();
        Instant now = Instant.now();
        Collection<MemoryInfo> historical = queryService.getMemoryInfo(now.minus(24, ChronoUnit.HOURS), now);
        return ResponseEntity.ok(new Resource<>(current, historical));
    }
}
