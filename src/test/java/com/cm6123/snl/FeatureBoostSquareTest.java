package com.cm6123.snl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class FeatureBoostSquareTest {

    //Issue #37
    @Test
    void store_player_roll() {

        Game boostGame = new GameBuilder()
                .withPlayers(1)
                .withBoardSize(5)
                .build();

        boostGame.moveCurrentPlayer(5);

        Assertions.assertEquals(5, boostGame.getCurrentPlayerRoll());
    }

    //Issue #38
    @Test
    void create_boost_square() {
        Board aBoard = new Board(5);
        Square boost = new BoostSquare(aBoard, 4);

        //Get label sources 1 above position due to zero-indexing.
        Assertions.assertEquals(5, Integer.parseInt(boost.getLabel()));
    }

    //Issue 29
    @Test
    void allow_boost_squares_on_board_alongside_other_squares() {
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

    //Issue #29
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

    //Issue #30
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

    //Issue #28
    @Test
    void board_can_create_boost_squares_without_GameBuilder() {
        Board boostBoard = new Board(5, new Integer[]{}, new Integer[]{}, new Integer[]{6, 12});
        Assertions.assertTrue((boostBoard.getSpecials().contains(6) &&
                boostBoard.getSpecials().contains(12)));

    }

    //Issue #32
    @Test
    void snake_head_and_boosts_cannot_clash() {
        Assertions.assertThrows(IllegalStateException.class,
                () -> {
                    Board boostBoard = new Board(5, new Integer[]{9, 3}, new Integer[]{}, new Integer[]{9});
                });
    }

    //Issue #32
    @Test
    void snake_tail_and_boosts_cannot_clash() {
        Assertions.assertThrows(IllegalStateException.class,
                () -> {
                    Board boostBoard = new Board(5, new Integer[]{9, 3}, new Integer[]{}, new Integer[]{3});
                });
    }

    //Issue #31
    @Test
    void ladder_foot_and_boosts_cannot_clash() {
        Assertions.assertThrows(IllegalStateException.class,
                () -> {
                    Board boostBoard = new Board(5, new Integer[]{}, new Integer[]{11, 15}, new Integer[]{11});
                });
    }

    //Issue #31
    @Test
    void ladder_top_and_boosts_cannot_clash() {
        Assertions.assertThrows(IllegalStateException.class,
                () -> {
                    Board boostBoard = new Board(5, new Integer[]{}, new Integer[]{11, 15}, new Integer[]{15});
                });
    }

    //Issue #36
    @Test
    void boost_cannot_exist_within_6_squares_of_winning_square() {
        Board boostBoard = new Board(5, new Integer[]{}, new Integer[]{}, new Integer[]{19});
//        Assertions.assertFalse(boostBoard.getSpecials().contains(19));
    }

    //Issue #36
    @Test
    void boost_cannot_exist_on_and_beyond_winning_square() {
        Board boostBoard = new Board(5, new Integer[]{}, new Integer[]{}, new Integer[]{26});
        Assertions.assertFalse(boostBoard.getSpecials().contains(26));
    }

    //Issue #36
    @Test
    void illegal_boost_ignored_and_legal_boost_implemented() {
        Board boostBoard = new Board(5, new Integer[]{}, new Integer[]{}, new Integer[]{26, 5});
        Assertions.assertTrue((boostBoard.getSpecials().contains(5)
                && ! boostBoard.getSpecials().contains(26)));
    }

    //Issue #36
    @Test
    void boost_ignored_on_larger_board() {
        Board boostBoard = new Board(10, new Integer[]{}, new Integer[]{}, new Integer[]{95, 93});
        Assertions.assertFalse(boostBoard.getSpecials().contains(95));
    }

    //Issue #33
    @Test
    void player_roll_is_doubled_when_landing_on_boost_square() {
        Game boostGame = new GameBuilder()
                .withBoosts(4)
                .build();

        boostGame.moveCurrentPlayer(4);

        Assertions.assertEquals(8, boostGame.getAccumulatedPlayerRoll());
    }

    //Issue #33 - addition after merge.
    @Test
    void player_2_roll_is_doubled_when_landing_on_boost_square() {
        Game boostGame = new GameBuilder()
                .withPlayers(2)
                .withBoosts(4)
                .build();

        boostGame.moveCurrentPlayer(4);
        boostGame.moveCurrentPlayer(9);

        Assertions.assertEquals(9, boostGame.getAccumulatedPlayerRoll());
    }

    //Issue #34
    @Test
    void player_moves_to_doubled_roll_location_upon_landing_on_boost_square() {
        Game boostGame = new GameBuilder()
                .withPlayers(1)
                .withBoosts(4)
                .build();

        boostGame.moveCurrentPlayer(4);

        Assertions.assertEquals(8, boostGame.getCurrentPlayer().getPosition().get());
    }

    //Issue #34
    @Test
    void player_moves_to_triple_of_roll_location_upon_landing_on_cumulative_boost_squares() {
        Game boostGame = new GameBuilder()
                .withPlayers(1)
                .withBoosts(4, 7)
                .build();

        boostGame.moveCurrentPlayer(1);
        boostGame.moveCurrentPlayer(3);

        Assertions.assertEquals(10, boostGame.getCurrentPlayer().getPosition().get());
    }

    //Issue #34
    @Test
    void player_moves_to_position_6_times_of_original_roll() {
        Game boostGame = new GameBuilder()
                .withPlayers(1)
                .withBoosts(6, 12, 18, 24, 30)
                .withBoardSize(10)
                .build();

        boostGame.moveCurrentPlayer(6);

        Assertions.assertEquals(36, boostGame.getCurrentPlayer().getPosition().get());

    }

    //Issue #25
    @Test
    void player_moves_to_snake_tail_after_boosting_to_snake_head() {
        Game boostGame = new GameBuilder()
                .withPlayers(1)
                .withSnakes(16, 11)
                .withBoosts(12)
                .withBoardSize(5)
                .build();

        boostGame.moveCurrentPlayer(8);
        boostGame.moveCurrentPlayer(4);

        Assertions.assertEquals(11, boostGame.getCurrentPlayer().getPosition().get());
    }

    //Issue #25
    @Test
    void player_moves_to_snake_tail_after_boosting_twice_to_snake_head() {
        Game boostGame = new GameBuilder()
                .withPlayers(1)
                .withSnakes(18, 10)
                .withBoosts(6, 12)
                .withBoardSize(5)
                .build();

        boostGame.moveCurrentPlayer(6);

        Assertions.assertEquals(10, boostGame.getCurrentPlayer().getPosition().get());
    }

    //Issue #24
    @Test
    void player_moves_to_ladder_top_after_boosting_to_ladder_bottom() {
        Game boostGame = new GameBuilder()
                .withPlayers(1)
                .withLadders(12, 18)
                .withBoosts(6)
                .withBoardSize(5)
                .build();

        boostGame.moveCurrentPlayer(6);

        Assertions.assertEquals(18, boostGame.getCurrentPlayer().getPosition().get());
    }

    //Issue #24
    @Test
    void player_moves_to_ladder_top_after_boosting_twice_to_ladder_bottom() {
        Game boostGame = new GameBuilder()
                .withPlayers(1)
                .withLadders(12, 20)
                .withBoosts(6, 9)
                .withBoardSize(5)
                .build();

        boostGame.moveCurrentPlayer(3);
        boostGame.moveCurrentPlayer(3);

        Assertions.assertEquals(20, boostGame.getCurrentPlayer().getPosition().get());
    }


    //Code adapted from le-rag - SystemOutTest.Java via github
    //Available at: https://gist.github.com/le-rag/28dad2f3346ae11e6a6b
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    @BeforeEach
    void newStream() {
        System.setOut(new PrintStream(output));
    }

//    @AfterEach
//    void emptyStream() {
//        System.setOut(null);
//    }

    //Issue #26
    @Test
    void provide_total_roll_made_by_player_to_UI_without_boost() {
        Game boostGame = new GameBuilder()
                .withPlayers(1)
                .withBoardSize(5)
                .build();

        boostGame.moveCurrentPlayer(5);

        Assertions.assertTrue(output.toString().contains("distance: 5"));

    }
    //Issue #26
    @Test
    void provide_total_roll_made_by_player_to_UI_with_boost() {

        Game boostGame = new GameBuilder()
                .withPlayers(1)
                .withBoosts(4)
                .withBoardSize(5)
                .build();

        boostGame.moveCurrentPlayer(4);

        Assertions.assertTrue(output.toString().contains("distance: 8"));
    }
    //Issue #26
    @Test
    void provide_total_roll_made_by_player_to_UI_with_boost_and_snake() {
        Game boostGame = new GameBuilder()
                .withPlayers(1)
                .withBoosts(4)
                .withSnakes(8, 3)
                .withBoardSize(5)
                .build();

        boostGame.moveCurrentPlayer(4);

        Assertions.assertTrue(output.toString().contains("distance: 3"));
    }
    //Issue #26
    @Test
    void provide_total_roll_made_by_player_to_UI_with_boost_and_ladder() {
        Game boostGame = new GameBuilder()
                .withPlayers(1)
                .withBoosts(4)
                .withLadders(8, 12)
                .withBoardSize(5)
                .build();

        boostGame.moveCurrentPlayer(4);

        Assertions.assertTrue(output.toString().contains("distance: 12"));
    }
    //Issue #27
    @Test
    void provide_new_position_at_turn_end() {
        Game boostGame = new GameBuilder()
                .withPlayers(1)
                .withBoardSize(5)
                .build();

        boostGame.moveCurrentPlayer(4);

        Assertions.assertTrue(output.toString().contains("position: 4"));
    }

    //Issue #27
    @Test
    void provide_new_position_after_boost_movement_at_turn_end() {
        Game boostGame = new GameBuilder()
                .withPlayers(1)
                .withBoosts(5)
                .withBoardSize(5)
                .build();

        boostGame.moveCurrentPlayer(5);

        Assertions.assertTrue(output.toString().contains("position: 10"));
    }

    //Issue #27
    @Test
    void provide_new_position_after_boost_and_snake_movement_at_turn_end() {
        Game boostGame = new GameBuilder()
                .withPlayers(1)
                .withBoardSize(5)
                .withSnakes(9, 3)
                .withBoosts(5)
                .build();

        boostGame.moveCurrentPlayer(1);
        boostGame.moveCurrentPlayer(4);

        Assertions.assertTrue(output.toString().contains("position: 3"));
    }

    //Issue #27
    @Test
    void provide_new_position_after_boost_and_ladder_movement_at_turn_end() {
        Game boostGame = new GameBuilder()
                .withPlayers(1)
                .withBoardSize(5)
                .withLadders(8, 14)
                .withBoosts(5)
                .build();

        boostGame.moveCurrentPlayer(2);
        boostGame.moveCurrentPlayer(3);

        Assertions.assertTrue(output.toString().contains("position: 14"));
    }


}