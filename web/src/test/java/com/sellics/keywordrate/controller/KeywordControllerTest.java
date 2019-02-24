package com.sellics.keywordrate.controller;

import com.sellics.keywordrate.EvaluateResponse;
import com.sellics.keywordrate.service.evaluation.api.EvaluationService;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;

import static com.sellics.keywordrate.controller.KeywordController.BASE_URL;
import static com.sellics.keywordrate.controller.KeywordController.Parameter.KEYWORD;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
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
                .andExpect(content().json(map(responce)))
                .andDo(document("evaluate",
                        preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()),
                        requestParameters(
                                parameterWithName(KEYWORD).description("Keyword to score")
                        ),
                        responseFields(
                                fieldWithPath("keyword").description("Evaluated keyword"),
                                fieldWithPath("score").description("Score of keyword")
                        )
                ));
    }

    @Override
    protected Object controllerToTest() {
        return new KeywordController(service);
    }
}