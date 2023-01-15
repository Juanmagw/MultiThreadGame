package com.jgomwal111.proyectomultihilo_psp.controller;

import com.jgomwal111.proyectomultihilo_psp.utils.chronometer.Chronometer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private Thread t;
    private Stage stage;

    /**
     * Starts the controller elements
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Chronometer c = new Chronometer();
        t = new Thread(c);

    }



    /*public void battle(){
        int option = -1;
        Scanner s = new Scanner(System.in);
        option = s.nextInt();
        switch(option){
            case 0: System.exit(0);
                break;
            case 1: attack();
                break;
            case 2: defend();
                break;
            case 3: surrend();
                break;
        }
    }*/
}