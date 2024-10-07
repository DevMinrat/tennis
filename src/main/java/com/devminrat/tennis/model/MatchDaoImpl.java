package com.devminrat.tennis.model;

import com.devminrat.tennis.entity.Match;
import com.devminrat.tennis.entity.QMatch;
import com.querydsl.jpa.impl.JPAQuery;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class MatchDaoImpl implements MatchDao {
    @Override
    public Match getMatch(Session session, int id) {
        return new JPAQuery<Match>(session).select(QMatch.match).from(QMatch.match)
                .where(QMatch.match.id.eq(id)).fetchOne();
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
