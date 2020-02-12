package com.cm6123.snl;

import com.cm6123.snl.dice.DiceSet;
import com.cm6123.snl.dice.LoadedDiceFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayGame {




  @Test
  public void move_player_by_two() throws Exception {
    //Simple test to draw out the required interface.
    //On each turn, we want the current player to be moved.
    //Therefore, the game needs to know the current player which it doesn't at the moment.
    //We'll defer dice rolling for now.
    //After the player is moved, the game should know who the next player to move is,
    //but maybe we just test that by rotating through the players
    Game theGame = new GameBuilder()
            .withPlayers(2)
            .withBoardSize(4)
            .build();
    theGame.moveCurrentPlayer(2);
    Assertions.assertEquals(2, theGame.getPlayerData(0).getPosition().get());
    Assertions.assertEquals(0, theGame.getPlayerData(1).getPosition().get());

  }

  @Test
  public void move_each_player_by_one_then_two() throws Exception {
    Game theGame = new GameBuilder()
            .withPlayers(2)
            .withBoardSize(4)
            .build();
    //this draws out "next player" functionality

    theGame.moveCurrentPlayer(1);
    theGame.moveCurrentPlayer(1);
    theGame.moveCurrentPlayer(2);
    theGame.moveCurrentPlayer(2);

    Assertions.assertEquals(3, theGame.getPlayerData(0).getPosition().get());
    Assertions.assertEquals(3, theGame.getPlayerData(1).getPosition().get());

  }

  @Test
  public void move_player_to_winning_position() throws Exception {
    Game theGame = new GameBuilder()
            .withPlayers(2)
            .withBoardSize(4)
            .build();
    //this draws out "next player" functionality

    theGame.moveCurrentPlayer(6);
    theGame.moveCurrentPlayer(1);
    theGame.moveCurrentPlayer(6);
    theGame.moveCurrentPlayer(1);
    theGame.moveCurrentPlayer(3);

    Assertions.assertEquals(15, theGame.getPlayerData(0).getPosition().get());
    Assertions.assertTrue(theGame.isGameOver());
    Assertions.assertEquals(theGame.getPlayerData(0), theGame.getWinningPlayer());


  }


  @Test
  public void move_player_beyond_last_square() throws Exception {
    Game theGame = new GameBuilder()
            .withPlayers(2)
            .withBoardSize(4)
            .build();

    //this draws out "next player" functionality

    theGame.moveCurrentPlayer(6);
    theGame.moveCurrentPlayer(1);
    theGame.moveCurrentPlayer(6);
    theGame.moveCurrentPlayer(1);
    theGame.moveCurrentPlayer(5);

    Assertions.assertEquals(15, theGame.getPlayerData(0).getPosition().get());
    Assertions.assertTrue(theGame.isGameOver());
    Assertions.assertEquals(theGame.getPlayerData(0), theGame.getWinningPlayer());


  }

  @Test
  public void add_simple_snake_and_move_back() throws Exception {

    Game theGame = new GameBuilder()
            .withBoardSize(4)
            .withPlayers(2)
            .withSnakes(5, 3)
            .build();



    Board theBoard = theGame.getBoard();
    theGame.moveCurrentPlayer(5);
    Assertions.assertEquals(3, theGame.getPlayerData(0).getPosition().get());


  }

  @Test
  public void add_simple_ladder_and_move_forward() throws Exception {

    Game theGame = new GameBuilder()
            .withBoardSize(4)
            .withPlayers(2)
            .withLadders(3, 5)
            .build();

    theGame.moveCurrentPlayer(3);
    Assertions.assertEquals(5, theGame.getPlayerData(0).getPosition().get());

  }

  @Test
  public void game_on_configured_board() throws Exception {

    Game newGame = new GameBuilder()
            .withBoardSize(10)
            .withPlayers(2)
            .withLadders(6, 90)
            .withSnakes(92, 80)
            .build();

    newGame.moveCurrentPlayer(6); //player 1 moves to 90
    newGame.moveCurrentPlayer(5); //player 2 moves to 5
    newGame.moveCurrentPlayer(2); //player 1 moves to 80
    newGame.moveCurrentPlayer(2); //player 2 moves to 7
    newGame.moveCurrentPlayer(6); //player 1 moves to 86
    newGame.moveCurrentPlayer(2);
    newGame.moveCurrentPlayer(5); //player 1 moves to 91
    newGame.moveCurrentPlayer(2);
    newGame.moveCurrentPlayer(5); //player 1 moves to 96
    newGame.moveCurrentPlayer(2);
    newGame.moveCurrentPlayer(4); //player 1 moves to 100 and wins


    Assertions.assertTrue(newGame.isGameOver());


  }

  @Test
  public void game_on_more_configured_board() throws Exception {

    Game newGame = new GameBuilder()
            .withBoardSize(10)
            .withPlayers(2)
            .withLadders(6, 10, 15, 20, 25, 97)
            .withSnakes(92, 80)
            .build();

    //player 0 is the current player
    Assertions.assertEquals(Player.PlayerColour.RED, newGame.getCurrentPlayer().getColour());

    newGame.moveCurrentPlayer(6); //player 1 moves to 10
    Assertions.assertEquals(Player.PlayerColour.BLUE, newGame.getCurrentPlayer().getColour());

    newGame.moveCurrentPlayer(5); //player 2 moves to 5 and the current player is back the player 0
    Assertions.assertEquals(10, newGame.getCurrentPlayer().getPosition().get());

    Assertions.assertEquals(Player.PlayerColour.RED, newGame.getCurrentPlayer().getColour());

    newGame.moveCurrentPlayer(5); //player 1 moves to 20
    newGame.moveCurrentPlayer(2); //player 2 moves to 7
    newGame.moveCurrentPlayer(5); //player 1 moves to 95
    newGame.moveCurrentPlayer(2);
    newGame.moveCurrentPlayer(5); //player 1 moves to 100 and wins


    Assertions.assertTrue(newGame.isGameOver());


  }

  @Test
  public void game_with_loaded_dice() throws Exception {

    Game newGame = new GameBuilder()
            .withBoardSize(4)
            .withPlayers(2)
            .build();

    DiceSet dices = new DiceSet(6, 1, new LoadedDiceFactory(4));
    newGame.moveCurrentPlayer(dices.roll().getValue());
    newGame.moveCurrentPlayer(dices.roll().getValue());
    newGame.moveCurrentPlayer(dices.roll().getValue());
    newGame.moveCurrentPlayer(dices.roll().getValue());
    newGame.moveCurrentPlayer(dices.roll().getValue());
    newGame.moveCurrentPlayer(dices.roll().getValue());
    newGame.moveCurrentPlayer(dices.roll().getValue());


    Assertions.assertTrue(newGame.isGameOver());


  }

  @Test
  public void game_with_loaded_variable_dice() throws Exception {

    Game newGame = new GameBuilder()
            .withBoardSize(6)
            .withPlayers(2)
            .build();

    DiceSet dices = new DiceSet(6, 1, new LoadedDiceFactory(3));
    newGame.moveCurrentPlayer(dices.roll().getValue());
    newGame.moveCurrentPlayer(dices.roll().getValue());
    newGame.moveCurrentPlayer(dices.roll().getValue());
    newGame.moveCurrentPlayer(dices.roll().getValue());
    newGame.moveCurrentPlayer(dices.roll().getValue());
    newGame.moveCurrentPlayer(dices.roll().getValue());

    Assertions.assertEquals(9, newGame.getPlayerData(0).getPosition().get());
    Assertions.assertEquals(9, newGame.getPlayerData(0).getPosition().get());


  }


}
