package com.sellics.keywordrate.service.amazon;

import com.sellics.keywordrate.model.Keyword;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
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
        String url = "https://completion.amazon.com/api/2017/suggestions?mid=ATVPDKIKX0DER&alias=aps&prefix=" + keyword;

        Keyword expected = new Keyword(keyword, prefix);

        when(restTemplate.getForObject(eq(url), any())).thenReturn(expected);

        assertThat(requester.requestKeywords(Arrays.asList(prefix)))
                .isEqualTo(expected);
    }
}