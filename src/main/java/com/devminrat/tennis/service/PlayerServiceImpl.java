package com.devminrat.tennis.service;

import com.devminrat.tennis.entity.Player;
import com.devminrat.tennis.model.PlayerDao;
import com.devminrat.tennis.model.PlayerDaoImpl;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlayerServiceImpl implements PlayerService {
    private static final Logger logger = LoggerFactory.getLogger(FinishedMatchesPersistenceServiceImpl.class);

    private final PlayerDao playerDao = new PlayerDaoImpl();

    @Override
    public Player getPlayer(Session session, String name) {
        try {
            return playerDao.getPlayer(session, name);
        } catch (HibernateException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public boolean addPlayer(Session session, Player player) {
        try {
            return playerDao.addPlayer(session, player);
        } catch (HibernateException e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Player findOrCreatePlayer(Session session, String name) {
        try {
            Player player = getPlayer(session, name);
            if (player == null) {
                player = new Player();
                player.setName(name);
                playerDao.addPlayer(session, player);
            }
            return player;
        } catch (HibernateException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }
}
