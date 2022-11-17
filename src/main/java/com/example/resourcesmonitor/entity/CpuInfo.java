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
@Table(name = "cpu")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class CpuInfo {

    @Id
    private Instant timestamp;
    private int availableProcessors;
    private double processLoad;
    private long processTime;
    private double systemLoad;

    public CpuInfo(Instant timestamp, int availableProcessors, double processLoad, long processTime, double systemLoad) {
        this.timestamp = timestamp;
        this.availableProcessors = availableProcessors;
        this.processLoad = processLoad;
        this.processTime = processTime;
        this.systemLoad = systemLoad;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public int getAvailableProcessors() {
        return availableProcessors;
    }

    public void setAvailableProcessors(int availableProcessors) {
        this.availableProcessors = availableProcessors;
    }

    public double getProcessLoad() {
        return processLoad;
    }

    public void setProcessLoad(double processLoad) {
        this.processLoad = processLoad;
    }

    public long getProcessTime() {
        return processTime;
    }

    public void setProcessTime(long processTime) {
        this.processTime = processTime;
    }

    public double getSystemLoad() {
        return systemLoad;
    }

    public void setSystemLoad(double systemLoad) {
        this.systemLoad = systemLoad;
    }
}
