package com.cm6123.snl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FeatureWinningSquareTest {

    @Test
    void store_player_position_at_turn_start() {

        Game winningGame = new GameBuilder()
                .withPlayers(2)
                .withBoardSize(5)
                .build();

        winningGame.moveCurrentPlayer(5);
        winningGame.moveCurrentPlayer(4);

        Assertions.assertEquals(5,winningGame.getCurrentPlayer().getPosition().get());
    }
}
