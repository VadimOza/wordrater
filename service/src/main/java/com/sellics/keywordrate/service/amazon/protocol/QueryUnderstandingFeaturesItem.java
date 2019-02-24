package com.sellics.keywordrate.service.amazon.protocol;

import lombok.Data;

import java.util.List;

@Data
public class QueryUnderstandingFeaturesItem {

    private List<String> annotations;

    private String source;
}