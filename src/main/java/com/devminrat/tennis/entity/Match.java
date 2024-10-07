package com.devminrat.tennis.entity;

import jakarta.persistence.*;

@Entity
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "player1_id", referencedColumnName = "id")
    private Player player1;

    @ManyToOne
    @JoinColumn(name = "player2_id", referencedColumnName = "id")
    private Player player2;

    @ManyToOne
    @JoinColumn(name = "winner_id", referencedColumnName = "id")
    private Player winner;

    @Embedded
    @Transient
    private MatchScore matchScore;

    public Match() {
    }

    public Match(int id, Player player1, Player player2, Player winner, MatchScore matchScore) {
        this.id = id;
        this.player1 = player1;
        this.player2 = player2;
        this.winner = winner;
        this.matchScore = matchScore;
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

    public MatchScore getMatchScore() {
        return matchScore;
    }

    public void setMatchScore(MatchScore matchScore) {
        this.matchScore = matchScore;
    }

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", player1=" + player1 +
                ", player2=" + player2 +
                ", winner=" + winner +
                ", matchScore=" + matchScore +
                '}';
    }

    public static class MatchBuilder {
        private int id;
        private Player player1;
        private Player player2;
        private Player winner;
        private MatchScore matchScore;

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

        public MatchBuilder matchScore(MatchScore matchScore) {
            this.matchScore = matchScore;
            return this;
        }

        public Match build() {
            return new Match(id, player1, player2, winner, matchScore);
        }
    }

    public static MatchBuilder builder() {
        return new MatchBuilder();
    }
}
