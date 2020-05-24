package org.nespolino.dhis2integrationservice.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiController.API_PATH)
class ApiController {

    static final String API_PATH = "api";
    static final String DATA_ELEMENTS_PATH = "dataElements";
    static final String DATA_ELEMENT_GROUPS_PATH = "dataElementGroups";

    private final DataService dataService;

    @GetMapping(DATA_ELEMENTS_PATH)
    Collection<DataElement> getDataElements() {
        return dataService.getDataElements();
    }

    @GetMapping(DATA_ELEMENTS_PATH + "/{id}")
    DataElement getDataElementById(@PathVariable String id) {
        return dataService.getDataElementById(id);
    }

    @GetMapping(DATA_ELEMENT_GROUPS_PATH)
    Collection<DataElementGroup> getDataElementGroups() {
        return dataService.getDataElementGroups();
    }

    @GetMapping(DATA_ELEMENT_GROUPS_PATH + "/{id}")
    DataElementGroup getDataElementGroupById(@PathVariable String id) {
        return dataService.getDataElementGroupById(id);
    }

}
