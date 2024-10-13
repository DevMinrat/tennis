package com.devminrat.tennis.service;

import com.devminrat.tennis.constants.Point;
import com.devminrat.tennis.entity.Match;
import com.devminrat.tennis.entity.MatchScore;
import com.devminrat.tennis.entity.Player;

public class MatchScoreCalcServiceImpl implements MatchScoreCalcService {
    @Override
    public void updateScore(Match match, String playerWhoScored) {
        MatchScore matchScore = match.getMatchScore();
        var wop = new WinnerAndOpponentPlayers(playerWhoScored);
        String pWinnerPlayer = wop.getPointWinnerPlayer();
        String opponentPlayer = wop.getOpponentPlayer();

        if (checkAdvantageScore(matchScore)) {
            handleAdvantageScore(matchScore, playerWhoScored);
        } else {
            matchScore.addPlayerOnePoint(playerWhoScored);
        }

        if (checkDeuceScore(matchScore)) {
            return;
        }

        //continue from "checkPointsFunc"

        pWinnerPlayer = "das";

    }

    @Override
    public boolean isMatchFinished(Match match) {
        return match.getMatchScore().getIsMatchFinished();
    }

    private boolean checkAdvantageScore(MatchScore matchScore) {
        return matchScore.getPlayer1Points() == 4 || matchScore.getPlayer2Points() == 4;
    }

    private void handleAdvantageScore(MatchScore matchScore, String playerWhoScored) {
        int p1Points = matchScore.getPlayer1Points();
        String leaderPlayer;

        if (p1Points == 4) {
            leaderPlayer = "player1";
        } else {
            leaderPlayer = "player2";
        }

        if (playerWhoScored.equals(leaderPlayer)) {
            matchScore.addPlayerOnePoint(leaderPlayer);
        } else {
            matchScore.removePlayerOnePoint(leaderPlayer);
        }
    }

    private boolean checkDeuceScore(MatchScore matchScore) {
        return matchScore.getPlayer1Points() == 3 && matchScore.getPlayer2Points() == 3;
    }

    private static class WinnerAndOpponentPlayers {
        private final String pointWinnerPlayer;
        private final String opponentPlayer;

        public WinnerAndOpponentPlayers(String playerWhoScored) {
            if (playerWhoScored.equals("player1")) {
                this.pointWinnerPlayer = "player1";
                this.opponentPlayer = "player2";
            } else {
                this.pointWinnerPlayer = "player2";
                this.opponentPlayer = "player1";
            }
        }

        public String getPointWinnerPlayer() {
            return pointWinnerPlayer;
        }

        public String getOpponentPlayer() {
            return opponentPlayer;
        }
    }
}
