package com.sellics.keywordrate.controller;

import com.sellics.keywordrate.EvaluateResponse;
import com.sellics.keywordrate.service.evaluation.api.EvaluationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.sellics.keywordrate.controller.KeywordController.BASE_URL;
import static com.sellics.keywordrate.controller.KeywordController.Parameter.KEYWORD;

@RestController
@RequestMapping(BASE_URL)
@RequiredArgsConstructor
public class KeywordController {

    static final String BASE_URL = "/estimate";

    static class Parameter{
        static final String KEYWORD = "keyword";
    }

    private final EvaluationService service;

    @GetMapping
    public ResponseEntity<EvaluateResponse> evaluateKeyword(@RequestParam(KEYWORD) String keyword) {
        return ResponseEntity.ok(service.evaluate(keyword));
    }
}
