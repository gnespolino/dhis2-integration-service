package org.nespolino.dhis2integrationservice.client;

import org.junit.jupiter.api.Test;
import org.nespolino.dhis2integrationservice.api.DataElement;
import org.nespolino.dhis2integrationservice.api.DataElementGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.Collection;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
@Import(Dhis2ClientTest.TestConfig.class)
class Dhis2ClientTest {

    @Autowired
    private Dhis2Client dhis2Client;
    @Autowired
    private Dhis2FeignClient dhis2FeignClient;

    @Test
    public void dataElementMappingIsCorrect() {
        Dhis2DataElementWrapper dhis2DataElementWrapper = Dhis2MockingSupport.dhis2DataElements();

        when(dhis2FeignClient.getDhis2DataElements())
                .thenReturn(dhis2DataElementWrapper);

        Collection<DataElement> dataElements = dhis2Client.getDataElements();
        assertThat(dataElements).hasSameSizeAs(dhis2DataElementWrapper.getDataElements());

        dhis2DataElementWrapper.getDataElements()
                .forEach(dhis2DataElement -> assertTrue(existsOneMatchInCollectionMatchingBy(dhis2DataElement, dataElements, dataElementMatcher())));
    }

    @Test
    public void dataElementGroupMappingIsCorrect() {
        Dhis2DataElementGroupWrapper dhis2DataElementGroupWrapper = Dhis2MockingSupport.dhis2DataElementGroups();

        when(dhis2FeignClient.getDhis2DataElementGroups())
                .thenReturn(dhis2DataElementGroupWrapper);

        Collection<DataElementGroup> dataElementGroups = dhis2Client.getDataElementGroups();
        assertThat(dataElementGroups).hasSameSizeAs(dhis2DataElementGroupWrapper.getDataElementGroups());

        dhis2DataElementGroupWrapper.getDataElementGroups()
                .forEach(dhis2DataElementGroup -> assertTrue(existsOneMatchInCollectionMatchingBy(dhis2DataElementGroup, dataElementGroups, dataElementGroupMatcher())));
    }

    private BiPredicate<Dhis2DataElement, DataElement> dataElementMatcher() {
        return (dhis2DataElement, dataElement) -> dhis2DataElement.getId().equals(dataElement.getId()) &&
                dhis2DataElement.getDisplayName().equals(dataElement.getName()) &&
                dhis2DataElement.getDataElementGroups().stream()
                        .map(Dhis2DataElement.Dhis2DataElementGroup::getId)
                        .collect(Collectors.toSet()).equals(dataElement.getGroups());
    }

    private BiPredicate<Dhis2DataElement.Dhis2DataElementGroup, DataElementGroup> dataElementGroupMatcher() {
        return (dhis2DataElementGroup, dataElementGroup) -> dhis2DataElementGroup.getId().equals(dataElementGroup.getId()) &&
                dhis2DataElementGroup.getDisplayName().equals(dataElementGroup.getName()) &&
                dhis2DataElementGroup.getDataElements().stream()
                        .map(Dhis2DataElement::getId)
                        .collect(Collectors.toSet()).equals(dataElementGroup.getMembers());
    }

    private <T, R> boolean existsOneMatchInCollectionMatchingBy(T element, Collection<R> collection, BiPredicate<T, R> matcher) {
        return collection.stream()
                .anyMatch(e -> matcher.test(element, e));
    }

    @Configuration
    @MockBeans(
            @MockBean(Dhis2FeignClient.class))
    static class TestConfig {

        @Bean
        Dhis2Client dhis2Client(Dhis2FeignClient dhis2FeignClient) {
            return new Dhis2Client(dhis2FeignClient, new Dhis2Mapper());
        }

    }
}
