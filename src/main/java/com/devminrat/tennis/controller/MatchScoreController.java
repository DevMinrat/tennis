package com.devminrat.tennis.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.devminrat.tennis.entity.Match;
import com.devminrat.tennis.entity.MatchScore;
import com.devminrat.tennis.entity.Player;
import com.devminrat.tennis.manager.MatchManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.h2.expression.function.table.CSVReadFunction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@WebServlet(name = "matchScoreController", value = "/match-score")
public class MatchScoreController extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        UUID matchId = UUID.fromString(request.getParameter("uuid"));
        Match match = MatchManager.getMatch(matchId);

        if (match != null) {
            MatchScore matchScore = match.getMatchScore();
            System.out.println(match);
            System.out.println(matchScore);

        } else {
            System.out.println("Match not found");
        }
    }
}