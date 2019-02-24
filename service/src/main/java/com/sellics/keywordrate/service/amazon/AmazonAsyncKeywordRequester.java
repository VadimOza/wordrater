package com.sellics.keywordrate.service.amazon;

import com.sellics.keywordrate.model.Keyword;
import com.sellics.keywordrate.service.amazon.protocol.AmazonKeywordResponse;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Profile("async")
@Primary
public class AmazonAsyncKeywordRequester implements KeywordRequester {

    private final String SUGGESTION_URL = "https://completion.amazon.com/api/2017/suggestions";
    private final WebClient client = WebClient.create();

    @Override
    public List<Keyword> requestKeywords(Collection<String> prefixes) {
        List<Mono<ClientResponse>> asyncResponses = prefixes.stream().map(this::requestKeywords)
                .collect(Collectors.toList());

        List<AmazonKeywordResponse> amazonResponses = Flux.merge(asyncResponses).flatMap(clientResponse -> clientResponse.bodyToMono(AmazonKeywordResponse.class))
                .collectList().block();

        return amazonResponses.stream().flatMap(r -> r.getSuggestions().stream().map(item -> new Keyword(item.getValue(), r.getPrefix()))).collect(Collectors.toList());
    }

    private Mono<ClientResponse> requestKeywords(String keyword) {
        return client.get().uri(getUrlWithParams(keyword)).exchange();
    }

    private String getUrlWithParams(String keyword) {
        return UriComponentsBuilder.fromHttpUrl(SUGGESTION_URL)
                .queryParam("mid", "ATVPDKIKX0DER")
                .queryParam("alias", "aps")
                .queryParam("prefix", keyword)
                .build().toUriString();
    }

}
