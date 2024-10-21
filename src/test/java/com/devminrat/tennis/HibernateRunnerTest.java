package com.devminrat.tennis;

import com.devminrat.tennis.entity.Match;
import com.devminrat.tennis.entity.Player;
import com.devminrat.tennis.util.HibernateUtil;
import jakarta.persistence.PersistenceException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HibernateRunnerTest {

    @Test
    public void addNewPlayerAndMatch() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            var player1 = new Player();
            player1.setName("test_player_1");
            var player2 = new Player();
            player2.setName("test_player_2");

            var match = Match.builder().player1(player1).player2(player2).winner(player1).build();
            session.persist(match);

            assertEquals("test_player_1", match.getPlayer1().getName());
            assertEquals("test_player_2", match.getPlayer2().getName());

            session.remove(match);

            assertNull(session.get(Match.class, match.getId()));
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void checkOneToMany() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            var player1 = session.get(Player.class, 1);
            System.out.println(player1);

            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void test_checkMatchConstraint_samePlayerNotAllowed() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            Player p1 = session.get(Player.class, 1);
            Match newMatch = Match.builder().player1(p1).player2(p1).build();

            var thrown = assertThrows(PersistenceException.class, () -> session.persist(newMatch));
            assertInstanceOf(ConstraintViolationException.class, thrown.getCause());
        }
    }
}
