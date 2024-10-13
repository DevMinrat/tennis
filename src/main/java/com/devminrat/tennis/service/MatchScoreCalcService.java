package com.devminrat.tennis.service;

import com.devminrat.tennis.entity.Match;
import com.devminrat.tennis.entity.Player;

public interface MatchScoreCalcService {
    void updateScore(Match match, String playerWhoScored);
    boolean isMatchFinished(Match match);
}
