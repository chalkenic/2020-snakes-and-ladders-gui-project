package com.cm6123.snl.gameDB;

/**
 * Constant class stores hardcoded database string name.
 */
public final class ConstantDatabaseName {

    private ConstantDatabaseName() {
    }
    /**
     * Get the string value equal to database name.
     */
    public static final String DATABASENAME = "snakesandladdersdatabase";
//    /**
//     * Get string creating game table.
//     */
//    public static final String CREATEGAMETABLE = "CREATE TABLE IF NOT EXISTS game (\n"
//            + "  gameID INT NOT NULL AUTO_INCREMENT,\n"
//            + "  gamePlayerTurn INT NOT NULL DEFAULT 1,\n"
//            + "  gameRound INT NOT NULL DEFAULT 1,\n"
//            + "  boardGridSize INT NOT NULL,\n"
//            + "  boardSize INT NOT NULL DEFAULT (boardGridSize * boardGridSize),\n"
//            + "  gameHasEnded TINYINT NULL DEFAULT false,\n"
//            + "  boostSquareFeature TINYINT NULL DEFAULT false,\n"
//            + "  winningSquareOnlyFeature TINYINT NULL DEFAULT false,\n"
//            + "  recordGameFeature TINYINT NULL DEFAULT false,\n"
//            + "  dice_diceID INT NOT NULL,\n"
//            + "  PRIMARY KEY (gameID),\n"
//            + "    FOREIGN KEY (dice_diceID)\n"
//            + "    REFERENCES dice (diceID))";
//    /**
//     * Get string creating dice table.
//     */
//    public static final String CREATEDICETABLE = "CREATE TABLE IF NOT EXISTS dice (\n"
//            + "  diceID INTEGER NOT NULL AUTO_INCREMENT,\n"
//            + "  diceCount INT NOT NULL,\n"
//            + "  diceFaces INT NOT NULL,\n"
//            + "  PRIMARY KEY (DiceID))";
//    /**
//     * Get string creating player table.
//     */
//    public static final String CREATEPLAYERSTABLE = "CREATE TABLE IF NOT EXISTS players (\n"
//            + "  playerID INT NOT NULL AUTO_INCREMENT,\n"
//            + "  playerColour VARCHAR(45) NOT NULL,\n"
//            + "  playerPosition INT DEFAULT 0,\n"
//            + "  playerMovesTaken INT DEFAULT 0,\n"
//            + "  playerWonGame TINYINT DEFAULT false,\n"
//            + "  game_gameID INT NOT NULL,\n"
//            + "  pl_PlayerListID INT NULL,\n"
//            + "  PRIMARY KEY (playerID),\n"
//            + "    FOREIGN KEY (game_gameID)\n"
//            + "    REFERENCES game(gameID),\n"
//            + "    FOREIGN KEY (pl_PlayerListID)\n"
//            + "    REFERENCES PlayerList (PlayerListID)";
//    /**
//     * Get string creating moves table.
//     */
//    public static final String CREATEMOVESTABLE = "CREATE TABLE IF NOT EXISTS moves (\n"
//            + "  moveID` INT NOT NULL AUTO_INCREMENT,\n"
//            + "  moveStart` INT NOT NULL,\n"
//            + "  moveEnd` INT NOT NULL,\n"
//            + "  moveRoll INT,\n"
//            + "  landedOnSnake TINYINT DEFAULT 0,\n"
//            + "  landedOnLadder TINYINT DEFAULT 0,\n"
//            + "  landedOnBoost TINYINT DEFAULT 0,\n"
//            +  "  players_playerID INT NOT NULL,\n"
//            + "  game_gameID INT NOT NULL,\n"
//            + "  PRIMARY KEY (`moveID`),\n"
//            + "    FOREIGN KEY (`players_playerID`)\n"
//            + "    REFERENCES players (`playerID`),\n"
//            + "    FOREIGN KEY (`game_gameID`)\n"
//            + "    REFERENCES game (`gameID`))";
//    /**
//     * Get string creating playerlist table.
//     */
//    public static final String CREATEPLAYERLISTTABLE = "CREATE TABLE IF NOT EXISTS playerList (\n"
//            + "\t`playerListID` INT NOT NULL AUTO_INCREMENT,\n"
//            + "    playerColour VARCHAR(45),\n"
//            + "    playerWinCount INT DEFAULT 0,\n"
//            + "    playerTotalMovesMade INT DEFAULT 0,\n"
//            + "    playerAverageGameMoves DECIMAL (4,2),\n"
//            + "    PRIMARY KEY (`playerListID`))";
//    /**
//     * Get string creating snakes table.
//     */
//    public static final String CREATESNAKESTABLE = "CREATE TABLE IF NOT EXISTS snakes (\n"
//            + "  snakeID INT NOT NULL AUTO_INCREMENT,\n"
//            + "  snakeHead INT NOT NULL,\n"
//            + "  snakeTail INT NOT NULL,\n"
//            + "  game_gameID INT NOT NULL,\n"
//            + "  PRIMARY KEY (snakeID),\n"
//            + "    FOREIGN KEY (game_gameID)\n"
//            + "    REFERENCES game (gameID))";
//    /**
//     * Get string creating ladders table.
//     */
//    public static final String CREATELADDERSTABLE = "CREATE TABLE IF NOT EXISTS ladders (\n"
//            + "  ladderID` INT NOT NULL AUTO_INCREMENT,\n"
//            + "  ladderFoot` INT NOT NULL,\n"
//            + "  ladderTop` INT NOT NULL,\n"
//            + "  game_gameID` INT NOT NULL,\n"
//            + "  PRIMARY KEY (ladderID),\n"
//            + "    FOREIGN KEY (game_gameID)\n"
//            + "    REFERENCES game (gameID))";
//
//    public static final String CREATEBOOSTSTABLE = "CREATE TABLE IF NOT EXISTS boosts (\n"
//            + "  boostID INT NOT NULL AUTO_INCREMENT,\n"
//            + "  boostLocation INT NOT NULL,\n"
//            + "  game_gameID INT NOT NULL,\n"
//            + "  PRIMARY KEY (`boostID`),\n"
//            + "    FOREIGN KEY (`Game_gameID`)\n"
//            + "    REFERENCES game (`gameID`))";
}
