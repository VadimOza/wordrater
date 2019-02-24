package com.sellics.keywordrate.service.amazon;

import com.sellics.keywordrate.model.Keyword;
import com.sellics.keywordrate.service.amazon.protocol.AmazonKeywordResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AmazonKeywordRequester implements KeywordRequester {

    private final RestTemplate restTemplate;
    private final String SUGGESTION_URL = "https://completion.amazon.com/api/2017/suggestions";

    @Override
    public List<Keyword> requestKeywords(Collection<String> prefixes) {
        return prefixes.stream().map(this::requestKeywords)
                .flatMap(response -> response.getSuggestions().stream().map(item -> new Keyword(item.getValue(), response.getPrefix())))
                .collect(Collectors.toList());
    }

    private AmazonKeywordResponse requestKeywords(String keyword) {
        return restTemplate.getForObject(getUrlWithParams(keyword), AmazonKeywordResponse.class);
    }

    private String getUrlWithParams(String keyword) {
        return UriComponentsBuilder.fromHttpUrl(SUGGESTION_URL)
                .queryParam("mid", "ATVPDKIKX0DER")
                .queryParam("alias", "aps")
                .queryParam("prefix", keyword)
                .build().toUriString();
    }

}
