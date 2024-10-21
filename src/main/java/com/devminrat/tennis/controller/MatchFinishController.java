package com.devminrat.tennis.controller;

import com.devminrat.tennis.entity.Match;
import com.devminrat.tennis.service.FinishedMatchesPersistenceService;
import com.devminrat.tennis.service.FinishedMatchesPersistenceServiceImpl;
import com.devminrat.tennis.util.HibernateUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet(name = "MatchFinishController", value = "/match-finish")
public class MatchFinishController extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(MatchesController.class);

    FinishedMatchesPersistenceService fmps = new FinishedMatchesPersistenceServiceImpl();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int matchId = Integer.parseInt(request.getParameter("matchId"));

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Match match = fmps.getMatchById(session, matchId);

            if (match != null) {
                request.setAttribute("match", match);
                request.getRequestDispatcher("/match-finish.jsp").forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Match not found");
            }
        } catch (HibernateException e) {
            logger.error(e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Match not found");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal server error");
        }
    }
}