package com.cm6123.snl;

import java.util.List;

public final class Game {

  /**
   * The board on which the game is being played.
   */
  private Board board;
  /**
   * The list of players playing the game.
   */
  private PlayerList players;

  Game(final Integer playerCount,
       final Integer width,
       final Integer[] snakes,
       final Integer[] ladders
  ) {

    board = new Board(width, snakes, ladders);
    players = new PlayerList(playerCount, board.start());

  }


  /**
   * Stores player's roll when taking an action in moveCurrentPlayer roll.
   */
  private Integer currentPlayerRoll;

  /**
   * get the current roll saved in the game.
   * @return Current integer.
   */
  public Integer getCurrentPlayerRoll() {
    return currentPlayerRoll;
  }

  Integer numberOfSquares() {
    return board.size();
  }

  /**
   * Get the number of players in the game.
   *
   * @return the number of players in the game.
   */
  public Integer numberOfPlayers() {
    return players.size();
  }

  List<Player> getPlayers() {
    return players.asList();
  }

  /**
   * Move the current player by a given number of squares.
   * @param squares the number of squares to move by - typically the value of the roll of the dice.
   */
  public void moveCurrentPlayer(final Integer squares) {
    if (isGameOver()) {
      throw new IllegalStateException("Can't move a player once the game is over.");
    } else {
      //change this to asking the board to provide the destination and then move the player there.
      Player currentPlayer = getCurrentPlayer();
      currentPlayerRoll = squares;

      if (checkPosition(currentPlayer, squares)) {

        //Add diceroll into Current game roll for determining whether player has passed winning position.
        Position newPosition = board.move(currentPlayer.getPosition(), squares);
        currentPlayer.moveTo(newPosition);


      }
      if (gameContinues()) {
        players.next();
      }
    }
  }

  /**
   * Returns player's roll for testing.
   * @param roll - player's roll on turn
   * @return value of roll.
   */
  public Integer getRoll(final Integer roll) {
    return roll;
  }

  /**
   * Get a read-only object for the players.
   * @param index - which player
   * @return a PlayerData object containing read-only data about the Player.
   */
  public PlayerData getPlayerData(final Integer index) {
    return players.get(index).getPlayerData();
  }

  /**
   * Get a read-only object for the current player.
   * @return a PlayerData object containing read-only data about the current player.
   */
  public PlayerData getPlayerData() {
    return players.getCurrentPlayer().getPlayerData();
  }

  Player getCurrentPlayer() {
    return players.getCurrentPlayer();
  }

  /**
   * Is the game over?
   * @return true if the game is over.
   */
  public Boolean isGameOver() {
    return board.isWinningPosition(getCurrentPlayer().getPosition());
  }

  /**
   * Get the winning player.
   * @return - Player data about the winning player
   */
  public PlayerData getWinningPlayer() {
    if (isGameOver()) {
      return getCurrentPlayer().getPlayerData();
    } else {
      throw new IllegalStateException("The Game isn't over.");
    }
  }

  /**
   * Checks position of player on board to confirm whether roll leaves them inside boundaries or not.
    * @param player - current player making a roll on Board.
   * @param roll - the value of player's roll.
   * @return
   */
  public Boolean checkPosition(final Player player, final Integer roll) {
    if ((player.getPosition().get() + roll) > numberOfSquares()) {
      System.out.println("WARNING: PLAYER ROLL EXCEEDS BOARD.");
      player.setinsideBoardArea(false);
    } else {
      player.setinsideBoardArea(true);
    }
    return player.getinsideBoardArea();
  }

  /**
   * Get the board being played on.
   * MAYBE THIS SHOULD BE A READ-ONLY REPRESENTATION?
   * @return the board
   */
  public Board getBoard() {
    return board;
  }

  /**
   * shoudl the game carry on?
   * @return true if the game is not over.
   */
  private Boolean gameContinues() {
    return !isGameOver();
  }


}
