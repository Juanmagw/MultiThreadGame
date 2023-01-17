package com.jgomwal111.proyectomultihilo_psp.controller;

import com.jgomwal111.proyectomultihilo_psp.model.dataObject.Enemy;
import com.jgomwal111.proyectomultihilo_psp.model.dataObject.Character;
import com.jgomwal111.proyectomultihilo_psp.utils.chronometer.Chronometer;
import com.jgomwal111.proyectomultihilo_psp.utils.message.ConfirmMessage;
import com.jgomwal111.proyectomultihilo_psp.utils.message.InfoMessage;
import com.jgomwal111.proyectomultihilo_psp.utils.message.Message;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    /**
     * Attributes of this
     */
    Character ch = new Character("Guts", 20, 2, 3, 0);
    Enemy e = new Enemy(30, 4, 2);
    Chronometer c;
    Thread t;

    @FXML
    Button btnAddPlayer;
    @FXML
    Button btnSurrender;

    @FXML
    VBox vBCage = new VBox();
    @FXML
    Button btnAttack;
    @FXML
    Button btnDefense;
    @FXML
    Button btnSurrender2;

    @FXML
    TextField tfPosition;

    @FXML
    TextField tfChronometer;

    /**
     * Method that shows if the Player is waiting or not
     *
     * @param waiting boolean that says if Player is waiting
     * @return true is Player is waiting or false if not
     */
    public boolean waiting(boolean waiting) {
        waiting = false;
        t = new Thread(ch);
        while (!waiting) {
            if (!t.isInterrupted()) {
                ch.getWait().setWait(true);
                waiting = true;
            }
        }
        return waiting;
    }

    /**
     * Method that shows if the Player find an Enemy
     * @return true if the Player find an Enemy or false if not
     */
    public boolean walkToEnemy() {
        boolean waiting = false;
        int pos = ch.getPosition();
        if (!t.isInterrupted()) {
            while (!waiting(waiting)) {
                btnAddPlayer.setVisible(true);
                btnSurrender.setVisible(true);
                vBCage.setVisible(false);
                while (!c.isInterrupted() && !t.isInterrupted()) {
                    tfChronometer.setText(this.c.getSessionTime());
                    tfPosition.setText(String.valueOf(this.ch.getPosition()));
                }
                ch.setPosition(pos++);
                int randomSteps = (int) Math.floor(Math.random() * 999);
                int rSCont = 0;
                rSCont += randomSteps;
                while (ch.getPosition() == rSCont) {
                    waiting = true;
                    new InfoMessage("The player found an enemy, it's time to fight").showMessage();
                    //battle();
                }
            }
        }
        return waiting;
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

    /**
     * Method that permit the Character attacks an Enemy
     * @return true if the Character attacks or false if it doesn't
     */
    @FXML
    public boolean attack() {
        boolean result = false;
        btnAddPlayer.setVisible(false);
        btnSurrender.setVisible(false);
        vBCage.setVisible(true);
        while (!waiting(result)) {
            if (ch.getAttack() > e.getDefense()) {
                e.setHealth(e.getHealth() - (ch.getAttack() - e.getDefense()));
                new InfoMessage("The enemy lost " + e.getHealth()).showMessage();
                result = true;
            } else {
                new InfoMessage("The attack failed").showMessage();
            }
        }
        return result;
    }

    /**
     * Method that permit the Character defend him/herself from an Enemy
     * @return true if the Character defends or false if it doesn't
     */
    @FXML
    public boolean defend() {
        boolean result = false;
        btnAddPlayer.setVisible(false);
        btnSurrender.setVisible(false);
        vBCage.setVisible(true);
        while (!waiting(result)) {
            if (e.getAttack() > ch.getDefense()) {
                ch.setHealth(ch.getHealth() - (e.getAttack() - ch.getDefense()));
                new InfoMessage("The player " + ch.getName() + " lost " + ch.getHealth()).showMessage();
                result = true;
            } else {
                new InfoMessage("The defense was succesfully").showMessage();
            }
        }
        return result;
    }

    /**
     * Method that permit the Character to surrend in a battle and lose the game
     * @return true if the Character surrends or false if it doesn't
     */
    @FXML
    public boolean surrend() {
        boolean result = false;
        btnAddPlayer.setVisible(false);
        btnSurrender.setVisible(false);
        vBCage.setVisible(true);
        while (!waiting(result)) {
            Message m = new ConfirmMessage("Do you want to surrender?");
            m.showMessage();
            if (((ConfirmMessage) m).getBt() == ButtonType.OK) {
                System.exit(0);
                result = true;
            }
        }
        return result;
    }

    /**
     * Method that starts the chronometer's thread
     */
    public void startChrono() {
        c = new Chronometer();
        c.start();
        while (!c.isInterrupted()) {
            tfChronometer.setText(c.getSessionTime());
            btnAddPlayer.setVisible(false);
        }
    }

    /**
     * Method that is started when the app opens
     * @param location
     * The location used to resolve relative paths for the root object, or
     * {@code null} if the location is not known.
     *
     * @param resources
     * The resources used to localize the root object, or {@code null} if
     * the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnAddPlayer.setVisible(true);
        btnSurrender.setVisible(true);
        vBCage.setVisible(false);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                btnAddPlayer.setOnAction(action -> {
                    if (t == null && c == null) {
                        startChrono();
                        //walkToEnemy();
                    }
                });
                btnSurrender.setOnAction(action -> {
                    System.exit(0);
                });
            }
        });
    }
}
