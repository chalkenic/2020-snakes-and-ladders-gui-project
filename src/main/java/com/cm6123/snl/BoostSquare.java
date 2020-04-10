package com.cm6123.snl;

public class BoostSquare extends Square {

//    private Square destination;
    /**
     * Create a Boost Square on the board, allowing player to move at double their original roll.
     * @param aboard - The board where the boost square resides.
     * @param index - The position of the Boost square on the board.
//     * @param boost - Designates s
     */
    public BoostSquare(final Board aboard,
                       final Integer index) {
        super(aboard, index);
//        destination = index;
    }
}
