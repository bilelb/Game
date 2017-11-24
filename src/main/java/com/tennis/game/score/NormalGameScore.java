package com.tennis.game.score;

public class NormalGameScore extends Score{

    public NormalGameScore(Player player1, Player player2) {
        super(player1, player2);
    }

    public boolean checkCondition() {
        return player1.getScoreGame() <=3 && player2.getScoreGame()<=3 && !player1.isDeuceRuleActivated(player2);
    }

    public String display() {
        return formatScore(scoreMap.get(player1.getScoreGame()), scoreMap.get(player2.getScoreGame()));
    }
}
