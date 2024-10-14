package com.devminrat.tennis;

import com.devminrat.tennis.constants.PlayerType;
import com.devminrat.tennis.entity.Match;
import com.devminrat.tennis.entity.MatchScore;
import com.devminrat.tennis.entity.Player;
import com.devminrat.tennis.service.MatchScoreCalcService;
import com.devminrat.tennis.service.MatchScoreCalcServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MatchScoreCalcTest {
    private final MatchScoreCalcService matchScoreCalcService = new MatchScoreCalcServiceImpl();
    private final Player p1 = new Player(0, "Player-1");
    private final Player p2 = new Player(1, "Player-2");

    @Test
    public void test_addGameAfter_40_0() {
        MatchScore ms = new MatchScore(3, 0, 0, 0, 0, 0);
        Match match = Match.builder().player1(p1).player2(p2).matchScore(ms).build();

        matchScoreCalcService.updateScore(match, PlayerType.PLAYER1);

        assertEquals(1, ms.getPlayerGames(PlayerType.PLAYER1));
        assertEquals(0, ms.getPlayerPoints(PlayerType.PLAYER1));
        assertEquals(0, ms.getPlayerPoints(PlayerType.PLAYER2));
    }

    @Test
    public void test_checkDeuceAndAdvantageScore() {
        MatchScore ms = new MatchScore(3, 3, 0, 0, 0, 0);
        Match match = Match.builder().player1(p1).player2(p2).matchScore(ms).build();

        // Player-1 wins deuce point
        matchScoreCalcService.updateScore(match, PlayerType.PLAYER1);
        assertEquals(4, ms.getPlayerPoints(PlayerType.PLAYER1));
        assertEquals(3, ms.getPlayerPoints(PlayerType.PLAYER2));

        // Player-2 wins deuce point
        matchScoreCalcService.updateScore(match, PlayerType.PLAYER2);
        assertEquals(3, ms.getPlayerPoints(PlayerType.PLAYER1));
        assertEquals(3, ms.getPlayerPoints(PlayerType.PLAYER2));

        // Player-2 wins deuce point
        matchScoreCalcService.updateScore(match, PlayerType.PLAYER2);
        assertEquals(3, ms.getPlayerPoints(PlayerType.PLAYER1));
        assertEquals(4, ms.getPlayerPoints(PlayerType.PLAYER2));

        // Player-2 wins game
        matchScoreCalcService.updateScore(match, PlayerType.PLAYER2);
        assertEquals(0, ms.getPlayerPoints(PlayerType.PLAYER1));
        assertEquals(0, ms.getPlayerPoints(PlayerType.PLAYER2));
        assertEquals(0, ms.getPlayerGames(PlayerType.PLAYER1));
        assertEquals(1, ms.getPlayerGames(PlayerType.PLAYER2));
    }

    @Test
    public void test_addSetWhenReached_6_0() {
        MatchScore ms = new MatchScore(3, 0, 5, 0, 0, 0);
        Match match = Match.builder().player1(p1).player2(p2).matchScore(ms).build();

        matchScoreCalcService.updateScore(match, PlayerType.PLAYER1);

        assertEquals(0, ms.getPlayerGames(PlayerType.PLAYER1));
        assertEquals(1, ms.getPlayerSets(PlayerType.PLAYER1));
    }

    @Test
    public void test_tiebreak() {
        MatchScore ms = new MatchScore(3, 0, 5, 5, 0, 0);
        Match match = Match.builder().player1(p1).player2(p2).matchScore(ms).build();

        // Player-1 wins game - Games: 6-5
        matchScoreCalcService.updateScore(match, PlayerType.PLAYER1);
        assertEquals(6, ms.getPlayerGames(PlayerType.PLAYER1));
        assertEquals(5, ms.getPlayerGames(PlayerType.PLAYER2));

        // Player-2 wins next game - Games: 6-6
        for (int i = 0; i < 4; i++) {
            matchScoreCalcService.updateScore(match, PlayerType.PLAYER2);
        }
        assertEquals(6, ms.getPlayerGames(PlayerType.PLAYER1));
        assertEquals(6, ms.getPlayerGames(PlayerType.PLAYER2));

        // Player-1 wins next game and gets 3 points - Games: 7-6
        for (int i = 0; i < 7; i++) {
            matchScoreCalcService.updateScore(match, PlayerType.PLAYER1);
        }
        assertEquals(3, ms.getPlayerPoints(PlayerType.PLAYER1));
        assertEquals(7, ms.getPlayerGames(PlayerType.PLAYER1));
        assertEquals(6, ms.getPlayerGames(PlayerType.PLAYER2));

        // Player-1 wins tiebreak and gets Set - Games: 8-6 -> Sets: 1-0
        matchScoreCalcService.updateScore(match, PlayerType.PLAYER1);
        assertEquals(0, ms.getPlayerGames(PlayerType.PLAYER1));
        assertEquals(0, ms.getPlayerGames(PlayerType.PLAYER2));
        assertEquals(1, ms.getPlayerSets(PlayerType.PLAYER1));
    }
}
