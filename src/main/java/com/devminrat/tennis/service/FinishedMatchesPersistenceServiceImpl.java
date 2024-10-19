package com.devminrat.tennis.service;

import com.devminrat.tennis.entity.Match;
import com.devminrat.tennis.model.MatchDao;
import com.devminrat.tennis.model.MatchDaoImpl;
import org.hibernate.Session;

import java.util.List;

public class FinishedMatchesPersistenceServiceImpl implements FinishedMatchesPersistenceService {
    MatchDao matchDao = new MatchDaoImpl();

    @Override
    public Match getMatchById(Session session, int id) {
        return matchDao.getMatchById(session, id);
    }

    @Override
    public List<Match> getMatchesByName(Session session, String playerName, int offset, int limit) {
        return matchDao.getMatchesByName(session, playerName, offset, limit);
    }

    @Override
    public List<Match> getAllMatches(Session session, int offset, int limit) {
        return matchDao.getAllMatches(session, offset, limit);
    }

    @Override
    public long getMatchesCount(Session session) {
        return matchDao.getMatchesCount(session);
    }

    @Override
    public long getMatchesCount(Session session, String playerName) {
        return matchDao.getMatchesCount(session, playerName);
    }

    @Override
    public boolean addMatch(Session session, Match match) {
        return matchDao.addMatch(session, match);
    }
}
