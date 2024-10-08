package com.devminrat.tennis.controller;

import java.io.*;
import java.util.UUID;

import com.devminrat.tennis.entity.Match;
import com.devminrat.tennis.entity.MatchScore;
import com.devminrat.tennis.manager.MatchManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "matchScoreController", value = "/match-score")
public class MatchScoreController extends HttpServlet {
    private MatchScore matchScore;
    private Match match;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UUID matchId = UUID.fromString(request.getParameter("uuid"));
        match = MatchManager.getMatch(matchId);
        matchScore = match.getMatchScore();

        if (match != null) {
            request.setAttribute("match", match);
            request.getRequestDispatcher("/match-score.jsp").forward(request, response);

        } else {
            System.out.println("Match not found");
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var pointWinner = (String) request.getParameter("winner");

        System.out.println(pointWinner);

        if (pointWinner != null && pointWinner.equals("player1")) {
            System.out.println("winner is player1");
            matchScore.setPlayer1Points(15);
            System.out.println(matchScore.getPlayer1Points());
        } else if (pointWinner != null && pointWinner.equals("player2")) {
            matchScore.setPlayer2Points(15);
        }

        request.setAttribute("match", match);
        request.getRequestDispatcher("/match-score.jsp").forward(request, response);
    }
}