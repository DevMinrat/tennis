package com.devminrat.tennis.entity;

import jakarta.persistence.*;

@Entity
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "player1_id", referencedColumnName = "id")
    private Player player1;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "player2_id", referencedColumnName = "id")
    private Player player2;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "winner_id", referencedColumnName = "id")
    private Player winner;

    public Match() {
    }

    public Match(int id, Player player1, Player player2, Player winner) {
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

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public static class MatchBuilder {
        private int id;
        private Player player1;
        private Player player2;
        private Player winner;

        public MatchBuilder() {
        }

        public MatchBuilder id(int id) {
            this.id = id;
            return this;
        }

        public MatchBuilder player1(Player player1) {
            this.player1 = player1;
            return this;
        }

        public MatchBuilder player2(Player player2) {
            this.player2 = player2;
            return this;
        }

        public MatchBuilder winner(Player winner) {
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
