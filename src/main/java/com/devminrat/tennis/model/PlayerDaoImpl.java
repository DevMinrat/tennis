package com.devminrat.tennis.model;

import com.devminrat.tennis.entity.Player;
import com.devminrat.tennis.entity.QPlayer;
import com.querydsl.jpa.impl.JPAQuery;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

public class PlayerDaoImpl implements PlayerDao {

    @Override
    public Player getPlayer(Session session, String name) {
        return new JPAQuery<Player>(session)
                .select(QPlayer.player)
                .from(QPlayer.player)
                .where(QPlayer.player.name.toLowerCase().eq(name.toLowerCase()))
                .fetchFirst();
    }

    @Override
    public boolean addPlayer(Session session, Player player) {
        try {
            session.persist(player);
            return true;
        } catch (ConstraintViolationException e) {
            System.out.println("Player already exists!");
            return false;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }
}
