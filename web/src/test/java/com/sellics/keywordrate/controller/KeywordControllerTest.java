package com.sellics.keywordrate.controller;

import com.sellics.keywordrate.EvaluateResponse;
import com.sellics.keywordrate.service.evaluation.api.EvaluationService;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;

import static com.sellics.keywordrate.controller.KeywordController.BASE_URL;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class KeywordControllerTest extends BaseTestController {

    @Mock
    private EvaluationService service;

    @Test
    public void evaluateTest() throws Exception {
        String param = "keyword";
        EvaluateResponse responce = EvaluateResponse.builder()
                .keyword(param)
                .score(45)
                .build();

        when(service.evaluate(eq(param))).thenReturn(responce);

        this.client.perform(RestDocumentationRequestBuilders.get(BASE_URL)
                .param("keyword", param))
                .andExpect(status().isOk())
                .andExpect(content().json(map(responce)));
    }

    @Override
    protected Object controllerToTest() {
        return new KeywordController(service);
    }
}