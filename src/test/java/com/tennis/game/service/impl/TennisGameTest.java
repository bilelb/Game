package com.tennis.game.service.impl;

import com.tennis.game.score.Player;
import com.tennis.game.service.ITennisGame;
import com.tennis.game.service.impl.TennisGame;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tennis Game Unit Test Class
 */
@RunWith(JUnitParamsRunner.class)
public class TennisGameTest {

    private ITennisGame tennisGame;
    private Player player1;
    private Player player2;
    @Before
    public void setUp(){
        tennisGame = new TennisGame("Federer", "Nadal");
        player1 = tennisGame.getPlayerByName("Federer");
        player2 = tennisGame.getPlayerByName("Nadal");

    }

    /**
     * SPRINT1 : manage a tennis GAME within a set of a tennis match
     */

    /**
     * <p>
     *      <b>User Story 1 :</b><br/>
     *      As a tennis referee <br/>
     *      I want to manage the score of a game of a set of a tennis match between 2 players
     *      with simple Game rules<br/>
     *      In order to display the current Game score of each player<br/>
     *      Rules details:<br/>
     *      <ul>
     *          <li>The game starts with a score of 0 point for each player</li>
     *          <li>Each time a player win a point, the Game score changes as follow: 0 -> 15 -> 30 -> 40-> Win game</li>
     *      </ul>
     * </p>
     */
    @Test
    @Parameters({
            "1,0, Federer : 15\nNadal : 0",
            "2,0, Federer : 30\nNadal : 0",
            "3,0, Federer : 40\nNadal : 0",
            "4,0, Federer : Win game\nNadal : 0",
            "1,1, Federer : 15\nNadal : 15",
            "2,1, Federer : 30\nNadal : 15",
            "3,1, Federer : 40\nNadal : 15",
            "4,1, Federer : Win game\nNadal : 15",
            "1,2, Federer : 15\nNadal : 30",
            "2,2, Federer : 30\nNadal : 30",
            "3,2, Federer : 40\nNadal : 30",
            "4,2, Federer : Win game\nNadal : 30",
            "1,3, Federer : 15\nNadal : 40",
            "2,3, Federer : 30\nNadal : 40",
            "4,3, Federer : Advantage\nNadal : 40",
            "0,1, Federer : 0\nNadal : 15",
            "0,2, Federer : 0\nNadal : 30",
            "0,3, Federer : 0\nNadal : 40",
            "0,4, Federer : 0\nNadal : Win game",
            "1,2, Federer : 15\nNadal : 30",
            "1,3, Federer : 15\nNadal : 40",
            "1,4, Federer : 15\nNadal : Win game",
            "3,4, Federer : 40\nNadal : Advantage"
    })
    public void should_display_current_game_score_when_players_win_points(int pointsToWinForP1, int pointsToWinForP2,  String gameScore){
        // When
        simulateGameFor(player1, pointsToWinForP1);
        simulateGameFor(player2, pointsToWinForP2);
        // Then
        assertThat(tennisGame.getCurrentGameScore()).isEqualTo(gameScore);
    }


    /**
     * <p>
     *      <b>User Story 2 :</b><br/>
     *      As a tennis referee <br/>
     *      I want to manage the specific of the rule DEUCE at the end of a Game<br/>
     *      In order to display the current Game score of each player<br/>
     *      Rules details:<br/>
     *      <ul>
     *          <li>If the 2 players reach the score 40, the DEUCE rule is activated</li>
     *          <li>If the score is DEUCE , the player who  win the point take the ADVANTAGE</li>
     *          <li>If the player who has the ADVANTAGE win the point, he win the game</li>
     *          <li>If the player who has the ADVANTAGE loose the point, the score is DEUCE</li>
     *      </ul>
     * </p>
     */
    @Test
    public void should_activate_deuce_rule_when_the_2_players_reach_40_score(){
        // When
        simulateGameFor(player1, 3);
        simulateGameFor(player2, 3);
        // Then
        assertThat(tennisGame.getCurrentGameScore()).isEqualTo("Federer : Deuce\nNadal : Deuce");
    }

    @Test
    public void should_display_deuce_score_for_the_player_who_have_advantage_and_lose_point(){
        // When
        simulateGameFor(player1, 4);
        simulateGameFor(player2, 3);
        player1.loosePoint();
        // Then
        assertThat(tennisGame.getCurrentGameScore()).isEqualTo("Federer : Deuce\nNadal : Deuce");
    }


