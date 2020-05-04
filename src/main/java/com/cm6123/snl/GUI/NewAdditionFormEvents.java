package com.cm6123.snl.GUI;

import java.util.EventObject;

public class NewAdditionFormEvents extends EventObject {

    private Integer firstFieldEntry;
    private Integer secondFieldEntry;
//    private Integer boostSquare;
    private String playerNameEntry;
    private int boardCategory;

    public NewAdditionFormEvents(final Object source) {
        super(source);
    }

    public NewAdditionFormEvents(final Object source, final Integer newBoostSquare) {
        super(source);
        this.firstFieldEntry = newBoostSquare;
    }

    public NewAdditionFormEvents(final Object source, final Integer newSpecialSquareStart, final Integer newSpecialSquareEnd) {
        super(source);

        this.firstFieldEntry = newSpecialSquareStart;
        this.secondFieldEntry = newSpecialSquareEnd;
    }

    public NewAdditionFormEvents(final Object source, final String newPlayerEntry) {
        super(source);

        this.playerNameEntry = newPlayerEntry;
    }

    public Integer getFirstFieldEntry() {
        return firstFieldEntry;
    }

    public void setFirstFieldEntry(final Integer newSpecialSquareStart) {
        this.firstFieldEntry = newSpecialSquareStart;
    }

    public Integer getSecondFieldEntry() {
        return secondFieldEntry;
    }

    public void setSecondFieldEntry(final Integer newSpecialSquareEnd) {
        this.secondFieldEntry = newSpecialSquareEnd;
    }

    public String getPlayerNameEntry() { return playerNameEntry; }

    public void setPlayerNameEntry(final String playerName) { this.playerNameEntry = playerNameEntry; }
//    public Integer getBoostSquare() {
//        return boostSquare;
//    }
//
//    public void setBoostSquare(final Integer newBoostSquare) {
//        this.boostSquare = boostSquare;
//    }
}
