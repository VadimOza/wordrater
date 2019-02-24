package com.sellics.keywordrate.service.agreggator.impl;

import com.sellics.keywordrate.model.Keyword;
import com.sellics.keywordrate.service.agreggator.PrefixLengthAggregator;
import com.sellics.keywordrate.service.agreggator.helper.ScoreNumberCalculator;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InMemoryPrefixLengthAggregator implements PrefixLengthAggregator {

    private Map<String, Integer> prefixLengthStore = new ConcurrentHashMap<>();

    @Override
    public Integer computeWordRelevance(Collection<Keyword> keywordResponses, String keyword) {
        keywordResponses.forEach(this::addItem);

        List<Integer> prefixLengths = getPrefixLengthFor(keyword);

        return ScoreNumberCalculator.calculateScore(prefixLengths);
    }

    private void addItem(Keyword keyword) {
        String value = keyword.getValue();
        Optional<Integer> wordWithWeight = Optional.ofNullable(prefixLengthStore.get(value));

        int prefixLength = keyword.getPrefix().length();

        if (wordWithWeight.isPresent())
            prefixLengthStore.put(value, Math.min(wordWithWeight.get(), prefixLength));
        else prefixLengthStore.put(value, prefixLength);
    }

    private List<Integer> getPrefixLengthFor(String keyword) {
        return prefixLengthStore.entrySet().stream()
                .filter(entry -> entry.getKey().contains(keyword))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }
}
