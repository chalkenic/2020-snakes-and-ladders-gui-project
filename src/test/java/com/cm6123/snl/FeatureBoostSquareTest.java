package com.cm6123.snl;

import com.cm6123.snl.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

class FeatureBoostSquareTest {

    @Test
    void store_player_roll() {

        Game boostGame = new GameBuilder()
                .withPlayers(1)
                .withBoardSize(5)
                .build();

        boostGame.moveCurrentPlayer(5);

        Assertions.assertEquals(5, boostGame.getCurrentPlayerRoll());
    }

    @Test
    void create_boost_square() {
        Board aBoard = new Board(5);
        Square boost = new BoostSquare(aBoard, 4);

        //Get label sources 1 above position due to zero-indexing.
        Assertions.assertEquals(5, Integer.parseInt(boost.getLabel()));
    }
    @Test
    void allow_boost_squares_on_board() {
        Game boostGame = new GameBuilder()
                .withPlayers(1)
                .withBoardSize(5)
                .withSnakes(11, 5)
                .withLadders(13, 18)
                .withBoosts(6)
                .build();

        Assertions.assertEquals(1, boostGame.numberOfPlayers());
        Assertions.assertEquals(25, boostGame.numberOfSquares());
        Assertions.assertTrue(boostGame.getSpecials().contains(6));
    }

    @Test
    void board_still_runs_without_boost_squares() {
        Game boostGame = new GameBuilder()
                .withPlayers(1)
                .withBoardSize(5)
                .withLadders(13, 18, 14, 21)
                .withSnakes(11, 5)
                .build();

        Assertions.assertEquals(1, boostGame.numberOfPlayers());
        Assertions.assertEquals(25, boostGame.numberOfSquares());

    }

    @Test
    void run_game_with_only_boost_squares() {
        Game boostGame = new GameBuilder()
                .withBoosts(6, 7)
                .build();

        Assertions.assertEquals(2, boostGame.numberOfPlayers());
        Assertions.assertEquals(100, boostGame.numberOfSquares());

        //Checks that both boost squares have been added into game.
        Assertions.assertTrue((
                boostGame.getSpecials().contains(6) &&
                boostGame.getSpecials().contains(7)));
    }
}
