package com.sellics.keywordrate.service.evaluation.api;

import com.sellics.keywordrate.EvaluateResponse;

public interface EvaluationService {

    EvaluateResponse evaluate(String keyword);
}
