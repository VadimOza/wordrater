package com.sellics.keywordrate.service.evaluation.impl;

import com.sellics.keywordrate.model.Keyword;
import com.sellics.keywordrate.service.amazon.AmazonKeywordRequester;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EvaluationServiceImplTest {

    @InjectMocks
    private EvaluationServiceImpl service;

    @Mock
    private AmazonKeywordRequester requester;

    private String text = "test";
    private List<String> prefixes = Arrays.asList("t", "te", "tes", "test");

    @Before
    public void setUp() {
        List<Keyword> keywords = Arrays.asList(new Keyword());
        when(requester.requestKeywords(prefixes)).thenReturn(keywords);
    }

    @Test
    public void requestPerLetterTest() {
        service.evaluate(text);

        verify(requester).requestKeywords(argThat(str -> prefixes.containsAll(str)));
    }
}