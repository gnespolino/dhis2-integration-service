package org.nespolino.dhis2integrationservice.api;

import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;
import org.nespolino.dhis2integrationservice.client.Dhis2Client;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class DataService {
    @Delegate
    private final Dhis2Client dhis2Client;

    public DataElement getDataElementById(String id) {
        return dhis2Client.getDataElements().stream()
                .filter(dataElement -> dataElement.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new DataNotFoundException(id));
    }

    public DataElementGroup getDataElementGroupById(String id) {
        return dhis2Client.getDataElementGroups().stream()
                .filter(dataElementGroup -> dataElementGroup.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new DataNotFoundException(id));
    }

    static class DataNotFoundException extends RuntimeException {
        public DataNotFoundException(String id) {
            super("Unable to get item with id " + id);
        }
    }
}