    /**
     * Sprint 2 : Manage a Tennis SET within a tennis match
     */

    /**
     * <p>
     *     User Story 1:<br/>
     *     As a tennis referee<br/>
     *     I want to manage the score of a set of a tennis between 2 players<br/>
     *     In order to display the current Game & Set score of each player<br/>
     *     Rules details
     *     <ul>
     *         <li>The set starts with a score of 0 Game of each player</li>
     *         <li>
     *             Each time a player win a Game, the Set score change as follow
     *             1 -> 2 -> 3 -> 4 -> 5 -> 6 (-> 7)
     *         </li>
     *         <li>
     *             If a player reach the Set score of 6 and the other player has a Set
     *              score of 4 or lower, the player win the Set
     *         </li>
     *         <li>
     *               If a player win a Game and reach the Set score of 6 and the other player
     *               has a Set score of 5,a new Game must be played
     *               and the first player who reach the score of 7 wins the match
     *         </li>
     *     </ul>
     * </p>
     */

    @Test
    public void should_display_current_game_and_set_score_when_a_player_win_a_game(){
        // When Federer win game
        simulateGameFor(player1, 4);
        // Then
        assertThat(tennisGame.getCurrentGameScore()).isEqualTo("Federer : Win game\nNadal : 0");
        assertThat(tennisGame.getSetScore()).isEqualTo("Federer : 1\nNadal : 0");
        // When nadal win game
        simulateGameFor(player2, 4);
        // Then
        assertThat(tennisGame.getCurrentGameScore()).isEqualTo("Federer : 0\nNadal : Win game");
        assertThat(tennisGame.getSetScore()).isEqualTo("Federer : 1\nNadal : 1");
        // when Federer win all next game
        for(int k=2; k<6; k++){
            simulateGameFor(player1, 4);
            assertThat(tennisGame.getCurrentGameScore()).isEqualTo("Federer : Win game\nNadal : 0");
            assertThat(tennisGame.getSetScore()).isEqualTo("Federer : "+k+"\nNadal : 1");

        }
    }

    @Test
    @Parameters({
            "6, 4, Federer : Win match\nNadal : 4",
            "6, 3, Federer : Win match\nNadal : 3",
            "6, 2, Federer : Win match\nNadal : 2",
            "6, 1, Federer : Win match\nNadal : 1",
            "6, 0, Federer : Win match\nNadal : 0",
            "4, 6, Federer : 4\nNadal : Win match",
            "3, 6, Federer : 3\nNadal : Win match",
            "2, 6, Federer : 2\nNadal : Win match",
            "1, 6, Federer : 1\nNadal : Win match",
            "0, 6, Federer : 0\nNadal : Win match",
    })
    public void should_player_win_a_set_when_her_score_set_is_6_and_other_player_score_is_4_or_lower(int gamesToWinForP1, int gamesToWinForP2, String score){
        // when
        player1.setScoreSet(gamesToWinForP1);
        player2.setScoreSet(gamesToWinForP2);
        // then
        assertThat(tennisGame.getSetScore()).isEqualTo(score);
    }


    @Test
    @Parameters({
            "1,0,Federer : 1\nNadal : 0",
            "1,1,Federer : 1\nNadal : 1",
            "6,5,Federer : 6\nNadal : 5",
            "7,6,Federer : 7\nNadal : 6",
            "7,5,Federer : Win match\nNadal : 5",
            "6,8,Federer : 6\nNadal : Win match",
    })
    public void should_display_tie_break_score_when_player_win_point(int pointsToWinForP1, int pointsToWinForP2, String score){
        // when
        player1.setScoreSet(6);
        player2.setScoreSet(6);
        simulateTieBreakFor(player1, pointsToWinForP1);
        simulateTieBreakFor(player2, pointsToWinForP2);
        //
        assertThat(tennisGame.getTieBreakScore()).isEqualTo(score);
    }


    private void simulateGameFor(Player player, int pointsToWin){
        for(int i=0; i<pointsToWin; i++){
            player.winPoint();
        }
    }

    private void simulateTieBreakFor(Player player, int pointsToWin){
        for(int i=0; i<pointsToWin; i++){
            player.winTieBreakPoint();
        }
    }

}
