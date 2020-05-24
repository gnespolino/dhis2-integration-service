package org.nespolino.dhis2integrationservice.api;

import lombok.Builder;
import lombok.Data;

import java.util.Collection;

@Data
@Builder
public class DataElement {
    private final String id;
    private final String name;
    private Collection<String> groups;
}
