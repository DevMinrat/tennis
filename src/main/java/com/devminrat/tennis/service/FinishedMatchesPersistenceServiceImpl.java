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
    public List<Match> getMatchesByName(Session session, String playerName) {
        return matchDao.getMatchesByName(session, playerName);
    }

    @Override
    public List<Match> getAllMatches(Session session) {
        return matchDao.getAllMatches(session);
    }

    @Override
    public boolean addMatch(Session session, Match match) {
        return matchDao.addMatch(session, match);
    }
}
