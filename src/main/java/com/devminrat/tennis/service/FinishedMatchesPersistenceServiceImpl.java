package com.devminrat.tennis.service;

import com.devminrat.tennis.entity.Match;
import com.devminrat.tennis.model.MatchDao;
import com.devminrat.tennis.model.MatchDaoImpl;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class FinishedMatchesPersistenceServiceImpl implements FinishedMatchesPersistenceService {
    private static final Logger logger = LoggerFactory.getLogger(FinishedMatchesPersistenceServiceImpl.class);

    MatchDao matchDao = new MatchDaoImpl();

    @Override
    public Match getMatchById(Session session, int id) {
        try {
            return matchDao.getMatchById(session, id);
        } catch (HibernateException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<Match> getMatchesByName(Session session, String playerName, int offset, int limit) {
        try {
            return matchDao.getMatchesByName(session, playerName, offset, limit);
        } catch (HibernateException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<Match> getAllMatches(Session session, int offset, int limit) {
        try {
            return matchDao.getAllMatches(session, offset, limit);
        } catch (HibernateException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public long getMatchesCount(Session session) {
        try {
            return matchDao.getMatchesCount(session);
        } catch (HibernateException e) {
            logger.error(e.getMessage(), e);
            return 0;
        }
    }

    @Override
    public long getMatchesCount(Session session, String playerName) {
        try {
            return matchDao.getMatchesCount(session, playerName);
        } catch (HibernateException e) {
            logger.error(e.getMessage(), e);
            return 0;
        }
    }

    @Override
    public boolean addMatch(Session session, Match match) {
        try {
            return matchDao.addMatch(session, match);
        } catch (HibernateException e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }
}
