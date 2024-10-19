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
    public static final int RECORDS_SIZE = 5;

    FinishedMatchesPersistenceService fmps = new FinishedMatchesPersistenceServiceImpl();

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //TODO: initialize SessionFactory once.
        try (SessionFactory sf = HibernateUtil.buildSessionFactory(); Session session = sf.openSession()) {
            List<Match> matches;
            long totalRecords;
            int totalPages;

            String playerName = req.getParameter("filter_by_player_name");
            String pageParam = req.getParameter("page_number");

            int page = pageParam != null ? Math.max(1, Integer.parseInt(pageParam)) : 1;
            int offset = (page - 1) * RECORDS_SIZE;

            //TODO: refactoring duplicated code
            if (playerName != null && !playerName.trim().isEmpty()) {
                matches = fmps.getMatchesByName(session, playerName, RECORDS_SIZE, offset);
                totalRecords = fmps.getMatchesCount(session, playerName);
                totalPages = (int) Math.ceil(totalRecords / (double) RECORDS_SIZE);
                totalPages = Math.max(1, totalPages);
            } else {
                matches = fmps.getAllMatches(session, RECORDS_SIZE, offset);
                totalRecords = fmps.getMatchesCount(session);
                totalPages = (int) Math.ceil(totalRecords / (double) RECORDS_SIZE);
                totalPages = Math.max(1, totalPages);
            }

            if (page > totalPages) {
                page = totalPages;
            }

            req.setAttribute("matches", matches);
            req.setAttribute("currentPage", page);
            req.setAttribute("totalPages", totalPages);
            req.setAttribute("playerName", playerName);
            System.out.println(playerName);
            req.getRequestDispatcher("matches.jsp").forward(req, res);

        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) {

    }
}
