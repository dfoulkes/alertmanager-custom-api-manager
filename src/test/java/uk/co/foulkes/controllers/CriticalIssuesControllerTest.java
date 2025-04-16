package uk.co.foulkes.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import uk.co.foulkes.criticalissuerestservice.CriticalIssueRestServiceApplication;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CriticalIssuesController.class)
@ContextConfiguration(classes = CriticalIssueRestServiceApplication.class)
class CriticalIssuesControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ApplicationEventPublisher eventPublisher;

    @Test
    void testCriticalIssuesEndpoint() throws Exception {
        // Perform a POST request to the endpoint
        mockMvc.perform(post("/api/critical-issues"))
                .andExpect(status().isOk());

        // Verify that the event was published
        Mockito.verify(eventPublisher).publishEvent(Mockito.any());
    }

}