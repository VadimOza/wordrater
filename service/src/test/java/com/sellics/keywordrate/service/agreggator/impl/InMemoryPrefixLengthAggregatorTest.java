package com.sellics.keywordrate.service.agreggator.impl;


import com.sellics.keywordrate.model.Keyword;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class InMemoryPrefixLengthAggregatorTest {

    @InjectMocks
    private InMemoryPrefixLengthAggregator aggregator;

    private String keywordText = "text";
    private String prefix = "t";

    @Test
    public void addPopularKeywordTest() {
        Keyword keyword = new Keyword(keywordText, prefix);

        assertThat(aggregator.computeWordRelevance(Arrays.asList(keyword), keywordText))
                .isEqualTo(100);
    }
}