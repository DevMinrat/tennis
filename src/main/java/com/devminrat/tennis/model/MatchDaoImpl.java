package com.devminrat.tennis.model;

import com.devminrat.tennis.entity.Match;
import com.devminrat.tennis.entity.QMatch;
import com.querydsl.jpa.impl.JPAQuery;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

public class MatchDaoImpl implements MatchDao {
    @Override
    public Match getMatchById(Session session, int id) {
        return new JPAQuery<Match>(session).select(QMatch.match).from(QMatch.match)
                .where(QMatch.match.id.eq(id)).fetchOne();
    }

    @Override
    public List<Match> getMatchesByName(Session session, String name) {
        return new JPAQuery<Match>(session).select(QMatch.match).from(QMatch.match)
                .where(QMatch.match.player1.name.eq(name).or(QMatch.match.player2.name.eq(name))).fetch();
    }

    @Override
    public List<Match> getAllMatches(Session session) {
        return new JPAQuery<Match>(session).from(QMatch.match).fetch();
    }

    @Override
    public boolean addMatch(Session session, Match match) {
        try {
            session.persist(match);
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }
}
