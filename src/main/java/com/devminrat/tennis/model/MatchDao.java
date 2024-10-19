package com.devminrat.tennis.model;

import com.devminrat.tennis.entity.Match;
import org.hibernate.Session;

import java.util.List;

public interface MatchDao {
    Match getMatchById(Session session, int id);

    List<Match> getMatchesByName(Session session, String name, int offset, int limit);

    List<Match> getAllMatches(Session session, int offset, int limit);

    long getMatchesCount(Session session);

    long getMatchesCount(Session session, String playerName);

    boolean addMatch(Session session, Match match);
}
