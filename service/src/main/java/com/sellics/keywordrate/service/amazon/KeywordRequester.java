package com.sellics.keywordrate.service.amazon;

import com.sellics.keywordrate.model.Keyword;

import java.util.Collection;
import java.util.List;

public interface KeywordRequester {
    List<Keyword> requestKeywords(Collection<String> prefixes);
}
