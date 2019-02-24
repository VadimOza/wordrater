package com.sellics.keywordrate.service.agreggator;

import com.sellics.keywordrate.model.Keyword;
import com.sellics.keywordrate.service.amazon.protocol.AmazonKeywordResponse;

import java.util.Collection;

public interface PrefixLengthAggregator {
    Integer computeWordRelevance(Collection<Keyword> keywords, String text);
}
