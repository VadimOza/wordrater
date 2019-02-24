package com.sellics.keywordrate.service.amazon.protocol;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.annotation.Generated;

@Data
public class QueryUnderstandingFeaturesItem {

    private List<String> annotations;

    private String source;
}