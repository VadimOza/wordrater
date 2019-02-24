package com.sellics.keywordrate.service.amazon.protocol;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.annotation.Generated;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AmazonKeywordResponse{

	private String prefix;

	private boolean shuffled;

	private String alias;

	private List<SuggestionsItem> suggestions;

	private String responseId;
}