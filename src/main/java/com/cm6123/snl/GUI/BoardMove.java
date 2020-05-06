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


         if (hitBoostSquare()) {
             temporaryBoardPosition = temporaryBoardPosition + diceRoll;
             gameGui.appendTextToPanel(currentPlayer.getColour() + "player has hit a Boost square and doubles "
             + "their roll to " + (diceRoll * 2) + "!\n");
             movePlayer(diceRoll, currentPlayer, temporaryBoardPosition);
         }
         if (!hitSnakeSquare()) {
             hitLadderSquare();
         }

        currentGame.moveCurrentPlayer(diceRoll);

    }

    public Boolean hitSnakeSquare() {

        Boolean hitSnake = false;
        for (Integer s : currentBoard.getSnakeHeadList()) {
            if (((currentPlayer.getPosition().get()) + diceRoll) == s) {
                Integer snakeHead = currentPlayer.getPosition().get() + diceRoll;
                Integer snakeTail = currentBoard.getSquareDestination(snakeHead);
                gameGui.appendTextToPanel(currentPlayer.getColour() + " player hits a snake square and "
                + "moves back " + (snakeHead - snakeTail) + " squares!\n");
                hitSnake = true;
            }
//            } else if (((currentPlayer.getPosition().get()) + diceRoll) == s) {
//                System.out.println("hello part 2?");
//            }
        } return hitSnake;
    }
    public void hitLadderSquare() {

        for (Integer s : currentBoard.getLadderFootList()) {
            if (((currentPlayer.getPosition().get()) + diceRoll) == s) {
                Integer ladderFoot = currentPlayer.getPosition().get() + diceRoll;
                Integer ladderTop = currentBoard.getSquareDestination(ladderFoot);
                gameGui.appendTextToPanel(currentPlayer.getColour() + " player hits a ladder square and "
                + "propels " + (ladderTop - ladderFoot) + " additional squares!\n");
            }
        }
    }

    public Boolean hitBoostSquare() {

        Boolean hitBoost = false;
        for (Integer s : currentBoard.getBoostList()) {
            if (((currentPlayer.getPosition().get()) + diceRoll) == s) {
                hitBoost = true;
                System.out.println("test?");
            }
        } return hitBoost;
    }
}
