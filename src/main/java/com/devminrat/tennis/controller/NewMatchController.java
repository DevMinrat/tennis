package com.devminrat.tennis.controller;

import com.devminrat.tennis.entity.Player;
import com.devminrat.tennis.model.PlayerDaoImpl;
import com.devminrat.tennis.util.HibernateUtil;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.IOException;

@WebServlet(name = "newMatchController", value = "/new-match")
public class NewMatchController extends HttpServlet {

    PlayerDaoImpl pdao = new PlayerDaoImpl();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (SessionFactory sf = HibernateUtil.buildSessionFactory(); Session session = sf.openSession()) {
            session.beginTransaction();

            String player1 = request.getParameter("player1");
            String player2 = request.getParameter("player2");

            System.out.println(player1);
            System.out.println(player2);

            Player pl = pdao.getPlayer(session, "Player-1");

            System.out.println(pl);


            session.getTransaction().commit();

        }
    }
}