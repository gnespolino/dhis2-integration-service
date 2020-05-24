package org.nespolino.dhis2integrationservice.api;

import lombok.Builder;
import lombok.Data;

import java.util.Collection;

@Data
@Builder
public class DataElementGroup {
    private final String id;
    private final String name;
    private final Collection<String> members;
}
