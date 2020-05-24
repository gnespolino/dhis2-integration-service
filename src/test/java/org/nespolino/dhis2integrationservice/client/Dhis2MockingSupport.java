package org.nespolino.dhis2integrationservice.client;

import lombok.experimental.UtilityClass;

import java.util.Collection;
import java.util.Set;

@UtilityClass
class Dhis2MockingSupport {

    Dhis2DataElementWrapper dhis2DataElements() {
        return new Dhis2DataElementWrapper(validDhis2DataElements());
    }

    private Collection<Dhis2DataElement> validDhis2DataElements() {
        return Set.of(
                new Dhis2DataElement("de-1", "de-dn1", Set.of(
                        new Dhis2DataElement.Dhis2DataElementGroup("deg-1", null, null),
                        new Dhis2DataElement.Dhis2DataElementGroup("deg-2", null, null))),
                new Dhis2DataElement("de-2", "de-dn2", Set.of(
                        new Dhis2DataElement.Dhis2DataElementGroup("deg-1", null, null),
                        new Dhis2DataElement.Dhis2DataElementGroup("deg-3", null, null))));
    }

    Dhis2DataElementGroupWrapper dhis2DataElementGroups() {
        return new Dhis2DataElementGroupWrapper(validDhis2DataElementGroups());
    }

    private Collection<Dhis2DataElement.Dhis2DataElementGroup> validDhis2DataElementGroups() {
        return Set.of(
                new Dhis2DataElement.Dhis2DataElementGroup("deg-1", "deg-dn1", Set.of(
                        new Dhis2DataElement("de-1", null, null),
                        new Dhis2DataElement("de-2", null, null))),
                new Dhis2DataElement.Dhis2DataElementGroup("deg-2", "deg-dn2", Set.of(
                        new Dhis2DataElement("de-1", null, null),
                        new Dhis2DataElement("de-3", null, null))));
    }
}
