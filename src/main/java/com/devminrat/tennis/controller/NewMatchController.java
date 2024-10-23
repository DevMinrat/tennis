package com.devminrat.tennis.controller;

import com.devminrat.tennis.constants.PlayerType;
import com.devminrat.tennis.entity.Match;
import com.devminrat.tennis.entity.MatchScore;
import com.devminrat.tennis.entity.Player;
import com.devminrat.tennis.manager.MatchManager;
import com.devminrat.tennis.service.PlayerService;
import com.devminrat.tennis.service.PlayerServiceImpl;
import com.devminrat.tennis.util.HibernateUtil;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.UUID;

import static com.devminrat.tennis.util.ValidateUtil.isValidValues;

@WebServlet(name = "newMatchController", value = "/new-match")
public class NewMatchController extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(MatchesController.class);

    PlayerService playerService = new PlayerServiceImpl();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            String player1Name = request.getParameter(PlayerType.PLAYER1.name().toLowerCase());
            String player2Name = request.getParameter(PlayerType.PLAYER2.name().toLowerCase());

            System.out.println(player1Name);
            System.out.println(player2Name);

            if (isValidValues(player1Name, player2Name)) {
                if (player1Name.equals(player2Name)) {
                    logger.error("Player1 name is the same as Player2 name");
                    response.sendError(HttpServletResponse.SC_CONFLICT, "Player1 name is the same as Player2 name");
                    return;
                }

                Player player1 = playerService.findOrCreatePlayer(session, player1Name);
                Player player2 = playerService.findOrCreatePlayer(session, player2Name);

                UUID matchID = UUID.randomUUID();
                Match match = Match.builder().player1(player1).player2(player2)
                        .matchScore(new MatchScore()).build();
                MatchManager.addMatch(matchID, match);

                response.sendRedirect(request.getContextPath() + "/match-score?uuid=" + matchID);

            } else {
                logger.error("Player1 name or Player2 name is null");
                response.sendError(HttpServletResponse.SC_CONFLICT, "Player1 name or Player2 name is null");
            }
        } catch (HibernateException e) {
            logger.error(e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error occurred while fetching players");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal server error");
        }
    }
}