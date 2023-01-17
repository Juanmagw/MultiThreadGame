package com.jgomwal111.proyectomultihilo_psp.utils.message;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.StageStyle;

public class ConfirmMessage extends Message {

    /**
     * Attributes of this
     */
    private ButtonType bt;

    /**
     * Constructors
     * @param message Mensaje a mostrar
     */
    public ConfirmMessage(String message) {
        super(message);
    }

    /**
     * It obtains the button response
     * @return Value of the button that the User pushes
     */
    public ButtonType getBt() {
        return bt;
    }

    /**
     * Method that emits a Confirmation message
     */
    @Override
    public void showMessage() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(this.message);
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.setHeaderText(null);
        bt = alert.showAndWait().get();
    }
}
