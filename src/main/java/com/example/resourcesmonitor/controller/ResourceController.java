package com.example.resourcesmonitor.controller;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;

import com.example.resourcesmonitor.dto.Resource;
import com.example.resourcesmonitor.entity.CpuInfo;
import com.example.resourcesmonitor.entity.MemoryInfo;
import com.example.resourcesmonitor.service.ResourceQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ResourceController {

    private final ResourceQueryService queryService;

    @Autowired
    public ResourceController(ResourceQueryService queryService) {
        this.queryService = queryService;
    }

    @GetMapping("/cpu")
    public ResponseEntity<Resource<CpuInfo>> getCpuInfo() {
        CpuInfo current = queryService.getCurrentCpuInfo();
        Instant now = Instant.now();
        Collection<CpuInfo> historical = queryService.getCpuInfo(now.minus(24, ChronoUnit.HOURS), now);
        Resource<CpuInfo> cpuResource = new Resource<>(current, historical);
        return ResponseEntity.ok(cpuResource);
    }

    @GetMapping("/memory")
    public ResponseEntity<Resource<MemoryInfo>> getMemoryInfo() {
        MemoryInfo current = queryService.getCurrentMemoryInfo();
        Instant now = Instant.now();
        Collection<MemoryInfo> historical = queryService.getMemoryInfo(now.minus(24, ChronoUnit.HOURS), now);
        return ResponseEntity.ok(new Resource<>(current, historical));
    }
}
