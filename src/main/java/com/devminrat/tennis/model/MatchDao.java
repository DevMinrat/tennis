package com.devminrat.tennis.model;

import com.devminrat.tennis.entity.Match;
import org.hibernate.Session;

public interface MatchDao {
    Match getMatch(Session session, int id);

    boolean addMatch(Session session, Match match);
}
