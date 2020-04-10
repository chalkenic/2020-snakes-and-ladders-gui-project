package com.cm6123.snl.dice;

import com.cm6123.snl.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
}
