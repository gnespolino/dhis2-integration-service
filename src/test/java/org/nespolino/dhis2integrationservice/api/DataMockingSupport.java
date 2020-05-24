package org.nespolino.dhis2integrationservice.api;

import lombok.experimental.UtilityClass;

import java.util.Collection;
import java.util.Set;

@UtilityClass
class DataMockingSupport {

    static final String A_DATA_ELEMENT = "de-1";
    static final String A_DATA_ELEMENT_GROUP = "deg-1";

    Collection<DataElement> validDataElements() {
        return Set.of(
                DataElement.builder()
                        .id(A_DATA_ELEMENT)
                        .name("de-n1")
                        .groups(Set.of("g1", "g2"))
                        .build(),
                DataElement.builder()
                        .id("de-2")
                        .name("de-n2")
                        .groups(Set.of("g1", "g3"))
                        .build());

    }

    Collection<DataElementGroup> validDataElementGroups() {
        return Set.of(
                DataElementGroup.builder()
                        .id(A_DATA_ELEMENT_GROUP)
                        .name("deg-n1")
                        .members(Set.of("m1", "m2"))
                        .build(),
                DataElementGroup.builder()
                        .id("deg-2")
                        .name("deg-n2")
                        .members(Set.of("m1", "m3"))
                        .build());
    }
}
