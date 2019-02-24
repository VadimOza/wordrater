package com.sellics.keywordrate.service.amazon.protocol;

import lombok.*;

import java.util.List;

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