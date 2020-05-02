package com.cm6123.snl.GUI;

import java.util.EventListener;

public interface FormListener extends EventListener {
     void formSpecialSquareEntry(NewSquareFormEvents data);
     void appendTextToPanel(String incorrectEntry);
}
