package com.devminrat.tennis.constants;

public enum TennisScore {
    ZERO(0, "0"),
    FIFTEEN(1, "15"),
    THIRTY(2, "30"),
    FORTY(3, "40"),
    ADVANTAGE(4, "Ad");

    private final int points;
    private final String displayValue;

    TennisScore(int x, String displayValue) {
        this.points = x;
        this.displayValue = displayValue;
    }

    public static String getDisplayValue(int points) {
        for (TennisScore score : TennisScore.values()) {
            if (score.points == points) {
                return score.displayValue;
            }
        }
        return null;
    }

    public int get() {
        return points;
    }

}
