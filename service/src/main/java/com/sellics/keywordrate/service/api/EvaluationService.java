package com.sellics.keywordrate.service.api;

import com.sellics.keywordrate.EvaluateResponse;

public interface EvaluationService {

    EvaluateResponse evaluate(String keyword);
}
