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
