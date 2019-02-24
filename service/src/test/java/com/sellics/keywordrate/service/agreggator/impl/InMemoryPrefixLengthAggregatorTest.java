package com.sellics.keywordrate.service.agreggator.impl;


import com.sellics.keywordrate.model.Keyword;
import com.sellics.keywordrate.service.amazon.protocol.AmazonKeywordResponse;
import com.sellics.keywordrate.service.amazon.protocol.AmazonKeywordResponseTestFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class InMemoryPrefixLengthAggregatorTest {

    @InjectMocks
    private InMemoryPrefixLengthAggregator aggregator;

    @Mock
    private Map<String, Integer> mockMap;

    private String keywordText = "text";
    private String prefix = "te";

    @Before
    public void setUp() {
        ReflectionTestUtils.setField(aggregator, "keywordRate", mockMap);
    }

    @Test
    public void addPopularKeywordTest() {
        Keyword keyword = new Keyword(keywordText, prefix);

        assertThat(aggregator.computeWordRelevance(Arrays.asList(keyword), keywordText))
                .isEqualTo(100);
    }
}