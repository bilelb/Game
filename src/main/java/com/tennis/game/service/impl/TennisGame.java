package com.tennis.game.service.impl;

import com.tennis.game.score.Player;
import com.tennis.game.service.ITennisGame;

import java.util.HashMap;
import java.util.Map;

/**
 * Tennis Game Kata
 */
public class TennisGame implements ITennisGame{

    private static String WIN_SET_MESSAGE = "Win match";
    private static String WIN_GAME_MESSAGE = "Win game";
    private static String DEUCE_GAME_MESSAGE = "Deuce";
    private static String ADVANTAGE_GAME_MESSAGE = "Advantage";
    private static String SCORE_PLAYER = "%s : %s";


    private Player player1;
    private Player player2;
    private Map<Integer, String> scoreMap = new HashMap<Integer, String>(5);

    /**
     * Tennis Game constructor
     *
     * @param playerOneName
     * @param playerTwoName
     */
    public TennisGame(String playerOneName, String playerTwoName) {
        this.player1 = new Player(playerOneName);
        this.player2 = new Player(playerTwoName);
        initScoreMap();
    }

    public Player getPlayerByName(String playerName) {
        return player1.getName().endsWith(playerName) ? player1 : player2;
    }

    /**
     * Principal method to display the current players game score
     *
     * @return game score
     */
    public String getCurrentGameScore() {
        String currentGameScore;
        if (player1.hasWinGameAgainst(player2)) {
            currentGameScore = formatScore(WIN_GAME_MESSAGE, scoreMap.get(player2.getScoreGame()));
            initScore();
            player1.winSetGame();
        }
        else if (player2.hasWinGameAgainst(player1)) {
            currentGameScore = formatScore(scoreMap.get(player1.getScoreGame()), WIN_GAME_MESSAGE);
            initScore();
            player2.winSetGame();
        }
        else if (isDeuceRuleActivated()) {
            currentGameScore = formatScore(DEUCE_GAME_MESSAGE, DEUCE_GAME_MESSAGE);
        }
        else if (player1.hasAdvantageAgainst(player2)) {
            currentGameScore = formatScore(ADVANTAGE_GAME_MESSAGE, scoreMap.get(player2.getScoreGame()));
        }
        else if (player2.hasAdvantageAgainst(player1)) {
            currentGameScore = formatScore(scoreMap.get(player1.getScoreGame()), ADVANTAGE_GAME_MESSAGE);
        }
        else {
            currentGameScore = formatScore(scoreMap.get(player1.getScoreGame()), scoreMap.get(player2.getScoreGame()));
        }
        return currentGameScore;
    }

    private void initScore() {
        player1.setScoreGame(0);
        player2.setScoreGame(0);
    }

    /**
     * Principal method to display the current players game score
     *
     * @return Set Score
     */
    public String getSetScore() {
        if(player1.hasWinSetAgainst(player2)){
            return formatScore(WIN_SET_MESSAGE, String.valueOf(player2.getScoreSet()));
        }
        else if(player2.hasWinSetAgainst(player1)){
            return formatScore(String.valueOf(player1.getScoreSet()), WIN_SET_MESSAGE);
        }
        else{
            return formatScore(String.valueOf(player1.getScoreSet()), String.valueOf(player2.getScoreSet()));
        }
    }

    /**
     *
     * @return
     */
    public String getTieBreakScore() {
        if(isTeiBreakRuleActivated()){
            if(player1.hasWinSetAgainst(player2)){
                return formatScore(WIN_SET_MESSAGE, String.valueOf(player2.getScoreTieBreak()));
            }
            else if(player2.hasWinSetAgainst(player1)){
                return formatScore(String.valueOf(player1.getScoreTieBreak()), WIN_SET_MESSAGE);
            }else{
                return formatScore(String.valueOf(player1.getScoreTieBreak()), String.valueOf(player2.getScoreTieBreak()));
            }
        }
        return null;
    }


    /**
     * format Score message
     *
     *  @param playerOneScore
     * @param playerTwoScore
     */
    private String formatScore(String playerOneScore, String playerTwoScore) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(SCORE_PLAYER, player1, playerOneScore));
        sb.append("\n");
        sb.append(String.format(SCORE_PLAYER, player2, playerTwoScore));
        return sb.toString();
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

    /**
     * Check if the Deuce rule is true
     *
     * @return true if the score of each player is 40
     */
    public boolean isDeuceRuleActivated() {
        return player1.getScoreGame() == 3 && player2.getScoreGame() == 3;
    }

    public boolean isTeiBreakRuleActivated(){
       return  player1.getScoreSet() == 6 && player2.getScoreSet() == 6;
    }

}

