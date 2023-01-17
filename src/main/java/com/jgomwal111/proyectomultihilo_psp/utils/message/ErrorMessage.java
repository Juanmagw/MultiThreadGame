package com.jgomwal111.proyectomultihilo_psp.utils.message;

import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

public class ErrorMessage extends Message {

    /**
     * Constructors
     * @param message Message to show
     */
    public ErrorMessage(String message) {
        super(message);
    }

    /**
     * Method that emits an Error message
     */
    @Override
    public void showMessage() {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.initStyle(StageStyle.TRANSPARENT);
        a.setHeaderText(null);
        a.setContentText(this.message);
        a.showAndWait();
    }
}
