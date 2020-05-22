package com.cm6123.snl;

import java.util.List;
import java.util.Set;

public final class Game {

    /**
     * The board on which the game is being played.
     */
    private Board board;
    /**
     * The list of players playing the game.
     */
    private PlayerList players;

    /**
     * Confirmation of whether Boost square is switched on in game. Defaults false.
     */

    private Boolean boostSquareOn = false;

    /**
     * Confirmation of whether winning square is switched on in game.
     */
    private Boolean winningSquareOn;
    /**
     * Stores player's roll when taking an action in moveCurrentPlayer roll.
     */
    private Integer currentPlayerRoll;

    /**
     * Stores accumulated player roll when using Boost Square feature.
     */
    private Integer accumulatedPlayerRoll = 0;

    /**
     * Builds the game.
     * @param playerCount - how many players in game.
     * @param width - size of the board.
     * @param snakes - snakes to be added onto board.
     * @param ladders - ladders to be added onto board.
     * @param boosts - boosts to be added onto board.
     */

    Game(final Integer playerCount,
         final Integer width,
         final Integer[] snakes,
         final Integer[] ladders,
         final Integer[] boosts
    ) {
        board = new Board(width, snakes, ladders, boosts);
        players = new PlayerList(playerCount, board.start());

    }
    /**
     * get the current roll saved in the game.
     * @return Current integer.
     */
    public Integer getCurrentPlayerRoll() {
        return currentPlayerRoll;
    }

    /**
     * get the size of the board.
     * @return the board size.
     */
    public Integer numberOfSquares() {
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
     * Gets number of special squares in game.
     * @return All special square locations
     */
    public Set<Integer> getSpecials() {
        return board.getSpecials();
    }


    /**
     * Move the current player by a given number of squares.
     * @param squares the number of squares to move by - typically the value of the roll of the dice.
     */
    public void moveCurrentPlayer(final Integer squares) {
        currentPlayerRoll = squares;
        if (isGameOver()) {
            throw new IllegalStateException("Can't move a player once the game is over.");
        } else {
            //change this to asking the board to provide the destination and then move the player there.
            Player currentPlayer = getCurrentPlayer();


            //If winning Square Only feature switched on, player's position with new roll queried for legality.
            if (winningSquareOn) {
                if (checkIfLegalPosition(currentPlayer, squares)) {
                    //Player moves as normal if legal.
                    movePlayerPosition(currentPlayer, squares);
                } else {
                    //Player does not move on board if illegal; turn ends.
                    movePlayerPosition(currentPlayer, 0);
//            players.next();

                }
                //Game continues as normal if winning square feature off.
            } else {
                movePlayerPosition(currentPlayer, squares);
            }



            if (gameContinues()) {
                players.next();
            }
        }
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


    /**
     * Get an object of current game player.
     * @return a Player object containing game's current player.
     */
    public Player getCurrentPlayer() {
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
     * @return true if player still inside board boundaries.
     */
    private Boolean checkIfLegalPosition(final Player player, final Integer roll) {
        if ((player.getPosition().get() + roll) >= numberOfSquares()) {
            System.out.print("WARNING: PLAYER ROLL (" + roll + ") "
                    + "EXCEEDS BOARD SIZE (" + board.size() + "). "
                    + "RETURNING TO ORIGINAL POSITION (" + getCurrentPlayer().getPosition().get() + ")");
            player.setinsideBoardArea(false);
        } else {
            player.setinsideBoardArea(true);
        }
        return player.getinsideBoardArea();
    }

    /**
     * Moves player to their new position on board.
     * @param currentPlayer - current playing moving on board.
     * @param squares - Position on board for player to move to.
     */
    private void movePlayerPosition(final Player currentPlayer, final Integer squares) {

        //Add diceroll into Current game roll for determining whether player has passed winning position.
        Position newPosition = board.move(currentPlayer.getPosition(), squares);
        accumulatedPlayerRoll = newPosition.get() - currentPlayer.getPosition().get();
        System.out.println("Turn distance: " + accumulatedPlayerRoll + "| New position: " + newPosition.get());
        currentPlayer.moveTo(newPosition);

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
    Boolean gameContinues() {
        return !isGameOver();
    }

    /**
     * Gets confirmation of whether WinningSquareOnly feature turned on.
     * @return true if winning square on.
     */
    public Boolean isWinningSquareOn() {
        return winningSquareOn;
    }

    /**
     * Changes game to WinningSquareOnly feature.
     * @param confirmation - whether winning square is to be used.
     */
    void setWinningSquareOnly(final Boolean confirmation) {
        winningSquareOn = confirmation;
    }

    /**
     * Checm whether boost Square feature switched on.
     * @return boostSquareOn - true if Boost switched on.
     */
    public Boolean getBoostSquareOn() {
        return boostSquareOn;
    }

    void setBoostSquareOn() {
        boostSquareOn = true;
    }

    /**
     * Calls player's sum total roll for addition purposes.
     * @return accumulatedPlayerRoll - total value of roll at present point.
     */
    public Integer getAccumulatedPlayerRoll() {
        return accumulatedPlayerRoll;
    }

    /**
     * Grab the specific player in game.
     * @param player - the number specified.
     * @return Player object
     */
    public Player getPlayer(final Integer player) {
        return players.get(player);
    }
}

