package com.devminrat.tennis.model;

import com.devminrat.tennis.entity.Match;
import org.hibernate.Session;

import java.util.List;

public interface MatchDao {
    Match getMatchById(Session session, int id);

    List<Match> getMatchesByName(Session session, String name);

    List<Match> getAllMatches(Session session);

    boolean addMatch(Session session, Match match);
}
