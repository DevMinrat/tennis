package com.devminrat.tennis.service;

import com.devminrat.tennis.entity.Match;
import org.hibernate.Session;

import java.util.List;

public interface FinishedMatchesPersistenceService {
    Match getMatchById(Session session, int id);

    List<Match> getMatchesByName(Session session, String playerName);

    List<Match> getAllMatches(Session session);

    boolean addMatch(Session session, Match match);
}
