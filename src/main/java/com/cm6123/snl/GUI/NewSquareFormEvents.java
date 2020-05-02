package com.cm6123.snl.GUI;

import java.util.EventObject;

public class NewSquareFormEvents extends EventObject {

    private Integer specialSquareStart;
    private Integer specialSquareEnd;
    private Integer boostSquare;
    private int boardCategory;

    public NewSquareFormEvents(final Object source) {
        super(source);
    }

    public NewSquareFormEvents(final Object source, final Integer newBoostSquare) {
        super(source);

        this.boostSquare = newBoostSquare;
    }

    public NewSquareFormEvents(final Object source, final Integer newSpecialSquareStart, final Integer newSpecialSquareEnd) {
        super(source);

        this.specialSquareStart = newSpecialSquareStart;
        this.specialSquareEnd = newSpecialSquareEnd;
    }

    public Integer getSpecialSquareStart() {
        return specialSquareStart;
    }

    public void setSpecialSquareStart(final Integer newSpecialSquareStart) {
        this.specialSquareStart = newSpecialSquareStart;
    }

    public Integer getSpecialSquareEnd() {
        return specialSquareEnd;
    }

    public void setSpecialSquareEnd(final Integer newSpecialSquareEnd) {
        this.specialSquareEnd = newSpecialSquareEnd;
    }

    public Integer getBoostSquare() {
        return boostSquare;
    }

    public void setBoostSquare(final Integer newBoostSquare) {
        this.boostSquare = boostSquare;
    }
}
