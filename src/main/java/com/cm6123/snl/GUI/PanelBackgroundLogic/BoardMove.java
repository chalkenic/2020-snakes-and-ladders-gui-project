package com.cm6123.snl.GUI.PanelBackgroundLogic;

import com.cm6123.snl.*;
import com.cm6123.snl.GUI.GUIFrame;

import java.util.List;
/**
 * Class records a player's move on board & prints status to textPanel. moveCurrentPlayer action in game object
 * not called until end of logic.
 */
public class BoardMove {
    /**
     * holds original dice roll made by player.
     */
    private Integer diceRoll;
    /**
     * Current game object.
     */
    private Game currentGame;
    /**
     * Current player moving on board.
     */
    private Player currentPlayer;
    /**
     * temporary integer marking the player's temporary move on board - used for appending to TextPanel
     * a player's journey.
     */
    private Integer temporaryBoardPosition;
    /**
     * Current game board object.
     */
    private Board currentBoard;
    /**
     * JFrame window used for appending text onto textpanel.
     */
    private GUIFrame gameGui;
    /**
     * All squares part of Board object.
     */
    private List<Square> squareList;
    /**
     * Tracks if player hits multiple boost squares to ensure additional boost moves are calculated correctly.
     */
    private Integer boostAccumulator = 0;

    /**
     * Builds a new board movement tracking system onto RunGamePanel.
     * @param newGame - Current Game Object.
     * @param gui - JFrame window.
     */
    public BoardMove(final Game newGame, final GUIFrame gui) {
        this.currentGame = newGame;
        this.gameGui = gui;
        this.currentBoard = currentGame.getBoard(); //Assigns board in tracker to Game object's board.
    }

    /**
     * Master recursive method to track a player's move. recalls own method if a boost square is hit to follow a
     * player's additional travel distance.
     * @param diceValue - a player's default roll.
     * @param player - player to be moved.
     * @param position - player's current position.
     */
    public void movePlayer(final Integer diceValue, final Player player, final Integer position) {
         diceRoll = diceValue;
         currentPlayer = player;
         temporaryBoardPosition = position;
         Boolean turnHasEnded = true; //Stays ended as long as boost square not hit.
        //Calls boostSquare method to check if player has hit a boost square.
         if (boostAccumulator < 1) {
             //Checks if player hit boost square on initial roll.
             turnHasEnded = hitBoostSquare(temporaryBoardPosition + diceRoll);
         } else {
             //Checks if player hit boost square after landing on an initial boost square on first method pass.
             turnHasEnded = hitBoostSquare(temporaryBoardPosition);
         }
         //Position checked against snakes & ladders on Board.
         if (!hitSnakeSquare(temporaryBoardPosition)) {
             hitLadderSquare(temporaryBoardPosition);
         }
         //Turn ends if no boost square landed on. Accumulator reset, regular Game move method called.
         if (turnHasEnded) {
             currentGame.moveCurrentPlayer(diceRoll);
             boostAccumulator = 0;
         } else {
             //swaps player's temporary position to position + double of die roll on first pass of method.
             if (boostAccumulator < 2) {
                 temporaryBoardPosition = temporaryBoardPosition + (diceRoll * 2);
                 movePlayer(diceRoll, currentPlayer, temporaryBoardPosition);
             //only position + 1 dice roll added to position on recursive call if second boost.
             } else {
                 temporaryBoardPosition = temporaryBoardPosition + diceRoll;
                 movePlayer(diceRoll, currentPlayer, temporaryBoardPosition);
             }
         }
    }

