package com.devminrat.tennis.controller;

import java.io.*;
import java.util.UUID;

import com.devminrat.tennis.constants.PlayerType;
import com.devminrat.tennis.constants.TennisScore;
import com.devminrat.tennis.entity.Match;
import com.devminrat.tennis.entity.MatchScore;
import com.devminrat.tennis.manager.MatchManager;
import com.devminrat.tennis.service.MatchScoreCalcService;
import com.devminrat.tennis.service.MatchScoreCalcServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "matchScoreController", value = "/match-score")
public class MatchScoreController extends HttpServlet {
    private Match match;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UUID matchId = UUID.fromString(request.getParameter("uuid"));
        match = MatchManager.getMatch(matchId);

        if (match != null) {
            setMatchScoreAttributes(request, response);

        } else {
            System.out.println("Match not found");
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MatchScoreCalcService matchScoreCalcService = new MatchScoreCalcServiceImpl();
        PlayerType pointWinner = PlayerType.valueOf((request.getParameter("winner").toUpperCase()));

        matchScoreCalcService.updateScore(match, pointWinner);

        setMatchScoreAttributes(request, response);
    }

    private void setMatchScoreAttributes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String player1PointsDisplay = TennisScore.getDisplayValue(match.getMatchScore().getPlayerPoints(PlayerType.PLAYER1));
        String player2PointsDisplay = TennisScore.getDisplayValue(match.getMatchScore().getPlayerPoints(PlayerType.PLAYER2));

        request.setAttribute("match", match);

        request.setAttribute("player1PointsDisplay", player1PointsDisplay);
        request.setAttribute("player2PointsDisplay", player2PointsDisplay);
        request.setAttribute("player1Games", match.getMatchScore().getPlayerGames(PlayerType.PLAYER1));
        request.setAttribute("player2Games", match.getMatchScore().getPlayerGames(PlayerType.PLAYER2));
        request.setAttribute("player1Sets", match.getMatchScore().getPlayerSets(PlayerType.PLAYER1));
        request.setAttribute("player2Sets", match.getMatchScore().getPlayerSets(PlayerType.PLAYER2));

        request.getRequestDispatcher("/match-score.jsp").forward(request, response);
    }
}