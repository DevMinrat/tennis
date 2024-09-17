package com.devminrat.tennis;

import com.devminrat.tennis.entity.Match;
import com.devminrat.tennis.entity.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");

        try (SessionFactory sessionFactory = cfg.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Player player = new Player();
            player.setId(0);
            player.setName("Player-1");
            session.persist(player);

            Match match = Match.builder()
                    .id(0)
                    .player1(0)
                    .player2(1)
                    .winner(1)
                    .build();
            session.persist(match);

            session.getTransaction().commit();

            Connection conn = DriverManager.getConnection("jdbc:h2:mem:tennis");
            ResultSet resultSet = conn.createStatement().executeQuery("SELECT * from Match");
            while (resultSet.next()) {
                System.out.println(
                        resultSet.getString("id") + " " +
                                resultSet.getString("player1") + " " +
                                resultSet.getString("player2") + " " +
                                resultSet.getString("winner"));
            }

            ResultSet resultSet2 = conn.createStatement().executeQuery("SELECT * from Player");
            while (resultSet2.next()) {
                System.out.println(resultSet2.getString("id") + " " + resultSet2.getString("name"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
