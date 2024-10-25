package com.devminrat.tennis.controller;

import java.io.*;
import java.util.UUID;

import com.devminrat.tennis.constants.Constants;
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
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.devminrat.tennis.constants.Constants.MatchScoreStrings.*;
import static com.devminrat.tennis.constants.ErrorMessage.*;
import static com.devminrat.tennis.util.ResponseUtil.*;

@WebServlet(name = "matchScoreController", value = "/match-score")
public class MatchScoreController extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(MatchesController.class);

    private Match match;
    private UUID matchUUID;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        matchUUID = UUID.fromString(request.getParameter(uuid));
        match = MatchManager.getMatch(matchUUID);

        if (match != null) {
            setMatchScoreAttributes(request);
            request.getRequestDispatcher("/match-score.jsp").forward(request, response);
        } else {
            logger.error(MATCH_NOT_FOUND.getMessage());
            writeNotFoundResponse(response, MATCH_NOT_FOUND.getMessage());
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        MatchScoreCalcService matchScoreCalcService = new MatchScoreCalcServiceImpl();
        FinishedMatchesPersistenceService fmps = new FinishedMatchesPersistenceServiceImpl();
        PlayerType pointWinner = PlayerType.valueOf((request.getParameter(winner).toUpperCase()));

        matchScoreCalcService.updateScore(match, pointWinner);

        if (match.getMatchScore().getIsMatchFinished()) {
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                session.beginTransaction();

                MatchManager.removeMatch(matchUUID);
                fmps.addMatch(session, match);

                session.getTransaction().commit();
            } catch (HibernateException e) {
                logger.error(e.getMessage(), e);
                writeConflictResponse(response, CANT_FETCH_MATCH.getMessage());
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                writeInternalServerErrorResponse(response);
            }

            request.setAttribute(Constants.MatchScoreStrings.match, match);
            request.setAttribute(player1Sets, match.getMatchScore().getPlayerSets(PlayerType.PLAYER1));
            request.setAttribute(player2Sets, match.getMatchScore().getPlayerSets(PlayerType.PLAYER2));
            request.getRequestDispatcher("/WEB-INF/pages/match-finish.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/match-score?uuid=" + matchUUID);
        }
    }

    private void setMatchScoreAttributes(HttpServletRequest request) {
        String player1PointsDisplay = TennisScore.getDisplayValue(match.getMatchScore().getPlayerPoints(PlayerType.PLAYER1));
        String player2PointsDisplay = TennisScore.getDisplayValue(match.getMatchScore().getPlayerPoints(PlayerType.PLAYER2));

        request.setAttribute(Constants.MatchScoreStrings.match, match);

        request.setAttribute(Constants.MatchScoreStrings.player1PointsDisplay, player1PointsDisplay);
        request.setAttribute(Constants.MatchScoreStrings.player2PointsDisplay, player2PointsDisplay);
        request.setAttribute(player1Games, match.getMatchScore().getPlayerGames(PlayerType.PLAYER1));
        request.setAttribute(player2Games, match.getMatchScore().getPlayerGames(PlayerType.PLAYER2));
        request.setAttribute(player1Sets, match.getMatchScore().getPlayerSets(PlayerType.PLAYER1));
        request.setAttribute(player2Sets, match.getMatchScore().getPlayerSets(PlayerType.PLAYER2));

    }
}