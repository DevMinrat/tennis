package com.devminrat.tennis.controller;

import com.devminrat.tennis.constants.PlayerType;
import com.devminrat.tennis.entity.Match;
import com.devminrat.tennis.entity.MatchScore;
import com.devminrat.tennis.entity.Player;
import com.devminrat.tennis.manager.MatchManager;
import com.devminrat.tennis.service.FinishedMatchesPersistenceService;
import com.devminrat.tennis.service.FinishedMatchesPersistenceServiceImpl;
import com.devminrat.tennis.service.PlayerService;
import com.devminrat.tennis.service.PlayerServiceImpl;
import com.devminrat.tennis.util.HibernateUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "MatchFinishController", value = "/match-finish")
public class MatchFinishController extends HttpServlet {
    FinishedMatchesPersistenceService fmps = new FinishedMatchesPersistenceServiceImpl();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int matchId = Integer.parseInt(request.getParameter("matchId"));

        try (SessionFactory sf = HibernateUtil.buildSessionFactory(); Session session = sf.openSession()) {
            Match match = fmps.getMatchById(session, matchId);

            if (match != null) {
                request.setAttribute("match", match);
                request.getRequestDispatcher("/match-finish.jsp").forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Match not found");
            }
        }
    }
}