package com.sellics.keywordrate;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EvaluateResponse {
    private String keyword;
    private int score;
}