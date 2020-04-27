-- MySQL Script generated by MySQL Workbench
-- Tue Apr 21 15:53:55 2020
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema snakesAndLaddersData
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema snakesAndLaddersData
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `snakesAndLaddersData`;
CREATE SCHEMA IF NOT EXISTS `snakesAndLaddersData` DEFAULT CHARACTER SET utf8 ;
USE `snakesAndLaddersData` ;


-- ----------------------------------------------------------------------------------
--
--
-- TABLE CREATIONS
-- 
--
-- ----------------------------------------------------------------------------------

-- -----------------------------------------------------
-- Table `snakesAndLaddersData`.`Dice`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `snakesAndLaddersData`.`Dice` ;

CREATE TABLE IF NOT EXISTS `snakesAndLaddersData`.`Dice` (
  `diceID` INTEGER NOT NULL AUTO_INCREMENT,
  `diceCount` INT NOT NULL,
  `diceFaces` INT NOT NULL,
  PRIMARY KEY (`DiceID`))
ENGINE = InnoDB;


INSERT INTO Dice (diceCount, diceFaces) VALUES (1, 6);



-- -----------------------------------------------------
-- Table `snakesAndLaddersData`.`Game`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `snakesAndLaddersData`.`Game` ;

CREATE TABLE IF NOT EXISTS `snakesAndLaddersData`.`Game` (
  `gameID` INT NOT NULL AUTO_INCREMENT,
  `playerTurn` INT NOT NULL DEFAULT 1,
  `gameRound` INT NOT NULL DEFAULT 0,
  `boardSize` INT NOT NULL,
--   `winningSquare` INT NULL,
  `gameHasEnded` TINYINT NULL DEFAULT false,
  `boostSquareFeature` TINYINT NULL DEFAULT false,
  `winningSquareOnlyFeature` TINYINT NULL DEFAULT false,
  `dice_diceID` INT NOT NULL,
  PRIMARY KEY (`gameID`),
    FOREIGN KEY (`dice_diceID`)
    REFERENCES `snakesAndLaddersData`.`Dice` (`diceID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `snakesAndLaddersData`.`GameStatistics`
-- -----------------------------------------------------
-- DROP TABLE IF EXISTS `snakesAndLaddersData`.`GameStatistics` ;

-- CREATE TABLE IF NOT EXISTS `snakesAndLaddersData`.`GameStatistics` (
--   `gameStatisticID` INT NOT NULL AUTO_INCREMENT,
--   `gamePlayerCount` INT NOT NULL,
--   `gameTotalMovesMade` INT NOT NULL,
--   `gameSnakesHit` INT NULL DEFAULT 0,
--   `gameLaddersHit` INT NULL DEFAULT 0,
--   `gameBoostsHit` INT NULL DEFAULT 0,
--   `gameWinningPlayer` VARCHAR(45),
--   `Game_gameID` INT NOT NULL,
--   PRIMARY KEY (`gameStatisticID`),
-- 	FOREIGN KEY (`Game_gameID`)
--     REFERENCES `snakesAndLaddersData`.`Game` (`gameID`)
--     ON DELETE NO ACTION
--     ON UPDATE NO ACTION)
-- ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `snakesAndLaddersData`.`Players`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `snakesAndLaddersData`.`players` ;

CREATE TABLE IF NOT EXISTS `snakesAndLaddersData`.`players` (
  `playerID` INT NOT NULL AUTO_INCREMENT,
  -- `playerName` VARCHAR(45),
  `playerColour` VARCHAR(45) NOT NULL,
  `playerPosition` INT DEFAULT 0,
  `playerMovesTaken` INT DEFAULT 0,
  `playerWonGame` TINYINT DEFAULT false,
  `game_gameID` INT NOT NULL,
  `pl_PlayerListID`INT NOT NULL,
  PRIMARY KEY (`playerID`),
    FOREIGN KEY (`game_gameID`)
    REFERENCES `snakesAndLaddersData`.`Game` (`gameID`),
    FOREIGN KEY (`pl_PlayerListID`)
    REFERENCES `snakesAndLaddersData`.`PlayerList` (`PlayerListID`)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `snakesAndLaddersData`.`PlayerList`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `snakesAndLaddersData`.`PlayerList` ;

CREATE TABLE IF NOT EXISTS `snakesAndLaddersData`.`PlayerList` (
	`PlayerListID` INT NOT NULL AUTO_INCREMENT,
    `playerName` VARCHAR(45),
    `playerWinCount` INT DEFAULT 0,
    `playerTotalMovesMade` INT DEFAULT 0,
    `playerAverageGameMoves` INT,
    PRIMARY KEY (`PlayerListID`))
ENGINE = InnoDB;
        
-- -----------------------------------------------------
-- Table `snakesAndLaddersData`.`Snakes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `snakesAndLaddersData`.`Snakes` ;

CREATE TABLE IF NOT EXISTS `snakesAndLaddersData`.`Snakes` (
  `snakeID` INT NOT NULL AUTO_INCREMENT,
  `snakeHead` INT NOT NULL,
  `snakeTail` INT NOT NULL,
  `game_gameID` INT NOT NULL,
  PRIMARY KEY (`snakeID`),
    FOREIGN KEY (`game_gameID`)
    REFERENCES `snakesAndLaddersData`.`Game` (`gameID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `snakesAndLaddersData`.`Ladders`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `snakesAndLaddersData`.`Ladders` ;

CREATE TABLE IF NOT EXISTS `snakesAndLaddersData`.`Ladders` (
  `ladderID` INT NOT NULL AUTO_INCREMENT,
  `ladderFoot` INT NOT NULL,
  `ladderTop` INT NOT NULL,
  `game_gameID` INT NOT NULL,
  PRIMARY KEY (`ladderID`),
    FOREIGN KEY (`game_gameID`)
    REFERENCES `snakesAndLaddersData`.`Game` (`gameID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `snakesAndLaddersData`.`Boosts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `snakesAndLaddersData`.`Boosts` ;

CREATE TABLE IF NOT EXISTS `snakesAndLaddersData`.`Boosts` (
  `boostID` INT NOT NULL AUTO_INCREMENT,
  `boostLocation` INT NOT NULL,
  `game_gameID` INT NOT NULL,
  PRIMARY KEY (`boostID`),
    FOREIGN KEY (`Game_gameID`)
    REFERENCES `snakesAndLaddersData`.`Game` (`gameID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `snakesAndLaddersData`.`Moves`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `snakesAndLaddersData`.`Moves` ;

CREATE TABLE IF NOT EXISTS `snakesAndLaddersData`.`Moves` (
  `moveID` INT NOT NULL AUTO_INCREMENT,
  `moveStart` INT NOT NULL,
  `moveEnd` INT NOT NULL,
  `moveRoll` INT,
  `landedOnSnake` TINYINT DEFAULT 0,
  `landedOnLadder`TINYINT DEFAULT 0,
  `landedOnBoost`TINYINT DEFAULT 0,
  `players_playerID` INT NOT NULL,
  `game_gameID` INT NOT NULL,
  PRIMARY KEY (`moveID`),
    FOREIGN KEY (`players_playerID`)
    REFERENCES `snakesAndLaddersData`.`players` (`playerID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    FOREIGN KEY (`game_gameID`)
    REFERENCES `snakesAndLaddersData`.`Game` (`gameID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



-- ----------------------------------------------------------------------------------
--
--
-- GAME FUNCTIONS
-- 
--
-- ----------------------------------------------------------------------------------

-- -----------------------------------------------------
-- Counts amount of snake squares landed on in a game.
-- -----------------------------------------------------

DELIMITER ££
CREATE FUNCTION snake_count_landed_in_game (gameChoice INT)
RETURNS INT NOT DETERMINISTIC

BEGIN

	DECLARE snakeCount INT;
    SET snakeCount = (SELECT COUNT(*) FROM Moves
					WHERE game_gameID = gameChoice
                    AND landedOnSnake = 1);
                    
	RETURN snakeCount;
			
END ££
DELIMITER ;

-- -----------------------------------------------------
-- Counts amount of ladder squares landed on in a game.
-- -----------------------------------------------------

DELIMITER ££
CREATE FUNCTION ladder_count_landed_in_game (gameChoice INT)
RETURNS INT NOT DETERMINISTIC

BEGIN

	DECLARE ladderCount INT;
    SET ladderCount = (SELECT COUNT(*) FROM Moves
					WHERE game_gameID = gameChoice
                    AND landedOnLadder = 1);
                    
	RETURN ladderCount;
			
END ££
DELIMITER ;


-- -----------------------------------------------------
-- Counts amount of ladder squares landed on in a game.
-- -----------------------------------------------------

DELIMITER ££
CREATE FUNCTION boost_count_landed_in_game (gameChoice INT)
RETURNS INT NOT DETERMINISTIC

BEGIN

	DECLARE boostCount INT;
    SET boostCount = (SELECT COUNT(*) FROM Moves
					WHERE game_gameID = gameChoice
                    AND landedOnBoost = 1);
                    
	RETURN boostCount;
			
END ££
DELIMITER ;

-- -----------------------------------------------------
-- Finds total moves taken in a game.
-- -----------------------------------------------------

DROP FUNCTION IF EXISTS find_longest_game()

DELIMITER ££
CREATE FUNCTION find_longest_game()
RETURNS INT NOT DETERMINISTIC

BEGIN
	DECLARE longestGame INT;
    SET longestGame = (SELECT COUNT(*) FROM moves
						GROUP BY game_gameID
                        ORDER BY COUNT(*) DESC LIMIT 1);
	
    RETURN longestGame;

END ££
DELIMITER ;

-- ----------------------------------------------------------------------------------
--
--
-- GAME PROCEDURES
-- 
--
-- ----------------------------------------------------------------------------------

-- -----------------------------------------------------
-- Procedure passes in new Player data from call into table. 
-- -----------------------------------------------------
DROP PROCEDURE IF EXISTS add_new_player;

DELIMITER //
CREATE PROCEDURE add_new_player (IN newPlayerEntry VARCHAR(45))
BEGIN
	INSERT INTO PlayerList(playerName)
	VALUES (newPlayerEntry);
END //
DELIMITER ;


-- -----------------------------------------------------
-- Procedure adds a new blank game state. 
-- -----------------------------------------------------
DROP PROCEDURE IF EXISTS add_new_game;

DELIMITER //
CREATE PROCEDURE add_new_game(IN chosenBoardSize INT, IN chosenDice INT)
BEGIN

	INSERT INTO Game(boardSize, dice_diceID)
    VALUES(chosenBoardSize, chosenDice);
END //
DELIMITER ;

-- -----------------------------------------------------
-- Procedure adds new player to a blank game state.
-- -----------------------------------------------------
DROP PROCEDURE IF EXISTS add_new_player_to_game;
DELIMITER //
CREATE PROCEDURE add_new_player_to_game(IN playerColourChoice VARCHAR(45), IN gameStateChoice INT, IN savedPlayerChoice INT)
BEGIN
	INSERT INTO players(playerColour, game_gameID, pl_PlayerListID)
    VALUES(playerColourChoice, gameStateChoice, savedPlayerChoice);

END //
DELIMITER ;

-- -----------------------------------------------------
-- Procedure passes in new Dice from call into table.
-- -----------------------------------------------------
DROP PROCEDURE IF EXISTS add_new_dice;

DELIMITER //
CREATE PROCEDURE add_new_dice(IN newDiceCount INT, IN newDiceFaces INT)
BEGIN

	DECLARE incorrectDiceEntry CONDITION FOR 1357;
    
    DECLARE CONTINUE HANDLER FOR incorrectDiceEntry
	SELECT 'INCORRECT dice choice made for new game.';

	INSERT INTO Dice (diceCount, diceFaces)
    VALUES (newDiceCount, newDiceFaces);
END //
DELIMITER ;

-- -----------------------------------------------------
-- Procedure passes in snake data from call into table. gameID pulled from last field created. 
-- -----------------------------------------------------
DROP PROCEDURE IF EXISTS add_new_snake;

DELIMITER //
CREATE PROCEDURE add_new_snake(IN newSnakeHead INT, IN newSnakeTail INT, IN gameChoice INT)
BEGIN

	INSERT INTO Snakes(snakeHead, snakeTail, Game_gameID)
	VALUES (newSnakeHead, newSnakeTail, gameChoice);
END //
DELIMITER ;

-- -----------------------------------------------------
-- Procedure passes in Ladder data from call into table. gameID pulled from last field created. 
-- -----------------------------------------------------

DROP PROCEDURE IF EXISTS add_new_ladder;

DELIMITER //
CREATE PROCEDURE add_new_ladder(IN newLadderFoot INT, IN newLadderTop INT, IN gameChoice INT)
BEGIN
    
	INSERT INTO Ladders(ladderFoot, ladderTop, Game_gameID)
	VALUES (newLadderFoot, newLadderTop, gameChoice);
END //
DELIMITER ;

-- -----------------------------------------------------
-- Procedure passes in Boost data from call into table. gameID pulled from last field created. 
-- -----------------------------------------------------
DROP PROCEDURE IF EXISTS add_new_boost;

DELIMITER //
CREATE PROCEDURE add_new_boost (IN boostLocation int, IN gameChoice INT)
BEGIN

	INSERT INTO Boosts(boostLocation, Game_gameID)
	VALUES (boostLocation, gameChoice);
END //
DELIMITER ;

-- -----------------------------------------------------
-- Procedure saves a player's move during turn into table.
-- -----------------------------------------------------

DROP PROCEDURE IF EXISTS add_player_move;

DELIMITER //
CREATE PROCEDURE add_player_move(IN playerTurnStart INT, IN playerTurnEnd INT, IN currentPlayer INT, IN currentGame INT)
BEGIN
	INSERT INTO Moves (moveStart, moveEnd, players_playerID, game_gameID)
    VALUES (playerTurnStart, playerTurnEnd, currentPlayer, currentGame);

END //
DELIMITER ;


-- -----------------------------------------------------
-- grabs game based on ID choice .
-- -----------------------------------------------------

DROP PROCEDURE IF EXISTS select_game;

DELIMITER ££
CREATE PROCEDURE select_game (gameChoice INT, OUT gameNumber INT)
BEGIN

SELECT * FROM Game WHERE gameID = gameChoice;

SELECT gameID INTO gameNumber
FROM Game
where gameID = gameChoice;

END ££
DELIMITER ;

-- -----------------------------------------------------
-- grabs player data based on current game choice.
-- -----------------------------------------------------

DROP PROCEDURE IF EXISTS select_players_from_game;

DELIMITER //

CREATE PROCEDURE select_players_from_game(IN currentgame INT)
BEGIN
	SELECT 
    playerColour AS 'player Colour',  
    playerPosition AS 'current position', 
    playerMovesTaken AS 'moves taken'
    FROM players
    WHERE game_gameID = currentGame;
END //
DELIMITER ;

-- -----------------------------------------------------
-- grabs dice choice based on current game choice.
-- -----------------------------------------------------

DROP PROCEDURE IF EXISTS select_dice_choice_from_game;

DELIMITER //

CREATE PROCEDURE select_dice_choice_from_game(IN currentgame INT)
BEGIN
	SELECT diceID, diceCount, diceFaces 
    FROM dice d
    INNER JOIN game g ON d.diceID = g.dice_diceID
    WHERE g.gameID = currentGame;
END //
DELIMITER ;

-- -----------------------------------------------------
-- grabs all snakes from current game choice.
-- -----------------------------------------------------

DROP PROCEDURE IF EXISTS select_game_snakes;

DELIMITER //

CREATE PROCEDURE select_game_snakes(IN currentgame INT)
BEGIN
	SELECT * 
    FROM snakes
    WHERE game_gameID = currentGame;
END //
DELIMITER ;

-- -----------------------------------------------------
-- grabs all ladders from current game choice.
-- -----------------------------------------------------

DROP PROCEDURE IF EXISTS select_game_ladders;

DELIMITER //

CREATE PROCEDURE select_game_ladders(IN currentgame INT)
BEGIN
	SELECT * 
    FROM ladders
    WHERE game_gameID = currentGame;
END //
DELIMITER ;

-- -----------------------------------------------------
-- grabs all boosts from current game choice.
-- -----------------------------------------------------

DROP PROCEDURE IF EXISTS select_game_boosts;

DELIMITER //
CREATE PROCEDURE select_game_boosts(IN currentgame INT)
BEGIN
	SELECT * 
    FROM boosts
    WHERE game_gameID = currentGame;
END //
DELIMITER ;

-- ----------------------------------------------------------------------------------
--
--
-- GAME TRIGGERS
-- 
--
-- ----------------------------------------------------------------------------------

-- -----------------------------------------------------
-- Trigger calculates diceroll into table before entry of player's positions on turn.
-- -----------------------------------------------------

DELIMITER ??
CREATE TRIGGER calc_move_roll BEFORE INSERT
ON Moves
FOR EACH ROW

BEGIN 
	SET new.moveRoll = (new.moveEnd - new.moveStart);

END ??
DELIMITER ;
-- -----------------------------------------------------
-- Trigger increments a player's personal & assigned game's total roll counts upon entering a new move into Moves table..
-- -----------------------------------------------------

DELIMITER ??
CREATE TRIGGER append_player_moves_made_total AFTER INSERT
ON Moves
FOR EACH ROW

BEGIN
	-- Update's player's personal stats page.
	UPDATE PlayerList 
	SET playerTotalMovesMade = playerTotalMovesMade + 1
	WHERE playerListID = (SELECT pl_PlayerListID FROM players p 
						WHERE p.playerID = (SELECT players_playerID FROM Moves
						ORDER BY moveID DESC Limit 1));
	
    -- Updates the same player's stats on the game itself.
	UPDATE Players
    SET playerMovesTaken = playerMovesTaken + 1
    WHERE (playerID = (SELECT players_playerID FROM moves
						ORDER BY moveID DESC LIMIT 1)
	AND game_gameID = (SELECT game_gameID FROM moves
						ORDER BY moveID DESC LIMIT 1));
						
END ??
DELIMITER ;

-- -----------------------------------------------------
-- Trigger a move's final roll position to the specific player for current record purpose.
-- -----------------------------------------------------

DELIMITER ??
CREATE TRIGGER assign_move_end_position_to_player_on_players_table AFTER INSERT
ON Moves
FOR EACH ROW

BEGIN

	DECLARE playerRollEnd INT;
    
    SET playerRollEnd = (SELECT moveEnd FROM Moves
	ORDER BY moveID DESC LIMIT 1);

	UPDATE Players
	SET playerPosition = playerRollEnd WHERE playerID = (SELECT players_playerID FROM Moves
		ORDER BY moveID DESC LIMIT 1);

END ??
DELIMITER ;

-- -----------------------------------------------------
-- Trigger notes whether player's roll triggered a special square (snake/ladder/boost) and assigns dedicated value tracker to true if so.
-- -----------------------------------------------------
DELIMITER ??
CREATE TRIGGER assert_if_player_lands_on_special_square BEFORE INSERT
ON Moves
FOR EACH ROW

BEGIN

	SET @moveEndPosition = new.moveStart + new.moveRoll;
	
    IF @moveEndPosition IN (SELECT snakeHead FROM snakes) 
    THEN SET new.landedOnSnake = 1;
	END IF;
        
    IF @moveEndPosition IN (SELECT ladderFoot FROM Ladders) 
    THEN SET new.landedOnLadder = 1;
	END IF;
    
    IF @moveEndPosition IN (SELECT boostLocation FROM Boosts) 
    THEN SET new.landedOnBoost = 1;    
	END IF;

END ??
DELIMITER ;

-- -----------------------------------------------------
-- Trigger marks if player won game during turn. Increments player, playerList and marks Game as complete.
-- -----------------------------------------------------

DELIMITER ??
CREATE TRIGGER confirm_if_player_has_won_game BEFORE INSERT
ON Moves
FOR EACH ROW
	
BEGIN
	SET @moveEndPosition = new.moveStart + new.moveRoll;
    
    IF @moveEndPosition >= (SELECT boardSize FROM game
    WHERE gameID = (SELECT game_gameID FROM moves
					ORDER BY moveID DESC LIMIT 1))
		
	THEN 
		UPDATE Players
        SET players.playerWonGame = players.playerWonGame + 1 WHERE players.playerID = 
			(SELECT players_playerID FROM moves
			ORDER BY moveID DESC LIMIT 1);
		UPDATE Game
        SET game.gameHasEnded = 1 WHERE gameID = (SELECT game_gameID FROM Players WHERE playerWonGame = 1);

    
    END IF;
    
END ??
DELIMITER ;

-- -----------------------------------------------------
-- Trigger increments game round if last
-- -----------------------------------------------------



-- ----------------------------------------------------------------------------------
--
--
-- DUMMY DATA
-- 
--
-- ----------------------------------------------------------------------------------

DROP PROCEDURE IF EXISTS insert_dummy_data;

DELIMITER !!
CREATE PROCEDURE insert_dummy_data() 
BEGIN
INSERT INTO dice (diceCount, diceFaces) VALUES (1, 6);
INSERT INTO dice (diceCount, diceFaces) VALUES (2, 6);
INSERT INTO dice (diceCount, diceFaces) VALUES (1, 10);



insert into Game(gameRound, boardSize, gameHasEnded, boostSquarefeature, winningSquareOnlyFeature, Dice_diceID)
values (0, 25, false, false, false, 1);
insert into Game(gameRound, boardSize, gameHasEnded, boostSquarefeature, winningSquareOnlyFeature, Dice_diceID)
values (0, 36,  false, true, false, 1);
insert into Game(gameRound, boardSize, gameHasEnded, boostSquarefeature, winningSquareOnlyFeature, Dice_diceID)
values (0, 100, false, true, true, 2);

CALL add_new_game(50, 5);

CALL add_new_snake (16,8, 1);
CALL add_new_snake (16,8, 2);
CALL add_new_snake (16,8, 3);

CALL add_new_snake (7, 3, 1);
CALL add_new_snake (7, 3, 2);
CALL add_new_snake (7, 3, 3);

CALL add_new_ladder(9, 13, 1);
CALL add_new_ladder(9, 13, 2);
CALL add_new_ladder(9, 13, 3);

CALL add_new_ladder (4, 21, 1);
CALL add_new_ladder (4, 21, 2);
CALL add_new_ladder (4, 21, 3);

CALL add_new_boost(14, 1);
CALL add_new_boost (14, 2);
CALL add_new_boost (14, 3);
CALL add_new_boost (19, 1);
CALL add_new_boost (19, 2);
CALL add_new_boost (19, 3);

INSERT INTO PlayerList(playerName, playerWinCount, playerTotalMovesMade) VALUES ("Fred", 2, 45);
INSERT INTO PlayerList(playerName, playerWinCount, playerTotalMovesMade) VALUES ("Sally", 1, 61);
INSERT INTO PlayerList(playerName, playerWinCount, playerTotalMovesMade) VALUES ("Burt", 4, 32);
INSERT INTO PlayerList(playerName, playerWinCount, playerTotalMovesMade) VALUES ("Wendy", 5, 25);

CALL add_new_player("Bill");

CALL add_new_player_to_game('ORANGE', 1, 1);
CALL add_new_player_to_game('GREEN', 1, 2);
CALL add_new_player_to_game('BLUE', 1, 3);
CALL add_new_player_to_game('ORANGE', 2, 1);
CALL add_new_player_to_game('GREEN', 2, 3);
CALL add_new_player_to_game('ORANGE', 3, 4);
CALL add_new_player_to_game('GREEN', 3, 3);

CALL add_player_move(0, 4, 1, 1);
CALL add_player_move(0, 5, 2, 1);
CALL add_player_move(0, 7, 3, 1);
CALL add_player_move(4, 10, 1, 1);
CALL add_player_move(5, 11, 2, 1);
CALL add_player_move(7, 11, 3, 1);
CALL add_player_move(10, 16, 1, 1);
CALL add_player_move(11, 14, 2, 1);
CALL add_player_move(11,17, 3, 1);
CALL add_player_move(0, 12, 4, 2);
CALL add_player_move(0, 7, 5, 2);
CALL add_player_move(12, 14, 4, 2);
CALL add_player_move(7, 15, 5, 2);
CALL add_player_move(10, 15, 6, 3);
CALL add_player_move(10, 15, 7, 3);

CALL add_player_move(10, 100, 6, 3);

END !!

DELIMITER ;


-- ----------------------------------------------------------------------------------
--
--
--  SCRIPT & PROCEDURE TESTS
--
--
-- ----------------------------------------------------------------------------------

CALL insert_dummy_data;

CALL select_game(1, @gameChoice);
CALL select_players_from_game(@gameChoice); 
CALL select_dice_choice_from_game(@gameChoice);

SELECT * FROM players;
SELECT * FROM playerList;
SELECT * FROM moves;
SELECT * FROM game;


CALL select_game_snakes(@gameChoice);


SET @snakeResult = snake_count_landed_in_game(1);
SET @ladderResult = ladder_count_landed_in_game(1);
SET @boostResult = boost_count_landed_in_game(1);

SELECT @snakeResult AS 'Snakes count', @ladderResult AS 'Ladders Count', @boostResult AS 'Boosts count';

SET @longestGame = find_longest_game();

SELECT @longestGame AS 'Longest Game (turns)';





SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

