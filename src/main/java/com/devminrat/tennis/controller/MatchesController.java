package com.devminrat.tennis.controller;

import com.devminrat.tennis.constants.Constants;
import com.devminrat.tennis.entity.Match;
import com.devminrat.tennis.service.FinishedMatchesPersistenceService;
import com.devminrat.tennis.service.FinishedMatchesPersistenceServiceImpl;
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
import java.util.List;

import static com.devminrat.tennis.constants.Constants.MatchesStrings.*;
import static com.devminrat.tennis.constants.ErrorMessage.CANT_GET_MATCHES;
import static com.devminrat.tennis.util.ResponseUtil.writeInternalServerErrorResponse;
import static com.devminrat.tennis.util.ValidateUtil.isValidValues;

@WebServlet(name = "MatchesController", value = "/matches")
public class MatchesController extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(MatchesController.class);

    public static final int RECORDS_SIZE = 5;
    FinishedMatchesPersistenceService fmps = new FinishedMatchesPersistenceServiceImpl();

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Match> matches;
            long totalRecords;
            int totalPages;

            String playerName = req.getParameter(filter_by_player_name);
            String pageParam = req.getParameter(page_number);

            int page = pageParam != null ? Math.max(1, Integer.parseInt(pageParam)) : 1;
            int offset = (page - 1) * RECORDS_SIZE;

            boolean isSearchByPlayer = isValidValues(playerName);

            matches = isSearchByPlayer ? fmps.getMatchesByName(session, playerName, RECORDS_SIZE, offset)
                    : fmps.getAllMatches(session, RECORDS_SIZE, offset);
            totalRecords = isSearchByPlayer ? fmps.getMatchesCount(session, playerName) : fmps.getMatchesCount(session);

            totalPages = (int) Math.ceil(totalRecords / (double) RECORDS_SIZE);
            totalPages = Math.max(1, totalPages);

            if (page > totalPages) {
                page = totalPages;
            }

            req.setAttribute(Constants.MatchesStrings.matches, matches);
            req.setAttribute(currentPage, page);
            req.setAttribute(Constants.MatchesStrings.totalPages, totalPages);
            req.setAttribute(Constants.MatchesStrings.playerName, playerName);
            req.getRequestDispatcher("matches.jsp").forward(req, res);

        } catch (HibernateException e) {
            logger.error(e.getMessage(), e);
            writeInternalServerErrorResponse(res, CANT_GET_MATCHES.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            writeInternalServerErrorResponse(res);
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String playerName = req.getParameter(filter_by_player_name);

        if (playerName != null && !playerName.trim().isEmpty()) {
            res.sendRedirect(req.getContextPath() + "/matches?filter_by_player_name=" + playerName);
        } else {
            res.sendRedirect(req.getContextPath() + "/matches");
        }
    }
}
