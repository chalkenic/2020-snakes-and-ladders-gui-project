package com.cm6123.snl.GUI;

import com.cm6123.snl.Board;
import com.cm6123.snl.Game;
import com.cm6123.snl.Player;
import com.cm6123.snl.Square;

public class BoardMove {

    private Integer diceRoll;
    private Game currentGame;
    private Player currentPlayer;
    private Integer temporaryBoardPosition;
    private Board currentBoard;

    public BoardMove(final Game newGame) {
        this.currentGame = newGame;
        this.currentBoard = currentGame.getBoard();


    }

    public Integer getDiceRoll() {
        return diceRoll;
    }

    public void movePlayer(final Integer diceValue) {
         diceRoll = diceValue;
         currentPlayer = currentGame.getCurrentPlayer();

         hitSnakeSquare();
         hitLadderSquare();


        currentGame.moveCurrentPlayer(diceRoll);

    }

    public void hitSnakeSquare() {
        System.out.println("current player position: " + currentPlayer.getPosition().get());
        System.out.println("current dice roll: " + diceRoll);
        System.out.println("apparent new position: " + ((currentPlayer.getPosition().get()) + diceRoll));
        for (Integer s : currentBoard.getSnakeHeadList()) {
            System.out.println("Snake head: " + s);
            if (((currentPlayer.getPosition().get()) + diceRoll) == s) {
                System.out.println("hello?");
            }
//            } else if (((currentPlayer.getPosition().get()) + diceRoll) == s) {
//                System.out.println("hello part 2?");
//            }
        }
    }
    public void hitLadderSquare() {
        System.out.println("current player position: " + currentPlayer.getPosition().get());
        System.out.println("current dice roll: " + diceRoll);
        System.out.println("apparent new position: " + ((currentPlayer.getPosition().get()) + diceRoll));
        for (Integer s : currentBoard.getLadderFootList()) {
            System.out.println("Ladder foot: " + s);
            if (((currentPlayer.getPosition().get()) + diceRoll) == s) {
                System.out.println("test?");
            }
        }
    }
}
