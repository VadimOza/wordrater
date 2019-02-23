package com.sellics.keywordrate.service.amazon.protocol;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class QueryUnderstandingFeaturesItem{

	@JsonProperty("annotations")
	private List<Object> annotations;

	@JsonProperty("source")
	private String source;
}