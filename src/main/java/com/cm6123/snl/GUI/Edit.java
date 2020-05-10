package com.cm6123.snl.GUI;
/**
 * Edit choices will only ever be one of 5 choices below.
 */
public enum Edit {
    /**
     * confirms edit choice is a snake for opening EditChoicePanel & adding database data -
     * must be provided with a game id & table id inside EditChoicePanel.
     */
    SNAKE,
    /**
     * confirms edit choice is a Ladder for opening EditChoicePanel & adding database data -
     * must be provided with a game id & table id inside EditChoicePanel.
     */
    LADDER,
    /**
     * confirms edit choice is a Boost for opening EditChoicePanel & adding database data -
     * must be provided with a game id & table id inside EditChoicePanel.
     */
    BOOST,
    /**
     * confirms edit choice is a player for opening EditChoicePanel & adding database data -
     * only requires table ID inside EditChoicePanel.
     */
    PLAYER,
    /**
     * confirms edit choice is a dice for opening EditChoicePanel & adding database data -
     * only requires table ID inside EditChoicePanel.
     */
    DIE
}
