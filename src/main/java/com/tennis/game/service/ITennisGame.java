package com.tennis.game.service;

import com.tennis.game.score.Player;

public interface ITennisGame {

    Player getPlayerByName(String playerName);

    String getCurrentGameScore();

    String getSetScore();

    String getTieBreakScore();

}
