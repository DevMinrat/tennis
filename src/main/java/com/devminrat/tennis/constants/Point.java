package com.devminrat.tennis.constants;

public enum Point {
    ONE_POINT(15),
    TWO_POINTS(30),
    THREE_POINTS(40),
    ADVANTAGE(50),
    GAME(60);

    private final int points;

    Point(int x) {
        this.points = x;
    }

    public int get() {
        return points;
    }

}
