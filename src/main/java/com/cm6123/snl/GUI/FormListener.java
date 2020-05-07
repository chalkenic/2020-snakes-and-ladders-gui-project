package com.cm6123.snl.GUI;

import java.util.EventListener;

public interface FormListener extends EventListener {
     void formDatabaseEntry(FormEvents data);
     void incorrectEntryMessage();
}
