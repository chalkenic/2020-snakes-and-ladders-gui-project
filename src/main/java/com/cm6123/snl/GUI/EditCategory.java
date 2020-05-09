package com.cm6123.snl.GUI;

import com.cm6123.snl.gameDB.LoadDataDBManager;

public class EditCategory {
        private Integer jlistID;
        private Integer gameID;
        private LoadDataDBManager data;
        private Edit editChoice;

        public EditCategory(final LoadDataDBManager loader, final Integer id, final Edit choice, final Integer game ) {
            this.jlistID = id;
            this.gameID = game;
            this.data = loader;
            this.editChoice = choice;

        }
        public EditCategory(final LoadDataDBManager loader, final Integer id, final Edit choice) {
            this.jlistID = id;
            this.editChoice = choice;
            this.data = loader;
//            this.text = newText;

//            if (editChoice == Edit.DIE) {
//                printDiceData();
            }

        public int getListID() {
            return jlistID;
        }
        public Integer getGameID() { return gameID; }


//        public String printDiceData() {
//            String addEelement =
//
//            return addEelement;
//        }
}
