package com.devminrat.tennis.service;

import com.devminrat.tennis.constants.PlayerType;
import com.devminrat.tennis.entity.Match;

public interface MatchScoreCalcService {
    void updateScore(Match match, PlayerType playerWhoScored);
}
