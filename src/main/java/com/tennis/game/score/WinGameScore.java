package com.tennis.game.score;

public class WinGameScore extends Score{

    public WinGameScore(Player player1, Player player2) {
        super(player1, player2);
    }

    public boolean checkCondition() {
        return player1.hasWinGameAgainst(player2) || player2.hasWinGameAgainst(player1);
    }

    public String display() {
        boolean isPlayer1WinTheGame = player1.getName().equals(wishPlayerHaveBigScore().getName());
        String score = isPlayer1WinTheGame ?
                formatScore(WIN_GAME_MESSAGE, scoreMap.get(player2.getScoreGame())) :
                formatScore(scoreMap.get(player1.getScoreGame()), WIN_GAME_MESSAGE);
        initScore();
        if(isPlayer1WinTheGame){
            player1.winSetGame();
        }else{
            player2.winSetGame();
        }
        return score;
    }

    private void initScore() {
        player1.setScoreGame(0);
        player2.setScoreGame(0);
    }
}