    /**
     * Checks if player's new position is a snake head square.
     * @param position - player's temporary position.
     * @return boolean to determine whether to check if player hits ladder square.
     */
    private Boolean hitSnakeSquare(final Integer position) {
        //For loop triggers Boolean to true if position equal to snake head.
        Boolean hitSnake = false;
        //Check snake head list sourced from Board.
        for (Integer s : currentBoard.getSnakeHeadList()) {
            if (boostAccumulator < 1) {
                //First pass can check player's position by checking the Player object's position on board.
                if (((currentPlayer.getPosition().get()) + diceRoll) == s) {
                    //Data collected for addition to GameTextPanel text area, informing player of what wil happen
                    // during move.
                    Integer snakeHead = currentPlayer.getPosition().get() + diceRoll;
                    Integer snakeTail = currentBoard.getSquareDestination(snakeHead);
                    gameGui.appendTextToPanel(currentPlayer.getPlayerData().getColour() + " player hits a snake square at "
                            + "position " + snakeHead + "...\n");
                    gameGui.appendTextToPanel(currentPlayer.getPlayerData().getColour() + " player moves back "
                            + (snakeHead - snakeTail) + " squares...\n");
                    hitSnake = true;
                }
            } else {
                //recursive passes must use the player's temporary position due to object position not having moved.
                if (position == s) {
                    Integer snakeTail = currentBoard.getSquareDestination(position);
                    gameGui.appendTextToPanel(currentPlayer.getPlayerData().getColour() + " player hits a snake square at "
                            + "position " + position + "...\n");
                    gameGui.appendTextToPanel(currentPlayer.getPlayerData().getColour() + " player slides down "
                            + (position - snakeTail) + " squares...\n");
                    hitSnake = true;
                }
            }
            //true if snake hit, false if not/
        } return hitSnake;
    }

    /**
     * Method called if no snake hit - checks player position against all ladder squares on Board object.
     * @param position - player's temporary position.
     */
    private void hitLadderSquare(final Integer position) {
        //Check ladder foot list sourced from Board.
        for (Integer s : currentBoard.getLadderFootList()) {
            if (boostAccumulator < 1) {
                if (((currentPlayer.getPosition().get()) + diceRoll) == s) {
                    Integer ladderFoot = currentPlayer.getPosition().get() + diceRoll;
                    Integer ladderTop = currentBoard.getSquareDestination(ladderFoot);
                    gameGui.appendTextToPanel(currentPlayer.getPlayerData().getColour() + " player hits a ladder square at "
                            + "position " + ladderFoot + ".\n");
                    gameGui.appendTextToPanel(currentPlayer.getPlayerData().getColour() + " player climbs "
                            + (ladderTop - ladderFoot) + " additional squares!\n");
                }
            } else {
                if (position == s) {
                    Integer ladderTop = currentBoard.getSquareDestination(position);
                    gameGui.appendTextToPanel(currentPlayer.getPlayerData().getColour() + " player hits a ladder square at "
                            + "position " + position + ".\n");
                    gameGui.appendTextToPanel(currentPlayer.getPlayerData().getColour() + " player propels "
                            + (ladderTop - position) + " additional squares!\n");
                }
            }
        }
    }
    /**
     * Check if new movement destination is a boost square.
     * @param tempPosition - player's temporary position.
     * @return noBoost - confirmation of whether boost square hit.
     */
    private Boolean hitBoostSquare(final Integer tempPosition) {
        Integer currentPosition = tempPosition;

        Boolean noBoost = true;
        //First check is regular Player object position call from Game.
        if (boostAccumulator < 1) {
            currentPosition = currentPlayer.getPosition().get() + diceRoll;
        }
        //Check boost square list sourced from Board.
        for (Integer s : currentBoard.getBoostList()) {
            if (tempPosition == s) {

                gameGui.appendTextToPanel(currentPlayer.getPlayerData().getColour() + " player hits a Boost square at "
                        + "position " + tempPosition + "!\n");
                if (boostAccumulator < 1) {
                    gameGui.appendTextToPanel(currentPlayer.getPlayerData().getColour() + " player doubles their dice roll "
                    + "to " + (diceRoll * 2) + "!\n");
                    //Confirms player has hit 1 square. future checks will check a refined player position.
                    boostAccumulator++;
                } else {
                    //textPanel addition text changed to reflect a multiple Boost move. player does not move double
                    // dice roll after first boost pad.
                    gameGui.appendTextToPanel(currentPlayer.getPlayerData().getColour() + " player increases their boosted roll "
                            + "by an additional " + (diceRoll) + "!\n");
                    boostAccumulator++;
                }
                //Indicates on parent method that recursive call is necessary.
                noBoost = false;
            }
            //Return whether boost square hit.
        } return noBoost;
    }

    /**
     * all players moved on Board according to their saved game position. Special squares ignored
     * during intial loaded move for competitive integrity.
     * @param player - current player being moved.
     * @param position - destination of player.
     */
    public void moveLoadedGamePlayer(final Player player, final Integer position) {
        Position startingSquare = new Position(position);
        player.moveTo(startingSquare);
    }
}
