package org.nespolino.dhis2integrationservice.client;

import lombok.Data;

import java.util.Collection;

@Data
class Dhis2DataElement {
    private final String id;
    private final String displayName;
    private final Collection<Dhis2DataElementGroup> dataElementGroups;

    @Data
    static class Dhis2DataElementGroup {
        private final String id;
        private final String displayName;
        private final Collection<Dhis2DataElement> dataElements;
    }
}
