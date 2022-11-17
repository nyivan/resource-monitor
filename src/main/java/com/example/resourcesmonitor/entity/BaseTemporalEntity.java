package com.example.resourcesmonitor.entity;

import java.time.Instant;
import javax.persistence.Id;

public abstract class BaseTemporalEntity {
    @Id
    private Instant timestamp;

    protected BaseTemporalEntity(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
