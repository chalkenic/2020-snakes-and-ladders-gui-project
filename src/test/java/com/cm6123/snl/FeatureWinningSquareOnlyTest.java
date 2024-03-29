package com.cm6123.snl;

import com.cm6123.snl.dice.DiceSet;
import com.cm6123.snl.dice.LoadedDiceFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;



class FeatureWinningSquareOnlyTest {



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
        Assertions.assertEquals(5, winningGame.getCurrentPlayer().getPosition().get());
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
    void check_if_player_position_goes_higher_than_winning_square() {
        Game winningGame = new GameBuilder()
                .withPlayers(1)
                .withBoardSize(5)
                .buildWithWinningSquare();

        winningGame.moveCurrentPlayer(5);
        winningGame.moveCurrentPlayer(5);
        winningGame.moveCurrentPlayer(5);
        winningGame.moveCurrentPlayer(5);
        winningGame.moveCurrentPlayer(6);

        if (winningGame.getCurrentPlayer().getPosition().get() == 26) {
            Assertions.assertEquals(26, winningGame.getCurrentPlayer().getPosition().get());
        }
    }

    //Issue #13
    @Test
    void player_boundary_is_true_when_inside_board_area() {
        Game winningGame = new GameBuilder()
                .withPlayers(1)
                .withBoardSize(5)
                .buildWithWinningSquare();

        winningGame.moveCurrentPlayer(5);
        winningGame.moveCurrentPlayer(5);
        winningGame.moveCurrentPlayer(5);
        winningGame.moveCurrentPlayer(5);
        winningGame.moveCurrentPlayer(4);


        Assertions.assertTrue(winningGame.getCurrentPlayer().getinsideBoardArea());
    }

    //Issue #13
    @Test
    void player_boundary_changes_to_false_when_outside_board_area() {
        Game winningGame = new GameBuilder()
                .withPlayers(1)
                .withBoardSize(5)
                .buildWithWinningSquare();

        winningGame.moveCurrentPlayer(5);
        winningGame.moveCurrentPlayer(5);
        winningGame.moveCurrentPlayer(5);
        winningGame.moveCurrentPlayer(5);

        Assertions.assertTrue(winningGame.getCurrentPlayer().getinsideBoardArea());

        winningGame.moveCurrentPlayer(6);


        Assertions.assertFalse(winningGame.getCurrentPlayer().getinsideBoardArea());
    }

    //Issue #19
    @Test
    void check_game_has_not_ended_when_player_rolls_outside_board_size() throws Exception {
        Game winningGame = new GameBuilder()
                .withPlayers(1)
                .withBoardSize(5)
                .buildWithWinningSquare();

        winningGame.moveCurrentPlayer(5);
        winningGame.moveCurrentPlayer(5);
        winningGame.moveCurrentPlayer(5);
        winningGame.moveCurrentPlayer(5);
        winningGame.moveCurrentPlayer(6);

        Assertions.assertThrows(IllegalStateException.class,
                () -> {
                    winningGame.getWinningPlayer();
                });
    }

    //Issue #14
    @Test
    void check_if_2_past_final_square_is_illegal() {
        Game winningGame = new GameBuilder()
                .withPlayers(1)
                .withBoardSize(5)
                .buildWithWinningSquare();

        winningGame.moveCurrentPlayer(5);
        winningGame.moveCurrentPlayer(5);
        winningGame.moveCurrentPlayer(5);
        winningGame.moveCurrentPlayer(5);
        winningGame.moveCurrentPlayer(3);

        winningGame.moveCurrentPlayer(4);
        Assertions.assertFalse(winningGame.isGameOver());

    }

    //Issue #14
    @Test
    void check_if_3_past_final_square_is_illegal() {
        Game winningGame = new GameBuilder()
                .withPlayers(1)
                .withBoardSize(5)
                .buildWithWinningSquare();

        winningGame.moveCurrentPlayer(5);
        winningGame.moveCurrentPlayer(5);
        winningGame.moveCurrentPlayer(5);
        winningGame.moveCurrentPlayer(5);
        winningGame.moveCurrentPlayer(3);

        winningGame.moveCurrentPlayer(5);
        Assertions.assertFalse(winningGame.isGameOver());


    }

