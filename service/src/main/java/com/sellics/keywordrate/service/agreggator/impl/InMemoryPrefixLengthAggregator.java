package com.sellics.keywordrate.service.agreggator.impl;

import com.sellics.keywordrate.model.Keyword;
import com.sellics.keywordrate.service.agreggator.PrefixLengthAggregator;
import com.sellics.keywordrate.service.agreggator.helper.ScoreNumberCalculator;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryPrefixLengthAggregator implements PrefixLengthAggregator {

    @Override
    public Integer computeWordRelevance(Collection<Keyword> keywordResponses, String keywordText) {
        Map<String, Integer> prefixLengthStore = new ConcurrentHashMap<>();

        keywordResponses.stream()
                .filter(keyword -> keyword.getValue().contains(keywordText))
                .forEach(keyword -> addItem(keyword, prefixLengthStore));

        return ScoreNumberCalculator.calculateScore(prefixLengthStore.values());
    }

    private void addItem(Keyword keyword, Map<String, Integer> prefixLengthStore) {
        String value = keyword.getValue();
        Optional<Integer> wordWithWeight = Optional.ofNullable(prefixLengthStore.get(value));

        int prefixLength = keyword.getPrefix().length();

        if (wordWithWeight.isPresent())
            prefixLengthStore.put(value, Math.min(wordWithWeight.get(), prefixLength));
        else prefixLengthStore.put(value, prefixLength);
    }
}
