package com.sellics.keywordrate.service.evaluation.impl;

import com.sellics.keywordrate.EvaluateResponse;
import com.sellics.keywordrate.model.Keyword;
import com.sellics.keywordrate.service.agreggator.PrefixLengthAggregator;
import com.sellics.keywordrate.service.amazon.AmazonKeywordRequester;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EvaluationServiceImplTest {

    @InjectMocks
    private EvaluationServiceImpl service;

    @Mock
    private AmazonKeywordRequester requester;

    @Mock
    private PrefixLengthAggregator aggregator;

    private String text = "test";
    private List<String> prefixes = Arrays.asList("t", "te", "tes", "test");
    private Integer score = 77;

    @Before
    public void setUp() {
        List<Keyword> keywords = Arrays.asList(new Keyword());
        when(requester.requestKeywords(argThat(strings -> strings.containsAll(prefixes)))).thenReturn(keywords);
        when(aggregator.computeWordRelevance(eq(keywords), eq(text))).thenReturn(score);
    }

    @Test
    public void requestPerLetterTest() {
        assertThat(service.evaluate(text))
                .extracting(EvaluateResponse::getScore)
                .isEqualTo(score);
    }
}