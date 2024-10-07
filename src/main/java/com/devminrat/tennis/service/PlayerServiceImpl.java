package com.devminrat.tennis.service;

import com.devminrat.tennis.entity.Player;
import com.devminrat.tennis.model.PlayerDao;
import com.devminrat.tennis.model.PlayerDaoImpl;
import org.hibernate.Session;

public class PlayerServiceImpl implements PlayerService {
    private final PlayerDao playerDao = new PlayerDaoImpl();

    @Override
    public Player getPlayer(Session session, String name) {
        return playerDao.getPlayer(session, name);
    }

    @Override
    public boolean addPlayer(Session session, Player player) {
        return playerDao.addPlayer(session, player);
    }

    @Override
    public Player findOrCreatePlayer(Session session, String name) {
        Player player = getPlayer(session, name);
        if (player == null) {
            player = new Player();
            player.setName(name);
            playerDao.addPlayer(session, player);
        }
        return player;
    }
}
