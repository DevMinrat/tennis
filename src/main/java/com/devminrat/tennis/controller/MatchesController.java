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
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "MatchesController", value = "/matches")
public class MatchesController extends HttpServlet {
    FinishedMatchesPersistenceService fmps = new FinishedMatchesPersistenceServiceImpl();

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try (SessionFactory sf = HibernateUtil.buildSessionFactory(); Session session = sf.openSession()) {
            List<Match> matches;
            var playerName = req.getParameter("filter_by_player_name");

            if (playerName != null) {
                matches = fmps.getMatchesByName(session, playerName);
            } else {
                matches = fmps.getAllMatches(session);
            }

            req.setAttribute("matches", matches);
            req.getRequestDispatcher("matches.jsp").forward(req, res);

        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) {

    }
}
