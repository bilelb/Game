package com.tennis.game.score;

public class AdvantageGameScore extends Score {

    public AdvantageGameScore(Player player1, Player player2) {
        super(player1, player2);
    }

    public boolean checkCondition() {
        return player1.hasAdvantageAgainst(player2) || player2.hasAdvantageAgainst(player1);
    }

    public String display() {
        return  player1.getName().equals(wishPlayerHaveBigScore().getName())?
                formatScore(ADVANTAGE_GAME_MESSAGE, scoreMap.get(player2.getScoreGame())) :
                formatScore(scoreMap.get(player1.getScoreGame()), ADVANTAGE_GAME_MESSAGE);
    }
}
