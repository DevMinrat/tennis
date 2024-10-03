package com.devminrat.tennis.model;

import com.devminrat.tennis.entity.Player;
import com.devminrat.tennis.entity.QPlayer;
import com.querydsl.jpa.impl.JPAQuery;
import org.hibernate.Session;

public class PlayerDaoImpl implements PlayerDao {

    @Override
    public Player getPlayer(Session session, String name) {
        return new JPAQuery<Player>(session).select(QPlayer.player).from(QPlayer.player)
                .where(QPlayer.player.name.eq(name)).fetchFirst();
    }

    @Override
    public Player setPlayer(Session session, Player player) {
        return null;
    }
}
