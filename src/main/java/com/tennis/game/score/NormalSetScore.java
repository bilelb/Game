package com.tennis.game.score;

public class NormalSetScore extends Score{

    public NormalSetScore(Player player1, Player player2) {
        super(player1, player2);
    }

    public boolean checkCondition() {
        return !player1.hasWinSetAgainst(player2) && !player2.hasWinSetAgainst(player1);
    }

    public String display() {
        return formatScore(String.valueOf(player1.getScoreSet()), String.valueOf(player2.getScoreSet()));
    }
}
