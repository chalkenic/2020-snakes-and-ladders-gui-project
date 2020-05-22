package com.cm6123.snl.GUI;
/**
 * Program can only navigate to following places on GUIFrame.
 */
public enum NavigateTo {
    /**
     *Confirm navigation to the mainMenu Panel.
     */
    MENU,
    /**
     *Confirm navigation to the newGame panel.
     */
    NEWGAME,
    /**
     *Confirm navigation to the runGame panel with default game options.
     */
    RUNDEFAULTGAME,
    /**
     * Confirm navigation to the runGame panel with custom game options.
     */
    RUNCUSTOMGAME,
    /**
     * Confirm navigation to the runGame panel with identical game options to currently running game.
     */
    RUNREPEATGAME,
    /**
     * Confirm navigation to the loadGame panel.
     */
    LOADGAME,
    /**
     * Confirm navigation to the runGame panel with loaded game options.
     */
    RUNLOADEDGAME,
    /**
     * Confirm navigation to the EditorMenu panel.
     */
    EDITORMENU,
    /**
     * Confirm navigation to the editorChoice panel with GameEdit enum choice.
     */
    NEWEDIT
}
