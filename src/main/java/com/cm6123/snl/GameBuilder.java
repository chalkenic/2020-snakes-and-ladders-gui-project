package com.cm6123.snl;

import java.util.Arrays;

public final class GameBuilder {

  /**
   * If not specified, board will be 10 x 10.
   */
  private static final Integer STANDARD_BOARD_WIDTH = 10;
  /**
   * The board must be at least 4 x 4 in size.
   */
  private static final Integer MINIMUM_BOARD_WIDTH = 4;
  /**
   * If not specified, there will be 2 players.
   */
  private static final Integer DEFAULT_NUMBER_OF_PLAYERS = 2;
  /**
   * The minimum number of players is 1.
   */
  private static final Integer MINIMUM_NUMBER_OF_PLAYERS = 1;
  /**
   * The maximum number of players is determined by the available number of colours.
   */
  private static final Integer MAXIMUM_NUMBER_OF_PLAYERS = Player.PlayerColour.values().length;

  /**
   * An array of integers that, in pairs, specifies the ladders.
   */
  private Integer[] ladders;
  /**
   * An array of integers that, in pairs, specifies the snakes.
   */
  private Integer[] snakes;

  /**
   * A temporary variable that holds the board size until the game is built.
   */
  private Integer tempBoardSize;
  /**
   * A temporary variable that holds the number of players until the game is built.
   */
  private Integer tempPlayers;

  /**
   * Temporary variable that defaults winning Square Only feature as false until game is built.
   */
  private Boolean winningSquareOnly = true;
  /**
   * Initialise the Game building process.
   */
  public GameBuilder() {
    tempPlayers = DEFAULT_NUMBER_OF_PLAYERS;
    tempBoardSize = STANDARD_BOARD_WIDTH;
    snakes = new Integer[0];
    ladders = new Integer[0];
  }

  /**
   * Use the set temporary variables to build a game.
   *
   * @return a Game object configured which board, snakes, ladders and players.
   */
  public Game build() {
    Game theGame = new Game(tempPlayers, tempBoardSize, snakes, ladders);
    return theGame;
  }

  /**
   * regular build variables with addition of winning Square confirmation to build game.
   * @return configured game object with board, snakes, ladders, players & winning square.
   */
  public Game buildWithWinningSquare() {
    Game theWinningSquareGame = new Game(tempPlayers, tempBoardSize, snakes, ladders, winningSquareOnly);
    return theWinningSquareGame;
  }

  /**
   * Set the board size (width and height).
   * @param boardSize the width (or height) of the board.
   * @return the configued GameBuilder.
   */
  public GameBuilder withBoardSize(final Integer boardSize) {
    if (boardSize >= MINIMUM_BOARD_WIDTH) {
      tempBoardSize = boardSize;
      return this;
    } else {
      throw new IllegalArgumentException("The board must be " + MINIMUM_BOARD_WIDTH + " squares wide.");
    }

  }

  /**
   * set the number of players in the game.
   * @param players how many players in the game.
   * @return the configured GameBuilder.
   */
  public GameBuilder withPlayers(final Integer players) {
    if ((players >= MINIMUM_NUMBER_OF_PLAYERS) && (players <= MAXIMUM_NUMBER_OF_PLAYERS)) {
      tempPlayers = players;
      return this;
    } else {
      throw new IllegalArgumentException("The number of players must be between " + MINIMUM_NUMBER_OF_PLAYERS + " and " + MAXIMUM_NUMBER_OF_PLAYERS + ".");
    }

  }

  /**
   * set the snakes.
   * Each pair specifies a snakes.
   * The snake must descend and neither the head or the tail can already be used as a snake or ladder.
   * @param someSnakes - each pair specified the head and tail of a snake
   * @return the configured GameBuilder.
   */
  public GameBuilder withSnakes(final Integer... someSnakes) {
    if (someSnakes.length % 2 == 1) {
      System.out.println("Ignoring last data point");
      this.snakes = Arrays.copyOf(someSnakes, someSnakes.length - 1);
    } else {
      this.snakes = someSnakes;
    }

    return this;
  }

  /**
   * set the ladders.
   * Each pair specifies a ladder.
   * The ladder must ascend and neither the foot or top can already be used as a snake or ladder.
   * @param someLadders - each pair specified the foot and top of a ladder
   * @return the configured GameBuilder.
   */
  public GameBuilder withLadders(final Integer... someLadders) {
    if (someLadders.length % 2 == 1) {
      System.out.println("Ignoring last data point");
      this.ladders = Arrays.copyOf(someLadders, someLadders.length - 1);
    } else {
      this.ladders = someLadders;
    }
    return this;
  }

  /**
   * Checks Game to confirm if winning square only feature has been switched on.
   * @return winningSquare only value to inform game during player movement.
   */
  public Boolean isWinningSquareOn() {
    return winningSquareOnly;
  }

}
