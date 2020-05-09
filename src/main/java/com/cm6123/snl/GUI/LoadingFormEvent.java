package com.cm6123.snl.GUI;

import java.util.EventObject;

public class LoadingFormEvent extends EventObject {



    private Integer loadGameEntry;
    private Integer firstEntry;
    private Integer secondEntry;
//    private Integer boostSquare;
    private String playerNameEntry;
    private int boardCategory;
    private Edit editChoice;

    public LoadingFormEvent(final Object source) {
        super(source);
    }

    public LoadingFormEvent(final Object source, final Integer loadGameChoice) {
        super(source);
        this.loadGameEntry = loadGameChoice;
    }


    public LoadingFormEvent(final Object source, final Integer newBoostSquare, final Edit addition) {
        super(source);
        this.firstEntry = newBoostSquare;
        this.editChoice = addition;
    }

    public LoadingFormEvent(final Object source, final Integer newSpecialSquareStart,
                            final Integer newSpecialSquareEnd, final Edit edit) {
        super(source);

        this.firstEntry = newSpecialSquareStart;
        this.secondEntry = newSpecialSquareEnd;
        this.editChoice = edit;
    }

    public LoadingFormEvent(final Object source, final String newPlayerEntry, final Edit addition) {
        super(source);

        this.playerNameEntry = newPlayerEntry;
        this.editChoice = addition;
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
    public Edit getEditChoice() {
        return editChoice;
    }
//    public void setBoostSquare(final Integer newBoostSquare) {
//        this.boostSquare = boostSquare;
//    }
    public Integer getLoadGameEntry() {
        return loadGameEntry;
}
}
