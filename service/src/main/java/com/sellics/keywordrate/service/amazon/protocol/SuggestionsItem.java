package com.sellics.keywordrate.service.amazon.protocol;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.annotation.Generated;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SuggestionsItem{

	private String refTag;

	private boolean help;

	private List<QueryUnderstandingFeaturesItem> queryUnderstandingFeatures;

	private boolean ghost;

	private String suggType;

	private boolean spellCorrected;

	private String type;

	private String value;

	private boolean fallback;

	private boolean xcatOnly;

	private boolean blackListed;

	private List<ScopesItem> scopes;
}