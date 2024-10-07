package com.devminrat.tennis.service;

import com.devminrat.tennis.entity.Player;
import org.hibernate.Session;

public interface PlayerService {
    Player getPlayer(Session session, String name);

    boolean addPlayer(Session session, Player player);

    Player findOrCreatePlayer(Session session, String name);
}
