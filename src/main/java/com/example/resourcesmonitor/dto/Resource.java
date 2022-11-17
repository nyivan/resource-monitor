package com.example.resourcesmonitor.dto;

import java.util.Collection;

import lombok.Data;

@Data
public class Resource<T> {
    private T current;
    private Collection<T> historical;

    public Resource(T current, Collection<T> historical) {
        this.current = current;
        this.historical = historical;
    }
}
