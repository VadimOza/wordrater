package com.sellics.keywordrate.service.agreggator.helper;

import java.util.Collection;

public class ScoreNumberCalculator {

    public static int calculateScore(Collection<Integer> numbers) {
        if (numbers.isEmpty())
            return 0;

        int sum = 0;
        int maxPref = 0;
        for (Integer pref : numbers) {
            sum += pref;
            maxPref = Math.max(pref, maxPref);
        }

        float averagePrefLength = ((float) sum) / numbers.size();
        return mapToRange(averagePrefLength, maxPref);
    }

    private static int mapToRange(float x, long in_max) {
        int maxScore = 100;
        int minScore = 0;

        if (in_max == 1) return 100;
        return Math.round((x - 1) * (minScore - maxScore) / (in_max - 1) + maxScore);
    }
}
