package com.cm6123.snl;

import com.cm6123.snl.dice.DiceSet;
import com.cm6123.snl.dice.LoadedDiceFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FeatureWinningSquareTest {

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

    @Test
    void store_1_player_roll() {
        Game winningGame = new GameBuilder()
                .withPlayers(1)
                .withBoardSize(5)
                .build();


        winningGame.moveCurrentPlayer(3);
        Assertions.assertEquals(3, winningGame.getCurrentPlayerRoll());
    }

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
}
