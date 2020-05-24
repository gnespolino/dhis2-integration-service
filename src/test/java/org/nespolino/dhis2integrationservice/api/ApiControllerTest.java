package org.nespolino.dhis2integrationservice.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.nespolino.dhis2integrationservice.client.Dhis2Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.nespolino.dhis2integrationservice.api.ApiController.API_PATH;
import static org.nespolino.dhis2integrationservice.api.ApiController.DATA_ELEMENTS_PATH;
import static org.nespolino.dhis2integrationservice.api.ApiController.DATA_ELEMENT_GROUPS_PATH;
import static org.nespolino.dhis2integrationservice.api.DataMockingSupport.A_DATA_ELEMENT;
import static org.nespolino.dhis2integrationservice.api.DataMockingSupport.A_DATA_ELEMENT_GROUP;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Dhis2Client dhis2Client;

    @Test
    void testAuthIsRequired() throws Exception {
        mockMvc.perform(get("/" + API_PATH + "/" + DATA_ELEMENTS_PATH))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "${dhis.username}", password = "${dhis2.password")
    void testAuthIsOk() throws Exception {
        when(dhis2Client.getDataElements()).thenReturn(DataMockingSupport.validDataElements());

        mockMvc.perform(get("/" + API_PATH + "/" + DATA_ELEMENTS_PATH))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "${dhis.username}", password = "${dhis2.password")
    void testGetDataElementGroupByIdIsOk() throws Exception {
        when(dhis2Client.getDataElementGroups()).thenReturn(DataMockingSupport.validDataElementGroups());

        MvcResult mvcResult = mockMvc.perform(get("/" + API_PATH + "/" + DATA_ELEMENT_GROUPS_PATH + "/" + A_DATA_ELEMENT_GROUP))
                .andExpect(status().isOk())
                .andReturn();

        DataElementGroup responseContent = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), DataElementGroup.class);

        assertEquals(responseContent.getId(), A_DATA_ELEMENT_GROUP);
    }

    @Test
    @WithMockUser(username = "${dhis.username}", password = "${dhis2.password")
    void testGetDataElementByIdIsOk() throws Exception {
        when(dhis2Client.getDataElements()).thenReturn(DataMockingSupport.validDataElements());

        MvcResult mvcResult = mockMvc.perform(get("/" + API_PATH + "/" + DATA_ELEMENTS_PATH + "/" + A_DATA_ELEMENT))
                .andExpect(status().isOk())
                .andReturn();

        DataElement responseContent = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), DataElement.class);

        assertEquals(responseContent.getId(), A_DATA_ELEMENT);
    }

}
