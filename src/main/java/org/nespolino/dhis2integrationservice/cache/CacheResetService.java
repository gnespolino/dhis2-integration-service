package org.nespolino.dhis2integrationservice.cache;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Component;

@Component
public class CacheResetService {

    public final static String DATA_ELEMENTS_CACHE_NAME = "data-elements";
    public final static String DATA_ELEMENT_GROUPS_CACHE_NAME = "data-element-groups";

    @CacheEvict(value = DATA_ELEMENTS_CACHE_NAME, allEntries = true)
    public void resetDataElements() {}

    @CacheEvict(value = DATA_ELEMENT_GROUPS_CACHE_NAME, allEntries = true)
    public void resetDataGroupElements() {}

}
