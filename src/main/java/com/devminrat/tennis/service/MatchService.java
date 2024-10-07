package com.devminrat.tennis.service;

import com.devminrat.tennis.entity.Match;
import org.hibernate.Session;

public interface MatchService {
    Match getMatch(Session session, int id);

    boolean addMatch(Session session, Match match);
}
