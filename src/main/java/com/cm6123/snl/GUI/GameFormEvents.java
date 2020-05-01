package com.cm6123.snl.GUI;

import java.util.EventObject;

public class GameFormEvents extends EventObject {

    private String specialSquareStart;
    private String specialSquareEnd;
    private String boostSquare;

    public GameFormEvents(final Object source) {
        super(source);
    }

    public GameFormEvents(final Object source, final String newBoostSquare) {
        super(source);

        this.boostSquare = newBoostSquare;
    }



    public GameFormEvents(final Object source, final String newSpecialSquareStart, final String newSpecialSquareEnd) {
        super(source);

        this.specialSquareStart = newSpecialSquareStart;
        this.specialSquareEnd = newSpecialSquareEnd;
    }

    public String getSpecialSquareStart() {
        return specialSquareStart;
    }

    public void setSpecialSquareStart(final String newSpecialSquareStart) {
        this.specialSquareStart = newSpecialSquareStart;
    }

    public String getSpecialSquareEnd() {
        return specialSquareEnd;
    }

    public void setSpecialSquareEnd(final String newSpecialSquareEnd) {
        this.specialSquareEnd = newSpecialSquareEnd;
    }

    public String getBoostSquare() {
        return boostSquare;
    }

    public void setBoostSquare(final String newBoostSquare) {
        this.boostSquare = boostSquare;
    }
}
