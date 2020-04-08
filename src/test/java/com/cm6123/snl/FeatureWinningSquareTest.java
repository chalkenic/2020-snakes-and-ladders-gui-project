package com.cm6123.snl;

import com.cm6123.snl.dice.DiceSet;
import com.cm6123.snl.dice.LoadedDiceFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FeatureWinningSquareTest {

    //Issue #11
    @Test
    void check_player_position_at_turn_start() {

        Game winningGame = new GameBuilder()
                .withPlayers(2)
                .withBoardSize(5)
                .build();

        winningGame.moveCurrentPlayer(5);
        winningGame.moveCurrentPlayer(4);

        //Check's player 1's position on board.
        Assertions.assertEquals(5,winningGame.getCurrentPlayer().getPosition().get());
    }

    //Issue #12
    @Test
    void store_1_player_roll() {
        Game winningGame = new GameBuilder()
                .withPlayers(1)
                .withBoardSize(5)
                .build();


        winningGame.moveCurrentPlayer(3);
        Assertions.assertEquals(3, winningGame.getCurrentPlayerRoll());
    }

    //Issue #12
    @Test
    void store_multiple_player_rolls() {
        Game winningGame = new GameBuilder()
                .withPlayers(2)
                .withBoardSize(5)
                .build();

        //Confirms if Game is storing both dice rolls after current player roll but before next player roll.
        winningGame.moveCurrentPlayer(4);
        Assertions.assertEquals(4, winningGame.getCurrentPlayerRoll());

        winningGame.moveCurrentPlayer(5);
        Assertions.assertEquals(5, winningGame.getCurrentPlayerRoll());
    }

    //Issue #13
    @Test
    void check_if_player_position_goes_higher_than_winning_square () {
        Game winningGame = new GameBuilder()
                .withPlayers(1)
                .withBoardSize(5)
                .build();

        winningGame.moveCurrentPlayer(5);
        winningGame.moveCurrentPlayer(5);
        winningGame.moveCurrentPlayer(5);
        winningGame.moveCurrentPlayer(5);
        winningGame.moveCurrentPlayer(6);

        if(winningGame.getCurrentPlayer().getPosition().get() == 26) {
            Assertions.assertEquals(26, winningGame.getCurrentPlayer().getPosition().get());
        }
    }

    @Test
    void check_if_player_boundary_is_true_when_inside_board_area() {
        Game winningGame = new GameBuilder()
                .withPlayers(1)
                .withBoardSize(5)
                .build();

        winningGame.moveCurrentPlayer(5);

        Assertions.assertTrue(winningGame.getCurrentPlayer().getinsideBoardArea());
    }

    @Test
    void check_if_player_boundary_changes_to_false_when_outside_board_area() {
        Game winningGame = new GameBuilder()
                .withPlayers(1)
                .withBoardSize(5)
                .build();

        winningGame.moveCurrentPlayer(5);
        winningGame.moveCurrentPlayer(5);
        winningGame.moveCurrentPlayer(5);
        winningGame.moveCurrentPlayer(5);

        Assertions.assertTrue(winningGame.getCurrentPlayer().getinsideBoardArea());

        winningGame.moveCurrentPlayer(6);

        Assertions.assertFalse(winningGame.getCurrentPlayer().getinsideBoardArea());
    }


//    @Test
}
