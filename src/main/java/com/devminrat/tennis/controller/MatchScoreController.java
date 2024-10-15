package com.devminrat.tennis.controller;

import java.io.*;
import java.util.UUID;

import com.devminrat.tennis.constants.PlayerType;
import com.devminrat.tennis.constants.TennisScore;
import com.devminrat.tennis.entity.Match;
import com.devminrat.tennis.manager.MatchManager;
import com.devminrat.tennis.service.FinishedMatchesPersistenceService;
import com.devminrat.tennis.service.FinishedMatchesPersistenceServiceImpl;
import com.devminrat.tennis.service.MatchScoreCalcService;
import com.devminrat.tennis.service.MatchScoreCalcServiceImpl;
import com.devminrat.tennis.util.HibernateUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@WebServlet(name = "matchScoreController", value = "/match-score")
public class MatchScoreController extends HttpServlet {
    private Match match;
    private UUID matchId;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        matchId = UUID.fromString(request.getParameter("uuid"));
        match = MatchManager.getMatch(matchId);

        if (match != null) {
            setMatchScoreAttributes(request);
            request.getRequestDispatcher("/match-score.jsp").forward(request, response);
        } else {
            System.out.println("Match not found");
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MatchScoreCalcService matchScoreCalcService = new MatchScoreCalcServiceImpl();
        FinishedMatchesPersistenceService fmps = new FinishedMatchesPersistenceServiceImpl();
        PlayerType pointWinner = PlayerType.valueOf((request.getParameter("winner").toUpperCase()));

        matchScoreCalcService.updateScore(match, pointWinner);

        if (match.getMatchScore().getIsMatchFinished()) {
            try (SessionFactory sf = HibernateUtil.buildSessionFactory(); Session session = sf.openSession()) {
                session.beginTransaction();

                MatchManager.removeMatch(matchId);
                fmps.addMatch(session, match);

                session.getTransaction().commit();
            }
            request.setAttribute("match", match);
            request.setAttribute("player1Sets", match.getMatchScore().getPlayerSets(PlayerType.PLAYER1));
            request.setAttribute("player2Sets", match.getMatchScore().getPlayerSets(PlayerType.PLAYER2));
            request.getRequestDispatcher("/match-finish.jsp").forward(request, response);

        } else {
            setMatchScoreAttributes(request);
            request.getRequestDispatcher("/match-score.jsp").forward(request, response);
        }
    }

    private void setMatchScoreAttributes(HttpServletRequest request) {
        String player1PointsDisplay = TennisScore.getDisplayValue(match.getMatchScore().getPlayerPoints(PlayerType.PLAYER1));
        String player2PointsDisplay = TennisScore.getDisplayValue(match.getMatchScore().getPlayerPoints(PlayerType.PLAYER2));

        request.setAttribute("match", match);

        request.setAttribute("player1PointsDisplay", player1PointsDisplay);
        request.setAttribute("player2PointsDisplay", player2PointsDisplay);
        request.setAttribute("player1Games", match.getMatchScore().getPlayerGames(PlayerType.PLAYER1));
        request.setAttribute("player2Games", match.getMatchScore().getPlayerGames(PlayerType.PLAYER2));
        request.setAttribute("player1Sets", match.getMatchScore().getPlayerSets(PlayerType.PLAYER1));
        request.setAttribute("player2Sets", match.getMatchScore().getPlayerSets(PlayerType.PLAYER2));

    }
}