package com.sellics.keywordrate.service.amazon;

import com.sellics.keywordrate.service.amazon.protocol.AmazonKeywordResponse;
import com.sellics.keywordrate.service.amazon.protocol.AmazonKeywordResponseTestFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AmazonKeywordRequesterTest {

    @InjectMocks
    private AmazonKeywordRequester requester;

    @Mock
    private RestTemplate restTemplate;

    @Test
    public void requestKeywords() {
        String keyword = "keyword";
        String prefix = "prefix";

        AmazonKeywordResponse response = AmazonKeywordResponseTestFactory.makeResponce(prefix, keyword);

        when(restTemplate.getForObject(anyString(), any())).thenReturn(response);

        assertThat(requester.requestKeywords(Arrays.asList(prefix)))
                .isNotEmpty()
                .first().hasFieldOrPropertyWithValue("value", keyword);
    }
}