package com.tennis.game.score;

public class DeuceGameScore extends Score{

   public DeuceGameScore(Player player1, Player player2) {
        super(player1, player2);
    }

    public boolean checkCondition() {
        return player1.isDeuceRuleActivated(player2);
    }

    public String display() {
        return formatScore(DEUCE_GAME_MESSAGE, DEUCE_GAME_MESSAGE);
    }
}
