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
