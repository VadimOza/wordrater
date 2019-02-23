package com.sellics.keywordrate.service.amazon.protocol;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class AmazonKeywordResponse{

	@JsonProperty("prefix")
	private String prefix;

	@JsonProperty("shuffled")
	private boolean shuffled;

	@JsonProperty("alias")
	private String alias;

	@JsonProperty("suggestions")
	private List<SuggestionsItem> suggestions;

	@JsonProperty("suffix")
	private Object suffix;

	@JsonProperty("responseId")
	private String responseId;
}