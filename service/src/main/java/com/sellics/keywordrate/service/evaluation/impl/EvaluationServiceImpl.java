package com.sellics.keywordrate.service.evaluation.impl;

import com.sellics.keywordrate.EvaluateResponse;
import com.sellics.keywordrate.model.Keyword;
import com.sellics.keywordrate.service.agreggator.PrefixLengthAggregator;
import com.sellics.keywordrate.service.agreggator.impl.InMemoryPrefixLengthAggregator;
import com.sellics.keywordrate.service.amazon.KeywordRequester;
import com.sellics.keywordrate.service.amazon.protocol.AmazonKeywordResponse;
import com.sellics.keywordrate.service.evaluation.api.EvaluationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EvaluationServiceImpl implements EvaluationService {

    private final KeywordRequester requester;

    @Override
    public EvaluateResponse evaluate(String keyword) {
        PrefixLengthAggregator aggregator = new InMemoryPrefixLengthAggregator();

        List<Keyword> collect = requester.requestKeywords(getPossiblePrefixes(keyword));

        Integer score = aggregator.computeWordRelevance(collect, keyword);

        return EvaluateResponse.builder()
                .keyword(keyword)
                .score(score)
                .build();
    }

    private Set<String> getPossiblePrefixes(String keyword) {
        Set<String> prefixes = new HashSet<>();

        for (int i = 1; i <= keyword.length(); i++) {
            prefixes.add(keyword.substring(0, i));
        }
        return prefixes;
    }
}
