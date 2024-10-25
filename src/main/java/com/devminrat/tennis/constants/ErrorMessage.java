package com.devminrat.tennis.constants;

public enum ErrorMessage {
    PLAYER_EXIST("Player already exists!"),
    NULL_PLAYERS_NAME("Player1 name or Player2 name is null"),
    CANT_FETCH_PLAYERS("Error occurred while fetching players"),
    CANT_FETCH_MATCH("Error occurred while adding new match"),
    CANT_GET_MATCHES("Error occurred while getting the matches list"),
    SAME_PLAYERS("Player1 name is the same as Player2 name"),
    MATCH_NOT_FOUND("Match not found");

    private final String message;

    ErrorMessage(String s) {
        this.message = s;
    }

    public String getMessage() {
        return message;
    }
}
