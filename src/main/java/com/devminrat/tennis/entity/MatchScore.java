package com.devminrat.tennis.entity;

public class MatchScore {
    private int player1Points;
    private int player2Points;
    private int player1Games;
    private int player2Games;
    private int player1Sets;
    private int player2Sets;

    public int getPlayer1Points() {
        return player1Points;
    }

    public void setPlayer1Points(int player1Points) {
        this.player1Points = player1Points;
    }

    public int getPlayer2Points() {
        return player2Points;
    }

    public void setPlayer2Points(int player2Points) {
        this.player2Points = player2Points;
    }

    public int getPlayer1Games() {
        return player1Games;
    }

    public void setPlayer1Games(int player1Games) {
        this.player1Games = player1Games;
    }

    public int getPlayer2Games() {
        return player2Games;
    }

    public void setPlayer2Games(int player2Games) {
        this.player2Games = player2Games;
    }

    public int getPlayer1Sets() {
        return player1Sets;
    }

    public void setPlayer1Sets(int player1Sets) {
        this.player1Sets = player1Sets;
    }

    public int getPlayer2Sets() {
        return player2Sets;
    }

    public void setPlayer2Sets(int player2Sets) {
        this.player2Sets = player2Sets;
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
