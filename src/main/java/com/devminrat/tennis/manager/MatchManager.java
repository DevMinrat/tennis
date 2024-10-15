package com.devminrat.tennis.manager;

import com.devminrat.tennis.entity.Match;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MatchManager {
    private static final Map<UUID, Match> matches = new HashMap<>();

    private MatchManager() {
    }

    public static Map<UUID, Match> getMatches() {
        return matches;
    }

    public static void addMatch(UUID uuid, Match match) {
        matches.put(uuid, match);
    }

    public static Match getMatch(UUID uuid) {
        return matches.get(uuid);
    }

    public static void removeMatch(UUID uuid) {
        matches.remove(uuid);
    }
}
