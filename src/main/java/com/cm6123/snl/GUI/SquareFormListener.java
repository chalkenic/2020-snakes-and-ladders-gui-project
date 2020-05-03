package com.cm6123.snl.GUI;

import java.util.EventListener;

public interface SquareFormListener extends EventListener {
     void formDatabaseEntry(NewSquareFormEvents data);
     void appendTextToPanel(String incorrectEntry);
     void incorrectEntryMessage();
}
