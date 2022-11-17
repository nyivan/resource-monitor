package com.example.resourcesmonitor.entity;

import java.time.Instant;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "memory")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemoryInfo {
    @Id
    private Instant timestamp;
    private long freeMemory;
    private long totalMemory;
    private long usedMemory;

    public MemoryInfo(Instant timestamp, long freeMemory, long totalMemory, long usedMemory) {
        this.timestamp = timestamp;
        this.freeMemory = freeMemory;
        this.totalMemory = totalMemory;
        this.usedMemory = usedMemory;
    }
}
