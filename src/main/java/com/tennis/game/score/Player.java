package com.tennis.game.score;

import java.util.HashMap;
import java.util.Map;


public class Player {

    private String name;
    private int scoreGame;
    private int scoreSet;
    private int scoreTieBreak;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScoreGame() {
        return scoreGame;
    }

    public void setScoreGame(int scoreGame) {
        this.scoreGame = scoreGame;
    }

    public int getScoreSet() {
        return scoreSet;
    }

    public void setScoreSet(int scoreSet) {
        this.scoreSet = scoreSet;
    }

    public int getScoreTieBreak() {
        return scoreTieBreak;
    }

    public void setScoreTieBreak(int scoreTieBreak) {
        this.scoreTieBreak = scoreTieBreak;
    }

    @Override
    public String toString() {
        return getName();
    }

    public void winPoint(){
        this.scoreGame = getScoreGame() + 1;
    }

    public void winTieBreakPoint(){
        this.scoreTieBreak = getScoreTieBreak() + 1;
    }


    public void loosePoint(){
        this.scoreGame = getScoreGame() - 1;
    }

    public void winSetGame(){
        this.scoreSet = getScoreSet() + 1;
    }

    public boolean hasWinGameAgainst(Player player2){
        return this.scoreGame == 4 && player2.scoreGame < 3;
    }

    public boolean hasAdvantageAgainst(Player player2){
        return this.scoreGame == 4 && player2.scoreGame ==3;
    }

    public boolean isDeuceRuleActivated(Player player2){
        return this.scoreGame == 3 && player2.getScoreGame() == this.scoreGame;
    }

    public boolean hasWinSetAgainst(Player player2){
        return (this.scoreSet == 6 && player2.scoreSet <=4) ||
                (this.scoreTieBreak>=6 && this.scoreTieBreak > player2.scoreTieBreak  && (this.scoreTieBreak - player2.scoreTieBreak >= 2));
    }

    public boolean isTeiBreakRuleActivated(Player player2){
        return  this.scoreSet == 6 && player2.getScoreSet() == 6;
    }

}
