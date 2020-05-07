package com.cm6123.snl.GUI;

import java.util.EventObject;

public class FormEvents extends EventObject {



    private Integer loadGameEntry;
    private Integer firstEntry;
    private Integer secondEntry;
//    private Integer boostSquare;
    private String playerNameEntry;
    private int boardCategory;
    private NewAddition additionChoice;

    public FormEvents(final Object source) {
        super(source);
    }

    public FormEvents(final Object source, Integer loadGameChoice) {
        super(source);
        this.loadGameEntry = loadGameChoice;
    }


    public FormEvents(final Object source, final Integer newBoostSquare, final NewAddition addition) {
        super(source);
        this.firstEntry = newBoostSquare;
        this.additionChoice = addition;
    }

    public FormEvents(final Object source, final Integer newSpecialSquareStart,
                      final Integer newSpecialSquareEnd, final NewAddition addition) {
        super(source);

        this.firstEntry = newSpecialSquareStart;
        this.secondEntry = newSpecialSquareEnd;
        this.additionChoice = addition;
    }

    public FormEvents(final Object source, final String newPlayerEntry, final NewAddition addition) {
        super(source);

        this.playerNameEntry = newPlayerEntry;
        this.additionChoice = addition;
    }

    public Integer getFirstEntry() {
        return firstEntry;
    }

    public void setFirstEntry(final Integer newSpecialSquareStart) {
        this.firstEntry = newSpecialSquareStart;
    }

    public Integer getSecondEntry() {
        return secondEntry;
    }

    public void setSecondEntry(final Integer newSpecialSquareEnd) {
        this.secondEntry = newSpecialSquareEnd;
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
    public Integer getLoadGameEntry() {
        return loadGameEntry;
}
}
