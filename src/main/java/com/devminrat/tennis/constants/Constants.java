package com.devminrat.tennis.constants;

public final class Constants {
    private Constants() {
    }

    public interface MatchScoreStrings {
        String uuid = "uuid";
        String winner = "winner";
        String match = "match";

        String player1Sets = "player1Sets";
        String player2Sets = "player2Sets";
        String player1Games = "player1Games";
        String player2Games = "player2Games";

        String player1PointsDisplay = "player1PointsDisplay";
        String player2PointsDisplay = "player2PointsDisplay";
    }

    public interface MatchesStrings {
        String filter_by_player_name = "filter_by_player_name";
        String page_number = "page_number";
        String matches = "matches";
        String currentPage = "currentPage";
        String totalPages = "totalPages";
        String playerName = "playerName";
    }

}
