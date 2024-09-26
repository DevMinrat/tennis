package com.devminrat.tennis;

import com.devminrat.tennis.entity.Match;
import com.devminrat.tennis.entity.Player;
import com.devminrat.tennis.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

public class HibernateRunnerTest {

    @Test
    public void addNewPlayerAndMatch() {
        try (SessionFactory sf = HibernateUtil.buildSessionFactory();
             Session session = sf.openSession()) {
            session.beginTransaction();

            var player1 = new Player();
            player1.setName("test_player_1");
            var player2 = new Player();
            player2.setName("test_player_2");

            var match = Match.builder().player1(player1).player2(player2).winner(player1).build();
            session.persist(match);

            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void checkOneToMany() {
        try (SessionFactory sf = HibernateUtil.buildSessionFactory();
             Session session = sf.openSession()) {
            session.beginTransaction();

            var player1 = session.get(Player.class, 1);
            System.out.println(player1);

            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }
}
