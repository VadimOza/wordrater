package com.sellics.keywordrate.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
public abstract class BaseTestController {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Rule
    public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

    protected MockMvc client;

    @Before
    public void setUp() {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        this.client = MockMvcBuilders.standaloneSetup(controllerToTest())
                .apply(documentationConfiguration(this.restDocumentation).uris().withHost("sellics.io"))
                .build();

        configure();
    }

    protected void configure() {

    }

    protected void badRequest(String url, Object request) throws Exception {
        this.client.perform(RestDocumentationRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(map(request)))
                .andExpect(status().isBadRequest());
    }

    protected String map(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }

    protected abstract Object controllerToTest();
}
