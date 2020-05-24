package org.nespolino.dhis2integrationservice.client;

import org.nespolino.dhis2integrationservice.api.DataElement;
import org.nespolino.dhis2integrationservice.api.DataElementGroup;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
class Dhis2Mapper {

    Collection<DataElement> toDataElements(Dhis2DataElementWrapper dhis2DataElementWrapper) {
        return dhis2DataElementWrapper.getDataElements().stream()
                .map(this::toDataElement)
                .collect(Collectors.toSet());
    }

    Collection<DataElementGroup> toDataElementGroups(Dhis2DataElementGroupWrapper dhis2DataElementGroupWrapper) {
        return dhis2DataElementGroupWrapper.getDataElementGroups().stream()
                .map(this::toDataElementGroup)
                .collect(Collectors.toSet());
    }

    private DataElementGroup toDataElementGroup(Dhis2DataElement.Dhis2DataElementGroup dhis2DataElementGroup) {
        return DataElementGroup.builder()
                .id(dhis2DataElementGroup.getId())
                .name(dhis2DataElementGroup.getDisplayName())
                .members(toMembers(dhis2DataElementGroup.getDataElements()))
                .build();
    }

    private Collection<String> toMembers(Collection<Dhis2DataElement> dataElements) {
        return dataElements.stream()
                .map(Dhis2DataElement::getId)
                .collect(Collectors.toSet());
    }

    private DataElement toDataElement(Dhis2DataElement dhis2DataElement) {
        return DataElement.builder()
                .id(dhis2DataElement.getId())
                .name(dhis2DataElement.getDisplayName())
                .groups(toGroups(dhis2DataElement.getDataElementGroups()))
                .build();
    }

    private Collection<String> toGroups(Collection<Dhis2DataElement.Dhis2DataElementGroup> dataElementGroups) {
        return dataElementGroups.stream()
                .map(Dhis2DataElement.Dhis2DataElementGroup::getId)
                .collect(Collectors.toSet());
    }

}
