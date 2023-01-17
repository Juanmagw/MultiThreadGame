package com.jgomwal111.proyectomultihilo_psp.utils.message;

public abstract class Message {

    /**
     * Attributes of this
     */
    protected String message;

    /**
     * Constructors
     * @param message Message to show
     */
    public Message(String message) {
        this.message = message;
    }
    public Message() {

    }

    /**
     * Abstract method that shows the message
     */
    abstract public void showMessage();
}
