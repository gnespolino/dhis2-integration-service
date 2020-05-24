package org.nespolino.dhis2integrationservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "dhis2FeignClient", url = "${dhis2.url}")
interface Dhis2FeignClient {

    @GetMapping("/api/29/dataElements.json?paging=false&fields=id,displayName,dataElementGroups[id]")
    Dhis2DataElementWrapper getDhis2DataElements();

    @GetMapping("/api/29/dataElementGroups.json?paging=false&fields=id,displayName,dataElements[id]")
    Dhis2DataElementGroupWrapper getDhis2DataElementGroups();

}
