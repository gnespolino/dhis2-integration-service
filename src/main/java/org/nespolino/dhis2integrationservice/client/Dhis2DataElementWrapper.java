package org.nespolino.dhis2integrationservice.client;

import lombok.Data;

import java.util.Collection;

@Data
class Dhis2DataElementWrapper {
    private final Collection<Dhis2DataElement> dataElements;
}
