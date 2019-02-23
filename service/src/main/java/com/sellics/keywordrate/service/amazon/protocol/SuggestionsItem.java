package com.sellics.keywordrate.service.amazon.protocol;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class SuggestionsItem{

	@JsonProperty("refTag")
	private String refTag;

	@JsonProperty("help")
	private boolean help;

	@JsonProperty("queryUnderstandingFeatures")
	private List<QueryUnderstandingFeaturesItem> queryUnderstandingFeatures;

	@JsonProperty("ghost")
	private boolean ghost;

	@JsonProperty("suggType")
	private String suggType;

	@JsonProperty("spellCorrected")
	private boolean spellCorrected;

	@JsonProperty("type")
	private String type;

	@JsonProperty("value")
	private String value;

	@JsonProperty("fallback")
	private boolean fallback;

	@JsonProperty("xcatOnly")
	private boolean xcatOnly;

	@JsonProperty("blackListed")
	private boolean blackListed;

	@JsonProperty("scopes")
	private List<ScopesItem> scopes;
}