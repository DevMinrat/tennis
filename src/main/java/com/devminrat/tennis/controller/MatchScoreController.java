package com.devminrat.tennis.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.devminrat.tennis.entity.Player;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@WebServlet(name = "matchScoreController", value = "/match-score")
public class MatchScoreController extends HttpServlet {
    ObjectMapper objectMapper = new ObjectMapper();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");

        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");

        List<Player> players = new ArrayList<>();

        try (SessionFactory sessionFactory = cfg.buildSessionFactory(); Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            var player1 = session.get(Player.class, 1);
            var player2 = session.get(Player.class, 2);
            players.add(player1);
            players.add(player2);

            String json = objectMapper.writeValueAsString(players);
            response.getWriter().println(json);

            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}