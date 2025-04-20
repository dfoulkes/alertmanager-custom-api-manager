package uk.co.foulkes.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StreamUtils;
import uk.co.foulkes.criticalissuerestservice.CriticalIssueRestServiceApplication;
import uk.co.foulkes.utils.TestUtils;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

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
        // Load JSON from file
        ClassPathResource resource = new ClassPathResource("critical-issue-request.json");
        InputStream inputStream = resource.getInputStream();
        String requestBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        mockMvc.perform(post("/api/critical-issues")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());
    }
}