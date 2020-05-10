package com.cm6123.snl.GUI;

import java.util.EventObject;
/**
 * Class handles all loading form entries for editor menu & creates an object to store the data.
 */
public class LoadingFormEvent extends EventObject {
    /**
     * handles first entry of edit form.
     */
    private Integer firstEntry;
    /**
     * Handles second entry of edit form.
     */
    private Integer secondEntry;
    /**
     * Holds a player name if loading is for EditChoice - Edit.PLAYER.
     */
    private String playerNameEntry;
    /**
     * Choice of Edit.
     */
    private Edit editChoice;
    /**
     * Does this Event have an associated game id?
     */
    private Integer gameID;
    /**
     * Holds position of field inside database.
     */
    private Integer databaseID;
    /**
     * Constructor handles player edit entries (no gameID or number entries required by object).
     * @param source - object where event initially occurred.
     * @param editPlayerEntry - player name,
     * @param edit - confirmation of addition choice to GUI frame.
     * @param dbID - indication in database as to where to edit on table. script not 0 indexed - appended by 1.
     */
    public LoadingFormEvent(final Object source, final String editPlayerEntry, final Edit edit, final Integer dbID) {
        super(source);
        this.databaseID = dbID + 1; //Database is not zero-indexed, ID requires increment.
        this.playerNameEntry = editPlayerEntry;
        this.editChoice = edit;
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

    /**
     * Get first entry of form.
     * @return firstEntry - the first entry.
     */
    public Integer getFirstEntry() {
        return firstEntry;
    }
    /**
     * get second entry of form (if using).
     * @return secondEntry - the second entry.
     */
    public Integer getSecondEntry() {
        return secondEntry;
    }
    /**
     * Get player name from form.
     * @return playerNameEntry - name of player.
     */
    public String getPlayerNameEntry() {
        return playerNameEntry;
    }
    /**
     * Get the game ID (if required).
     * @return gameID - ID of game inside database.
     */
    public Integer getGameID() {
        return gameID;
    }
    /**
     * get the ID of row location inside database.
     * @return databaseID - location inside database.
     */
    public Integer getDatabaseID() {
        return databaseID;
    }
    /**
     * Get the choice of edit.
     * @return editChoice - enum field saved.
     */
    public Edit getEditChoice() {
        return editChoice;
    }
}
