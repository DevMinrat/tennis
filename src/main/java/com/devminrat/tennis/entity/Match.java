package com.devminrat.tennis.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

@Entity
public class Match {
    @Id
    @GeneratedValue
    private int id;
    @JoinColumn()
    private int player1;
    private int player2;
    private int winner;

    public Match() {
    }

    public Match(int id, int player1, int player2, int winner) {
        this.id = id;
        this.player1 = player1;
        this.player2 = player2;
        this.winner = winner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlayer1() {
        return player1;
    }

    public void setPlayer1(int player1) {
        this.player1 = player1;
    }

    public int getPlayer2() {
        return player2;
    }

    public void setPlayer2(int player2) {
        this.player2 = player2;
    }

    public int getWinner() {
        return winner;
    }

    public void setWinner(int winner) {
        this.winner = winner;
    }

    public static class MatchBuilder {
        private int id;
        private int player1;
        private int player2;
        private int winner;

        public MatchBuilder() {
        }

        public MatchBuilder id(int id) {
            this.id = id;
            return this;
        }

        public MatchBuilder player1(int player1) {
            this.player1 = player1;
            return this;
        }

        public MatchBuilder player2(int player2) {
            this.player2 = player2;
            return this;
        }

        public MatchBuilder winner(int winner) {
            this.winner = winner;
            return this;
        }

        public Match build() {
            return new Match(id, player1, player2, winner);
        }
    }

    public static MatchBuilder builder() {
        return new MatchBuilder();
    }
}
