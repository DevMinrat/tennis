package com.devminrat.tennis.service;

import com.devminrat.tennis.entity.Match;
import com.devminrat.tennis.model.MatchDao;
import com.devminrat.tennis.model.MatchDaoImpl;
import org.hibernate.Session;

public class MatchServiceImpl implements MatchService {
    MatchDao matchDao = new MatchDaoImpl();

    @Override
    public Match getMatch(Session session, int id) {
        return matchDao.getMatch(session, id);
    }

    @Override
    public boolean addMatch(Session session, Match match) {
        return matchDao.addMatch(session, match);
    }
}
