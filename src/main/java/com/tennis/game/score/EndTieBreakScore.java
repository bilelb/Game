package com.tennis.game.score;

public class EndTieBreakScore extends Score {

    public EndTieBreakScore(Player player1, Player player2) {
        super(player1, player2);
    }

    public boolean checkCondition() {
        return player1.hasWinSetAgainst(player2) || player2.hasWinSetAgainst(player1);
    }

    public String display() {
        return player1.getScoreTieBreak() > player2.getScoreTieBreak() ?
                   formatScore(WIN_SET_MESSAGE, String.valueOf(player2.getScoreTieBreak())) :
                   formatScore(String.valueOf(player1.getScoreTieBreak()), WIN_SET_MESSAGE);

    }
}
