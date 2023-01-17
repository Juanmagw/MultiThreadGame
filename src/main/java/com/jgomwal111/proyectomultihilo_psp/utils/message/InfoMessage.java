package com.jgomwal111.proyectomultihilo_psp.utils.message;

import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

public class InfoMessage extends Message {

    /**
     * Constructors
     * @param message Message to show
     */
    public InfoMessage(String message) {
        super(message);
    }

    /**
     * Method that emits an Information message
     */
    @Override
    public void showMessage() {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.initStyle(StageStyle.TRANSPARENT);
        a.setHeaderText(null);
        a.setContentText(this.message);
        a.showAndWait();
    }
}