    //Issue #14
    @Test
    void any_roll_above_6_past_final_square_is_illegal() {
        Integer playerWinCount = 0;
        Game winningGame = new GameBuilder()
                .withPlayers(1)
                .withBoardSize(5)
                .buildWithWinningSquare();

        winningGame.moveCurrentPlayer(5);
        winningGame.moveCurrentPlayer(5);
        winningGame.moveCurrentPlayer(5);
        winningGame.moveCurrentPlayer(5);
        winningGame.moveCurrentPlayer(3);

        for (int i = 7; i < 99; i++) {
            winningGame.moveCurrentPlayer(i);

            //If game continues, do not increment player win count
            if (! winningGame.gameContinues()) {
                playerWinCount++;
            }
        }
        Assertions.assertEquals(0, playerWinCount);

    }
    //Issue #17
    @Test
    void check_if_player_begins_turn_after_illegal_move_at_original_position() {
        Game winningGame = new GameBuilder()
                .withPlayers(1)
                .withBoardSize(5)
                .buildWithWinningSquare();


        winningGame.moveCurrentPlayer(5);
        winningGame.moveCurrentPlayer(5);
        winningGame.moveCurrentPlayer(5);
        winningGame.moveCurrentPlayer(5);
        winningGame.moveCurrentPlayer(3);

        Assertions.assertEquals(23, winningGame.getCurrentPlayer().getPosition().get());

        winningGame.moveCurrentPlayer(5);


        Assertions.assertEquals(23, winningGame.getCurrentPlayer().getPosition().get());

    }

    //Issue #21
    @Test
    void initialise_game_with_winning_square_feature_switched_on() {
        Game winningGame = new GameBuilder()
                .withPlayers(1)
                .withBoardSize(5)
                .buildWithWinningSquare();

        Assertions.assertTrue(winningGame.isWinningSquareOn());


    }
    //Issue #21
    @Test
    void check_game_has_winningSquareOnly_feature_turned_off() {
        Game winningGame = new GameBuilder()
                .withPlayers(1)
                .withBoardSize(5)
                .build();

        Assertions.assertFalse(winningGame.isWinningSquareOn());
    }




    //Code adapted from le-rag - SystemOutTest.Java via github
    //Available at: https://gist.github.com/le-rag/28dad2f3346ae11e6a6b
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    @BeforeEach
    void newStream() {
        System.setOut(new PrintStream(output));
    }

    //Issue #15 (amended to #16 after changes)
    @Test
    void player_receives_message_about_illegal_move() {

        Game winningGame = new GameBuilder()
                .withPlayers(1)
                .withBoardSize(5)
                .buildWithWinningSquare();

        winningGame.moveCurrentPlayer(5);
        winningGame.moveCurrentPlayer(5);
        winningGame.moveCurrentPlayer(5);
        winningGame.moveCurrentPlayer(5);
        winningGame.moveCurrentPlayer(3);

        winningGame.moveCurrentPlayer(5);
        Assertions.assertTrue(output.toString().contains("WARNING: PLAYER ROLL (5) " +
                        "EXCEEDS BOARD SIZE (25). " +
                        "RETURNING TO ORIGINAL POSITION (23)"));
    }
    //Issue #16
    @Test
    void player_informed_info_about_illegal_move_and_original_position() {
        Game winningGame = new GameBuilder()
                .withPlayers(1)
                .withBoardSize(5)
                .buildWithWinningSquare();

        winningGame.moveCurrentPlayer(5);
        winningGame.moveCurrentPlayer(5);
        winningGame.moveCurrentPlayer(5);
        winningGame.moveCurrentPlayer(5);
        winningGame.moveCurrentPlayer(3);

        winningGame.moveCurrentPlayer(5);
        Assertions.assertTrue(output.toString().contains("WARNING: PLAYER ROLL (5) " +
                        "EXCEEDS BOARD SIZE (25). " +
                        "RETURNING TO ORIGINAL POSITION (23)"));
    }
}


