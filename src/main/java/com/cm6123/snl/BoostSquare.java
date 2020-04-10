package com.cm6123.snl;

public class BoostSquare extends Square {
    /**
     * Create a Boost Square on the board, allowing player to move at double their original roll.
     * @param aboard - The board where the boost square resides.
     * @param position - The position of the Boost square on the board.
     */
    public BoostSquare(final Board aboard,
                       final Integer position) {
        super(aboard, position);
    }
}
