package com.cm6123.snl.GUI;

import java.util.EventObject;

public class NewAdditionFormEvents extends EventObject {

    private Integer firstFieldEntry;
    private Integer secondFieldEntry;
//    private Integer boostSquare;
    private String playerNameEntry;
    private int boardCategory;
    private NewAddition additionChoice;

    public NewAdditionFormEvents(final Object source) {
        super(source);
    }

    public NewAdditionFormEvents(final Object source, final Integer newBoostSquare, final NewAddition addition) {
        super(source);
        this.firstFieldEntry = newBoostSquare;
        this.additionChoice = addition;
    }

    public NewAdditionFormEvents(final Object source, final Integer newSpecialSquareStart,
                                 final Integer newSpecialSquareEnd, final NewAddition addition) {
        super(source);

        this.firstFieldEntry = newSpecialSquareStart;
        this.secondFieldEntry = newSpecialSquareEnd;
        this.additionChoice = addition;
    }

    public NewAdditionFormEvents(final Object source, final String newPlayerEntry, final NewAddition addition) {
        super(source);

        this.playerNameEntry = newPlayerEntry;
        this.additionChoice = addition;
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
    public NewAddition getAdditionChoice() {
        return additionChoice;
    }
//    public void setBoostSquare(final Integer newBoostSquare) {
//        this.boostSquare = boostSquare;
//    }
}
