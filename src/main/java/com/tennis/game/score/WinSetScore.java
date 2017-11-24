package com.tennis.game.score;

public class WinSetScore extends Score {

    public WinSetScore(Player player1, Player player2) {
        super(player1, player2);
    }

    public boolean checkCondition() {
        return player1.hasWinSetAgainst(player2) || player2.hasWinSetAgainst(player1);
    }

    public String display() {
        return player1.getScoreSet() > player2.getScoreSet() && !player1.isTeiBreakRuleActivated(player2) ?
                formatScore(WIN_SET_MESSAGE, String.valueOf(player2.getScoreSet())) :  formatScore(String.valueOf(player1.getScoreSet()), WIN_SET_MESSAGE);
    }
}
