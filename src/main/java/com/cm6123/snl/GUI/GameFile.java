package com.cm6123.snl.GUI;

public class GameFile {

    private String gameText;

    private Integer gameID;
    private Integer gameRound;
    private Integer gamePlayerRound;
    private Integer boardSize;
    private Boolean gameOver;
    private Boolean boostSquareFeature;
    private Boolean winningSquareFeature;

    public GameFile(final int id, final String text) {
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
