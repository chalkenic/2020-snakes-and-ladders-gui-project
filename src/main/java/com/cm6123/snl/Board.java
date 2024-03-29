package com.cm6123.snl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class Board {
  /**
   * width of the board.
   */
  private final Integer width;
  /**
   * internal set of Squares representing the board.
   */
  private final List<Square> squares;
  /**
   * Internal set of special (snake or ladder) squares.
   */
  private final Set<Integer> specials;
  /**
   * List of positions where snake head squares reside.
   */
  private final List<Integer> snakeHeadList;
  /**
   * List of positions where ladder foot squares reside.
   */
  private final List<Integer> ladderFootList;
  /**
   * List of positions where boost squares reside.
   */
  private final List<Integer> boostList;

    /**
     * States default dice size for determining boost squares.
     */
  private final Integer standardDieSize = 6;

  /**
   * @param aWidth - the width of the board (remember it is square)
   * @param snakes - the snakes in pairs (head, tail, head, tail)
   * @param ladders - the ladders in pairs (foot, top, foot, top)
   */
  public Board(
          final Integer aWidth,
          final Integer[] snakes,
          final Integer[] ladders) {

    this.width = aWidth;
    squares = new ArrayList<>();
    for (int w = 0; w < aWidth; w++) {
      for (int h = 0; h < aWidth; h++) {
        squares.add(new Square(this, (w * aWidth) + h));
      }
    }

    setLastSquareAsWinner();

    specials = new HashSet<>();

    snakeHeadList = new ArrayList<>();
    ladderFootList = new ArrayList<>();
    boostList = new ArrayList<>();


    addSnakesAndLadders(snakes, ladders);
  }

  /**
   * Create Board with additional Boost squares.
   * @param aWidth - Width of the board.
   * @param snakes - the snakes in pairs (head, tail, head, tail)
   * @param ladders - the ladders in pairs (foot, top, foot, top)
   * @param boosts - boosts as separate squares.
   */
  public Board(
          final Integer aWidth,
          final Integer[] snakes,
          final Integer[] ladders,
          final Integer[] boosts) {

    this.width = aWidth;
    squares = new ArrayList<>();
    for (int w = 0; w < aWidth; w++) {
      for (int h = 0; h < aWidth; h++) {
        squares.add(new Square(this, (w * aWidth) + h));
      }
    }

    setLastSquareAsWinner();

    specials = new HashSet<>();

    snakeHeadList = new ArrayList<>();
    ladderFootList = new ArrayList<>();
    boostList = new ArrayList<>();
    addInteractiveSquares(snakes, ladders, boosts);
  }

  /**
   * Create a board with the given width.  It will be a square board.
   *
   * @param aWidth - the width of the board
   */
  public Board(final Integer aWidth) {

    this(aWidth, new Integer[0], new Integer[0]);

  }

  /**
   * @return the size of the board = the number of squares.
   */
  public Integer size() {
    return squares.size();
  }
  /**
   * @return returns the starting square.
   */
  public Position start() {
    return new Position(squares.get(0).getNumber());
  }

  /**
   * @param from the starting position.
   * @param roll the number of squares to move (i.e. the dice roll).
   * @return the new position after moving.
   */
  Position move(final Position from, final Integer roll) {
    Integer tempMove = from.get() + roll;
    if (tempMove > squares.size() - 1) {
      return new Position((squares.get(size() - 1)).getNumber());
    } else {
      Position currentPosition = new Position(squares.get(tempMove).destination().getNumber());
//      return new Position(squares.get(tempMove).destination().getNumber());
      //Check if new position is Boost square in order to move player further.
      return queryPlayerSquare(currentPosition, roll);
    }
  }

  /**
   * Check's player's position on board to determine if Boost square.
   * @param position - player's position to be queried.
   * @param roll - player's original roll for possible incrementing
   * @return function calls itsel recursively until player no longer on boost square.
   */
  private Position queryPlayerSquare(final Position position, final Integer roll) {
    Integer currentPosition = position.get();

    if ((squares.get(currentPosition).isBoostSquare())) {

      Integer newMove = position.get() + roll;
      Position newPosition = new Position(squares.get(newMove).destination().getNumber());
      return queryPlayerSquare(newPosition, roll);
    } else {
        return position;

    }
  }
  /**
   * set the last square on the board to be the winning square.
   * Landing on this square means that the player has won.
   */
  private void setLastSquareAsWinner() {
    squares.get(squares.size() - 1).setAsWinningSquare();
  }

  /**
   * Returns true if the passed position is a winning position.
   *
   * @param aPosition - a position of a player
   * @return true if the passed position is a winning position.
   */
  boolean isWinningPosition(final Position aPosition) {
    return squares.get(aPosition.get()).isWinningSquare();
  }

  private void addSnakesAndLadders(
          final Integer[] snakes,
          final Integer[] ladders) {
    addSnakes(snakes);
    addLadders(ladders);
  }

  private void addInteractiveSquares(
          final Integer[] snakes,
          final Integer[] ladders,
          final Integer[] boosts) {
    addSnakes(snakes);
    addLadders(ladders);
    addBoosts(boosts);
  }


  private void addLadders(final Integer[] ladders) {
    for (Integer ladder = 0; ladder <= ladders.length - 1; ladder += 2) {
      this.addLadder(ladders[ladder], ladders[ladder + 1]);
    }
  }

  private void addSnakes(final Integer[] snakes) {
    for (Integer snake = 0; snake <= snakes.length - 1; snake += 2) {
      this.addSnake(snakes[snake], snakes[snake + 1]);
    }
  }

  private void addBoosts(final Integer[] boosts) {
    for (Integer boost = 0; boost < boosts.length; boost++) {
      if (boosts[boost] < squares.size() - standardDieSize) {
        this.addBoost(boosts[boost]);
      } else {
          System.out.println("Boost square at position " + boosts[boost]
                  + " cannot be added within final 6 spaces from\nwinning square ("
                  + squares.size() + ")");
      }
    }
  }

  private void addBoost(final Integer boost) {
    if (specials.contains(boost)) {
      throw new IllegalStateException("Boost cannot clash with other interactive squares");
    }

    specials.add(boost);
    boostList.add(boost);
    Square boostSquare = squares.get(boost);
    Square newBoost = new BoostSquare(this, boost);

    //Flags square as Boost for determining movement on board.
    squares.get(newBoost.getNumber()).setAsBoostSquare();
    this.setSquareAt(boost, boostSquare);
  }


  private void addSnake(final Integer head, final Integer tail) {
    if (specials.contains(head) || specials.contains(tail)) {
      throw new IllegalStateException("Snakes and Ladders can't clash");
    }

    if (head == tail) {
      throw new IllegalStateException("Snake can't go to itself");
    }

    if (head < tail) {
      throw new IllegalStateException("Snake can't go up");

    }
    snakeHeadList.add(head);
    specials.add(head);
    specials.add(tail);

    Square tailSquare = squares.get(tail);
    SnakeSquare snake = new SnakeSquare(this, head, tailSquare);
    this.setSquareAt(head, snake);

  }

  private void addLadder(final Integer foot, final Integer top) {
    if (specials.contains(foot) || specials.contains(top)) {
      throw new IllegalStateException("Snakes and Ladders can't clash");
    }

    if (foot == top) {
      throw new IllegalStateException("Ladder can't go to itself");
    }

    if (foot > top) {
      throw new IllegalStateException("Ladder can't go down");
    }

    ladderFootList.add(foot);
    specials.add(foot);
    specials.add(top);
    Square topSquare = squares.get(top);
    Square ladder = new LadderSquare(this, foot, topSquare);
    this.setSquareAt(foot, ladder);

  }

  private void setSquareAt(final Integer index, final Square newSquare) {
    squares.set(index, newSquare);
  }

  /**
   * method parses all snake head squares created.
   * @return list of snake head squares.
   */
  public List<Integer> getSnakeHeadList() {
    return snakeHeadList;
  }
  /**
   * method parses all ladder foot squares created.
   * @return list of ladder foot squares.
   */
  public List<Integer> getLadderFootList() {
    return ladderFootList;
  }
  /**
   * method parses all boost squares created.
   * @return list of boost squares.
   */
  public List<Integer> getBoostList() {
    return boostList;
  }

  /**
   * Locate where special square will lead player when landing on initial square location.
   * @param choice starting square.
   * @return destination of choice if landed upon.
   */
  public Integer getSquareDestination(final Integer choice) {
    Integer squareChoice = null;
    for (Square s : squares) {
      //Cycles through squares and selects one matching against parameter.
      if (s.getNumber() == choice) {
        squareChoice = squares.get(choice).destination().getNumber();
      }
    } return squareChoice;
  }
  /**
   * Get the winning square on board.
   * @return the penultimate (winning) square.
   */
  public Integer getWinningSquare() {
    return squares.get(size() - 1).getNumber();
  }

  /**
   * @return getSpecials - all interactive squares.
   */
  Set<Integer> getSpecials() {
    return specials;
  }
}


