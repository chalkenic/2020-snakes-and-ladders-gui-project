package com.cm6123.snl.GUI;

import com.cm6123.snl.Board;
import com.cm6123.snl.Game;
import com.cm6123.snl.Player;
import com.cm6123.snl.Square;

import java.util.List;

public class BoardMove {

    private Integer diceRoll;
    private Game currentGame;
    private Player currentPlayer;
    private Integer temporaryBoardPosition;
    private Board currentBoard;
    private GUIFrame gameGui;
    private List<Square> squareList;
    private Integer boostAccumulator = 0;

    public BoardMove(final Game newGame, final GUIFrame gui) {
        this.currentGame = newGame;
        this.gameGui = gui;
        this.currentBoard = currentGame.getBoard();


    }

    public Integer getDiceRoll() {
        return diceRoll;
    }

    public void movePlayer(final Integer diceValue, final Player player, final Integer position) {
         diceRoll = diceValue;
         currentPlayer = player;
         temporaryBoardPosition = position;
         Boolean turnHasEnded = true;

         if (boostAccumulator < 1) {
             turnHasEnded = hitBoostSquare(temporaryBoardPosition + diceRoll);
         } else {
             turnHasEnded = hitBoostSquare(temporaryBoardPosition);
         }

         if (!hitSnakeSquare(temporaryBoardPosition)) {
             System.out.println("test1");
             hitLadderSquare(temporaryBoardPosition);
         }

         if (turnHasEnded) {
             currentGame.moveCurrentPlayer(diceRoll);
             boostAccumulator = 0;
         } else {
             if (boostAccumulator < 2) {
                 temporaryBoardPosition = temporaryBoardPosition + (diceRoll * 2);
                 movePlayer(diceRoll, currentPlayer, temporaryBoardPosition);
             } else {
                 temporaryBoardPosition = temporaryBoardPosition + diceRoll;
                 movePlayer(diceRoll, currentPlayer, temporaryBoardPosition);
             }

         }
    }

    public Boolean hitSnakeSquare(final Integer position) {

        Boolean hitSnake = false;
        for (Integer s : currentBoard.getSnakeHeadList()) {
            if (boostAccumulator < 1) {
                if (((currentPlayer.getPosition().get()) + diceRoll) == s) {
                    Integer snakeHead = currentPlayer.getPosition().get() + diceRoll;
                    Integer snakeTail = currentBoard.getSquareDestination(snakeHead);
                    gameGui.appendTextToPanel(currentPlayer.getColour() + " player hits a snake square at "
                            + "position " + snakeHead + "...\n");
                    gameGui.appendTextToPanel(currentPlayer.getColour() + " player moves back "
                            + (snakeHead - snakeTail) + " squares...\n");
                    hitSnake = true;
                }
            } else {
                if (position == s) {
                    Integer snakeTail = currentBoard.getSquareDestination(position);
                    gameGui.appendTextToPanel(currentPlayer.getColour() + " player hits a snake square at "
                            + "position " + position + "...\n");
                    gameGui.appendTextToPanel(currentPlayer.getColour() + " player slides down "
                            + (position - snakeTail) + " squares...\n");
                    hitSnake = true;
                }
//            } else if (((currentPlayer.getPosition().get()) + diceRoll) == s) {
//                System.out.println("hello part 2?");
//            }
            }
        } return hitSnake;
    }

    public void hitLadderSquare(final Integer position) {
        System.out.println("test2");

        for (Integer s : currentBoard.getLadderFootList()) {
            if (boostAccumulator < 1) {
                if (((currentPlayer.getPosition().get()) + diceRoll) == s) {
                    Integer ladderFoot = currentPlayer.getPosition().get() + diceRoll;
                    Integer ladderTop = currentBoard.getSquareDestination(ladderFoot);
                    gameGui.appendTextToPanel(currentPlayer.getColour() + " player hits a ladder square at "
                            + "position " + ladderFoot + ".\n");
                    gameGui.appendTextToPanel(currentPlayer.getColour() + " player climbs "
                            + (ladderTop - ladderFoot) + " additional squares!\n");
                }
            } else {
                if (position == s) {
                    Integer ladderTop = currentBoard.getSquareDestination(position);
                    gameGui.appendTextToPanel(currentPlayer.getColour() + " player hits a ladder square at "
                            + "position " + position + ".\n");
                    gameGui.appendTextToPanel(currentPlayer.getColour() + " player propels "
                            + (ladderTop - position) + " additional squares!\n");
                }
            }
        }
    }

    public Boolean hitBoostSquare(final Integer tempPosition) {
        Integer currentPosition = tempPosition;

        Boolean noBoost = true;

        if (boostAccumulator < 1) {
            currentPosition = tempPosition + diceRoll;
        }
        for (Integer s : currentBoard.getBoostList()) {
            if (tempPosition == s) {
//                Integer boostSquare = currentPlayer.getPosition().get() + diceRoll;
                gameGui.appendTextToPanel(currentPlayer.getColour() + " player hits a Boost square at "
                        + "position " + tempPosition + "!\n");
                if (boostAccumulator < 1) {
                    gameGui.appendTextToPanel(currentPlayer.getColour() + " player doubles their dice roll "
                    + "to " + (diceRoll * 2) + "!\n");
                    boostAccumulator++;
                } else {
                    gameGui.appendTextToPanel(currentPlayer.getColour() + " player increases their boosted roll "
                            + "by an additional " + (diceRoll) + "!\n");
                    boostAccumulator++;
                }
                noBoost = false;

            }
        } return noBoost;
    }
}
