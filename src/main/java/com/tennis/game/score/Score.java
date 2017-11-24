package com.tennis.game.score;

import java.util.HashMap;
import java.util.Map;

public abstract class Score {

    protected static String WIN_SET_MESSAGE = "Win match";
    protected static String WIN_GAME_MESSAGE = "Win game";
    protected static String DEUCE_GAME_MESSAGE = "Deuce";
    protected static String ADVANTAGE_GAME_MESSAGE = "Advantage";
    protected static String SCORE_PLAYER = "%s : %s";
    protected Map<Integer, String> scoreMap = new HashMap<Integer, String>(5);


    public Player player1;
    public Player player2;

    public Score(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        initScoreMap();
    }

    public abstract boolean checkCondition();

    public abstract String display();

    protected String formatScore(String playerOneScore, String playerTwoScore) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(SCORE_PLAYER, player1, playerOneScore));
        sb.append("\n");
        sb.append(String.format(SCORE_PLAYER, player2, playerTwoScore));
        return sb.toString();
    }

    protected Player wishPlayerHaveBigScore(){
        return player1.getScoreGame() > player2.getScoreGame() ? player1 : player2;
    }

    /**
     * init the game score Map
     */
    private void initScoreMap() {
        scoreMap.put(0, "0");
        scoreMap.put(1, "15");
        scoreMap.put(2, "30");
        scoreMap.put(3, "40");
    }
}
