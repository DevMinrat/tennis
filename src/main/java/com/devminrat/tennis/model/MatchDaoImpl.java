package com.devminrat.tennis.model;

import com.devminrat.tennis.entity.Match;
import com.devminrat.tennis.entity.QMatch;
import com.querydsl.jpa.impl.JPAQuery;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class MatchDaoImpl implements MatchDao {
    private static final Logger logger = LoggerFactory.getLogger(MatchDaoImpl.class);

    @Override
    public Match getMatchById(Session session, int id) {
        return new JPAQuery<Match>(session).select(QMatch.match).from(QMatch.match)
                .where(QMatch.match.id.eq(id)).fetchOne();
    }

    @Override
    public List<Match> getMatchesByName(Session session, String name, int limit, int offset) {
        name = name.toLowerCase();
        var player1 = QMatch.match.player1.name.toLowerCase();
        var player2 = QMatch.match.player2.name.toLowerCase();

        return new JPAQuery<Match>(session).select(QMatch.match).from(QMatch.match)
                .where(player1.eq(name).or(player2.eq(name)))
                .leftJoin(QMatch.match.player1).fetchJoin()
                .leftJoin(QMatch.match.player2).fetchJoin()
                .leftJoin(QMatch.match.winner).fetchJoin()
                .limit(limit)
                .offset(offset)
                .fetch();
    }

    @Override
    public List<Match> getAllMatches(Session session, int limit, int offset) {
        return new JPAQuery<Match>(session)
                .select(QMatch.match)
                .from(QMatch.match)
                .leftJoin(QMatch.match.player1).fetchJoin()
                .leftJoin(QMatch.match.player2).fetchJoin()
                .leftJoin(QMatch.match.winner).fetchJoin()
                .limit(limit)
                .offset(offset)
                .fetch();
    }

    @Override
    public long getMatchesCount(Session session) {
        return Optional.ofNullable(new JPAQuery<Match>(session)
                .select(QMatch.match.count())
                .from(QMatch.match)
                .fetchOne()).orElse(0L);
    }

    @Override
    public long getMatchesCount(Session session, String playerName) {
        playerName = playerName.toLowerCase();
        var player1 = QMatch.match.player1.name.toLowerCase();
        var player2 = QMatch.match.player2.name.toLowerCase();

        return Optional.ofNullable(new JPAQuery<Match>(session)
                .select(QMatch.match.count())
                .from(QMatch.match)
                .where(player1.eq(playerName).or(player2.eq(playerName)))
                .fetchOne()).orElse(0L);
    }

    @Override
    public boolean addMatch(Session session, Match match) {
        try {
            session.persist(match);
            return true;
        } catch (HibernateException e) {
            logger.error("Error adding match: ", e);
            return false;
        }
    }
}
