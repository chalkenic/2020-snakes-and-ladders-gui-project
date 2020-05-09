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




    private Integer gameID;
    private Integer databaseID;

    /**
     * Constructor handles player edit entries (no gameID or number entries required by object).
     * @param source - eventObject,
     * @param editPlayerEntry - player name,
     * @param edit - confirmation of addition choice to GUI frame.
     * @param dbID - indication in database as to where to edit on table. script not 0 indexed - appended by 1.
     */
    public LoadingFormEvent(final Object source, final String editPlayerEntry, final Edit edit, final Integer dbID) {
        super(source);
        this.databaseID = dbID + 1;
        this.playerNameEntry = editPlayerEntry;
        this.editChoice = edit;
    }
//     Unsure of this purpose!
//    public LoadingFormEvent(final Object source) {
//        super(source);
//    }

    public LoadingFormEvent(final Object source, final Integer loadGameChoice) {
        super(source);
        this.loadGameEntry = loadGameChoice;
    }

    /**
     * Constructor handles boost entries - no second entry required.
     * @param source eventObject.
     * @param newBoostSquare -  boost to be changed.
     * @param edit - confirmation of addition choice to GUI frame.
     * @param id - game ID of choice.
     * @param dbID - indication in database as to where to edit on table. script not 0 indexed - appended by 1.
     */
    public LoadingFormEvent(final Object source, final Integer newBoostSquare, final Edit edit, final Integer id,
                            final Integer dbID) {
        super(source);
        this.firstEntry = newBoostSquare;
        this.editChoice = edit;
        this.gameID = id;
        this.databaseID = dbID + 1;
    }

    /**
     * Constructor handles snake & ladder entries.
     * @param source eventObject.
     * @param newSpecialSquareStart square trigger location.
     * @param newSpecialSquareEnd square destination
     * @param id game ID of choice.
     * @param edit confirmation of addition choice to GUI frame.
     * @param dbID - indication in database as to where to edit on table. script not 0 indexed - appended by 1.
     */
    public LoadingFormEvent(final Object source, final Integer newSpecialSquareStart,
                                 final Integer newSpecialSquareEnd, final Edit edit, final Integer id,
                            final Integer dbID) {
        super(source);
        this.gameID = id;
        this.databaseID = dbID + 1;
        this.firstEntry = newSpecialSquareStart;
        this.secondEntry = newSpecialSquareEnd;
        this.editChoice = edit;
    }

    /**
     * Constructor handles dice entries (no gameID or playerName required).
     * @param source - eventObject,
     * @param newSpecialSquareStart - dice count
     * @param newSpecialSquareEnd - dice faces
     * @param edit - confirmation of addition choice to GUI frame.
     * @param dbID - indication in database as to where to edit on table. script not 0 indexed - appended by 1.
     */
    public LoadingFormEvent(final Object source, final Integer newSpecialSquareStart,
                            final Integer newSpecialSquareEnd, final Edit edit, final Integer dbID) {
        super(source);
        this.databaseID = dbID + 1;

        this.firstEntry = newSpecialSquareStart;
        this.secondEntry = newSpecialSquareEnd;
        this.editChoice = edit;
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
    public Integer getGameID() {
        return gameID;
    }

    public void setGameID(final Integer id) {
        this.gameID = id;
    }
    //

    public Integer getDatabaseID() {
        return databaseID;
    }
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
