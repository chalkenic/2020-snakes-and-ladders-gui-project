package com.cm6123.snl.GUI;

/**
 * Object created that holds data from database when loaded onto EditChoicePanel.
 */
public class EditCategory {
    /**
     * Holds position of row inside database table.
     */
    private Integer jlistID;
    /**
     * Game that Edit choice refers to.
     */
    private Integer gameID;
    /**
     * What type of edit is being made.
     */
    private Edit editChoice;

    /**
     * Constructor handles any choice that must be linked to a specific game (i.e. snake/ladder/boost).
     * @param id - ID of specific row inside database table.
     * @param choice - Edit choice enum.
     * @param game - Which game inside database does this object relate to?
     */
    public EditCategory(final Integer id, final Edit choice, final Integer game ) {
        this.jlistID = id;
        this.gameID = game;
        this.editChoice = choice;
    }
    /**
     * Consutrctor handles choices that don't require any specific game (dice/player)
     * @param id - ID of specific row inside database table.
     * @param choice - Edit choice enum.
     */
    public EditCategory(final Integer id, final Edit choice) {
        this.jlistID = id;
        this.editChoice = choice;
    }
    /**
     * Getter for finding table row position.
     * @return jlistID - table ID.
     */
    public int getListID() {
        return jlistID;
    }
    /**
     * Getter for finding the edit choice's related game.
     * @return gameID - related game.
     */
    public Integer getGameID() {
        return gameID;
    }
}

