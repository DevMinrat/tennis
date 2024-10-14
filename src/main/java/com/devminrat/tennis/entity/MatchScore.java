package com.devminrat.tennis.entity;

import com.devminrat.tennis.constants.PlayerType;

public class MatchScore {
    private int player1Points;
    private int player2Points;
    private int player1Games;
    private int player2Games;
    private int player1Sets;
    private int player2Sets;
    private boolean isMatchFinished;

    public MatchScore() {
    }

    public MatchScore(int player1Points, int player2Points, int player1Games, int player2Games, int player1Sets, int player2Sets) {
        this.player1Points = player1Points;
        this.player2Points = player2Points;
        this.player1Games = player1Games;
        this.player2Games = player2Games;
        this.player1Sets = player1Sets;
        this.player2Sets = player2Sets;
    }

    public void setPlayerPoints(PlayerType player, int points) {
        if (player == PlayerType.PLAYER1) {
            this.player1Points = points;
        } else {
            this.player2Points = points;
        }
    }

    public int getPlayerPoints(PlayerType player) {
        return player == PlayerType.PLAYER1 ? this.player1Points : this.player2Points;
    }

    public void setPlayerGames(PlayerType player, int games) {
        if (player == PlayerType.PLAYER1) {
            this.player1Games = games;
        } else {
            this.player2Games = games;
        }
    }

    public int getPlayerGames(PlayerType player) {
        return player == PlayerType.PLAYER1 ? this.player1Games : this.player2Games;
    }

    public void setPlayerSets(PlayerType player, int sets) {
        if (player == PlayerType.PLAYER1) {
            this.player1Sets = sets;
        } else {
            this.player2Sets = sets;
        }
    }

    public int getPlayerSets(PlayerType player) {
        return player == PlayerType.PLAYER1 ? this.player1Sets : this.player2Sets;
    }

    public void incrementPlayerPoints(PlayerType player) {
        if (player == PlayerType.PLAYER1) {
            this.player1Points++;
        } else {
            this.player2Points++;
        }
    }

    public void decrementPlayerPoints(PlayerType player) {
        if (player == PlayerType.PLAYER1) {
            this.player1Points--;
        } else {
            this.player2Points--;
        }
    }

    public void incrementPlayerGames(PlayerType player) {
        if (player == PlayerType.PLAYER1) {
            this.player1Games++;
        } else {
            this.player2Games++;
        }
    }

    public void incrementPlayerSets(PlayerType player) {
        if (player == PlayerType.PLAYER1) {
            this.player1Sets++;
        } else {
            this.player2Sets++;
        }
    }

    public boolean getIsMatchFinished() {
        return isMatchFinished;
    }

    public void setIsMatchFinished(boolean isMatchFinished) {
        this.isMatchFinished = isMatchFinished;
    }

    @Override
    public String toString() {
        return "MatchScore{" +
                "player1Points=" + player1Points +
                ", player2Points=" + player2Points +
                ", player1games=" + player1Games +
                ", player2games=" + player2Games +
                ", player1sets=" + player1Sets +
                ", player2sets=" + player2Sets +
                '}';
    }
}
