package org.nespolino.dhis2integrationservice.client;

import lombok.RequiredArgsConstructor;
import org.nespolino.dhis2integrationservice.api.DataElement;
import org.nespolino.dhis2integrationservice.api.DataElementGroup;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Collection;

import static org.nespolino.dhis2integrationservice.cache.CacheResetService.DATA_ELEMENTS_CACHE_NAME;
import static org.nespolino.dhis2integrationservice.cache.CacheResetService.DATA_ELEMENT_GROUPS_CACHE_NAME;

@Component
@RequiredArgsConstructor
public class Dhis2Client {

    private final Dhis2FeignClient dhis2FeignClient;
    private final Dhis2Mapper dhis2Mapper;

    @Cacheable(DATA_ELEMENTS_CACHE_NAME)
    public Collection<DataElement> getDataElements() {
        return dhis2Mapper.toDataElements(dhis2FeignClient.getDhis2DataElements());
    }

    @Cacheable(DATA_ELEMENT_GROUPS_CACHE_NAME)
    public Collection<DataElementGroup> getDataElementGroups() {
        return dhis2Mapper.toDataElementGroups(dhis2FeignClient.getDhis2DataElementGroups());
    }

}
