package com.cm6123.snl.dice;

import com.cm6123.snl.Game;
import com.cm6123.snl.GameBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FeatureBoostSquareTest {

    @Test
    void store_player_roll() {

        Game boostGame = new GameBuilder()
                .withPlayers(1)
                .withBoardSize(5)
                .build();

        boostGame.moveCurrentPlayer(5);

        Assertions.assertEquals(5, boostGame.getCurrentPlayerRoll());
    }
}
