package com.devminrat.tennis.model;

import com.devminrat.tennis.entity.Player;
import org.hibernate.Session;

public interface PlayerDao {
    Player getPlayer(Session session, String name);

    boolean addPlayer(Session session, Player player);
}
