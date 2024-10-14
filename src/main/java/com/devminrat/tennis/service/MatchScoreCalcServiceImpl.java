package com.devminrat.tennis.service;

import com.devminrat.tennis.constants.PlayerType;
import com.devminrat.tennis.entity.Match;
import com.devminrat.tennis.entity.MatchScore;

public class MatchScoreCalcServiceImpl implements MatchScoreCalcService {
    private static final PlayerType PLAYER_1 = PlayerType.PLAYER1;
    private static final PlayerType PLAYER_2 = PlayerType.PLAYER2;
    private static final int POINTS_TO_WIN_GAME = 4;
    private static final int POINTS_TO_WIN_SET = 6;
    private static final int POINTS_TO_WIN_MATCH = 2;
    private static final int DEUCE_POINTS = 3;
    private static final int PREPONDERANCE = 2;

    @Override
    public void updateScore(Match match, PlayerType playerWhoScored) {
        MatchScore matchScore = match.getMatchScore();
        PlayerType opponentPlayer = getOpponentPlayer(playerWhoScored);

        if (checkAdvantageScore(matchScore)) {
            handleAdvantageScore(matchScore, playerWhoScored);
        } else {
            matchScore.incrementPlayerPoints(playerWhoScored);
        }

        if (checkDeuceScore(matchScore)) {
            return;
        }

        if (checkPointsForGame(matchScore, playerWhoScored, opponentPlayer)) {
            addOneGameAndResetPoints(matchScore, playerWhoScored);

            if (checkGamesForSet(matchScore, playerWhoScored, opponentPlayer)) {
                addOneSetAndResetGames(matchScore, playerWhoScored);
                if (checkSets(matchScore, playerWhoScored)) {
                    matchScore.setIsMatchFinished(true);
                    System.out.println("MATCH FINISHED");
                    // run service for finishing game.
                }
            }
        }
    }

    @Override
    public boolean isMatchFinished(Match match) {
        return match.getMatchScore().getIsMatchFinished();
    }

    private PlayerType getOpponentPlayer(PlayerType playerWhoScored) {
        return playerWhoScored == PLAYER_1 ? PLAYER_2 : PLAYER_1;
    }

    private boolean checkWinCondition(int playerScore, int opponentScore, int minToWin) {
        return playerScore >= minToWin && playerScore - opponentScore >= MatchScoreCalcServiceImpl.PREPONDERANCE;
    }

    private boolean checkAdvantageScore(MatchScore matchScore) {
        return matchScore.getPlayerPoints(PLAYER_1) == POINTS_TO_WIN_GAME || matchScore.getPlayerPoints(PLAYER_2) == POINTS_TO_WIN_GAME;
    }

    private void handleAdvantageScore(MatchScore matchScore, PlayerType playerWhoScored) {
        PlayerType leaderPlayer = matchScore.getPlayerPoints(PLAYER_1) == POINTS_TO_WIN_GAME ? PLAYER_1 : PLAYER_2;

        if (playerWhoScored.equals(leaderPlayer)) {
            matchScore.incrementPlayerPoints(leaderPlayer);
        } else {
            matchScore.decrementPlayerPoints(leaderPlayer);
        }
    }

    private boolean checkDeuceScore(MatchScore matchScore) {
        return matchScore.getPlayerPoints(PLAYER_1) == DEUCE_POINTS && matchScore.getPlayerPoints(PLAYER_2) == DEUCE_POINTS;
    }

    private boolean checkPointsForGame(MatchScore matchScore, PlayerType playerWhoScored, PlayerType opponentPlayer) {
        return checkWinCondition(matchScore.getPlayerPoints(playerWhoScored), matchScore.getPlayerPoints(opponentPlayer), POINTS_TO_WIN_GAME);
    }

    private void addOneGameAndResetPoints(MatchScore matchScore, PlayerType playerWhoScored) {
        matchScore.incrementPlayerGames(playerWhoScored);
        matchScore.setPlayerPoints(PLAYER_1, 0);
        matchScore.setPlayerPoints(PLAYER_2, 0);
    }

    private boolean checkGamesForSet(MatchScore matchScore, PlayerType playerWhoScored, PlayerType opponentPlayer) {
        return checkWinCondition(matchScore.getPlayerGames(playerWhoScored), matchScore.getPlayerGames(opponentPlayer), POINTS_TO_WIN_SET);
    }

    private void addOneSetAndResetGames(MatchScore matchScore, PlayerType playerWhoScored) {
        matchScore.incrementPlayerSets(playerWhoScored);
        matchScore.setPlayerGames(PLAYER_1, 0);
        matchScore.setPlayerGames(PLAYER_2, 0);
    }

    private boolean checkSets(MatchScore matchScore, PlayerType playerWhoScored) {
        return matchScore.getPlayerSets(playerWhoScored) == POINTS_TO_WIN_MATCH;
    }
}
