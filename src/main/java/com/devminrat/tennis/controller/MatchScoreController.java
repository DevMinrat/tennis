package com.devminrat.tennis.controller;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.devminrat.tennis.entity.Match;
import com.devminrat.tennis.entity.Player;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@WebServlet(name = "matchScore", value = "/match-score")
public class MatchScoreController extends HttpServlet {
    ObjectMapper objectMapper = new ObjectMapper();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");

        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");

        List<Player> players = new ArrayList<>();

        try (SessionFactory sessionFactory = cfg.buildSessionFactory(); Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Connection conn = DriverManager.getConnection("jdbc:h2:E:/JAVA/practice/_prj/tennis/src/main/resources/data/tennis");
            ResultSet resultSet = conn.createStatement().executeQuery("SELECT * from Player");
            while (resultSet.next()) {
                Player p = new Player(resultSet.getInt("id"), resultSet.getString("name"));
                players.add(p);

            }

            String json = objectMapper.writeValueAsString(players);
            response.getWriter().println(json);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}