package org.nespolino.dhis2integrationservice.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nespolino.dhis2integrationservice.cache.CacheResetService;
import org.nespolino.dhis2integrationservice.client.Dhis2Client;
import org.nespolino.dhis2integrationservice.api.DataElement;
import org.nespolino.dhis2integrationservice.api.DataElementGroup;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@RequiredArgsConstructor
@Slf4j
class CacheResetJob {

    private final CacheResetService cacheResetService;
    private final Dhis2Client dhis2Client;

    @Scheduled(fixedRateString = "${dhis2.reset-cache-every}")
    void resetCache() {

        cacheResetService.resetDataElements();
        cacheResetService.resetDataGroupElements();

        log.debug("Cache reset - fresh data will be loaded from dhis2 service upon next invocation");

        /*

        We can discuss if resetting the cache should also pre-populate it so that final service users will
        take advantage of lower response times, or lazily resetting the cache and let next invocation
        populate it. One service invocation every cache-reset event will be significantly slower than average.
        Drawback is that with few users, pre-populating the cache, will perform unneeded invocations to
        the underlying system, resulting a possible waste of resources. It depends on use cases.
        Here I suppose we prioritize user response times, so following invocations are "dummy ones" just
        for the sake of populating the cache. A solution might be introducing a cache-holder bean
        and using it directly without taking advantage of Spring's @Cacheable annotation.

         */

        Collection<DataElementGroup> dataElementGroups = dhis2Client.getDataElementGroups();
        Collection<DataElement> dataElements = dhis2Client.getDataElements();

        log.debug("loaded {} dataElements and {} dataElementGroups from source",
                dataElements.size(),
                dataElementGroups.size());
    }

}