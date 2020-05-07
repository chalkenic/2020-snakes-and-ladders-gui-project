package com.cm6123.snl.GUI;

public class BoardCategory {
        private Integer id;
        private String text;
        public BoardCategory(final int newID, final String newText) {
            this.id = newID;
            this.text = newText;
        }

        @Override
        public String toString() {
            return text;
        }
        public int getID() {
            return id;
        }
}
