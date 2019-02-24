package com.sellics.keywordrate.service.amazon.protocol;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AmazonKeywordResponseTestFactory {

    public static AmazonKeywordResponse makeResponce(String prefix, String... keywords) {
        List<SuggestionsItem> items = Stream.of(keywords)
                .map(keyword -> SuggestionsItem.builder()
                        .value(keyword)
                        .build()).collect(Collectors.toList());


        return AmazonKeywordResponse.builder()
                .suggestions(items)
                .prefix(prefix)
                .build();
    }

}