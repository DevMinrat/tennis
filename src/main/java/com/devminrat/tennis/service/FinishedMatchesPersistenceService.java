package com.devminrat.tennis.service;

import com.devminrat.tennis.entity.Match;
import org.hibernate.Session;

import java.util.List;

public interface FinishedMatchesPersistenceService {
    Match getMatchById(Session session, int id);

    List<Match> getMatchesByName(Session session, String playerName, int offset, int limit);

    List<Match> getAllMatches(Session session, int offset, int limit);

    long getMatchesCount(Session session);

    long getMatchesCount(Session session, String playerName);

    boolean addMatch(Session session, Match match);
}
