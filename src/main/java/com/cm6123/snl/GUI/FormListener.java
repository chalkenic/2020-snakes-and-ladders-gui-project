package com.cm6123.snl.GUI;

import java.util.EventListener;

/**
 * Default interface that ensures any Panel using FormListener has to include below methods.
 */
public interface FormListener extends EventListener {
     /**
      * Method to be used for adding data from forms into database.
      * @param data
      */
     void formDatabaseEntry(LoadingFormEvent data);

     /**
      * used for Custom messages.
      */
     void incorrectEntryMessage();
}
