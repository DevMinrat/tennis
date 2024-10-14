package com.devminrat.tennis.service;

import com.devminrat.tennis.constants.PlayerType;
import com.devminrat.tennis.entity.Match;
import com.devminrat.tennis.entity.Player;

public interface MatchScoreCalcService {
    void updateScore(Match match, PlayerType playerWhoScored);
    boolean isMatchFinished(Match match);
}
