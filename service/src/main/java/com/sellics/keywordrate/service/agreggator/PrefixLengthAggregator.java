package com.sellics.keywordrate.service.agreggator;

import com.sellics.keywordrate.model.Keyword;

import java.util.Collection;

public interface PrefixLengthAggregator {
    Integer computeWordRelevance(Collection<Keyword> keywords, String text);
}
