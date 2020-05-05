package com.cm6123.snl.GUI;

public class LoadGameFile {

    private int gameID;
    private String gameText;

    public LoadGameFile(final int id, final String text) {
        this.gameID = id;
        this.gameText = text;

    }
    public String toString() {
        return gameText;
    }

    public int getGameID() {
        return gameID;
    }
}
