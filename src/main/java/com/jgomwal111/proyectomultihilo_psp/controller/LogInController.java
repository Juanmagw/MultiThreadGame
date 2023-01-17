package com.jgomwal111.proyectomultihilo_psp.controller;

import com.jgomwal111.proyectomultihilo_psp.model.dao.UserDAO;
import com.jgomwal111.proyectomultihilo_psp.model.dataObject.User;
import com.jgomwal111.proyectomultihilo_psp.utils.message.ConfirmMessage;
import com.jgomwal111.proyectomultihilo_psp.utils.message.ErrorMessage;
import com.jgomwal111.proyectomultihilo_psp.utils.message.InfoMessage;
import com.jgomwal111.proyectomultihilo_psp.utils.message.Message;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LogInController {

    /**
     * Attributes of this
     */
    Stage stage;

    @FXML Button btnLogin;
    @FXML Button btnClose;

    @FXML TextField tfUserName;
    @FXML PasswordField pfUserPassword;

    /**
     * Format a string to protect the information using SHA256
     */
    public static String encrypt(String s) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        }catch(NoSuchAlgorithmException e) {
            //Log.warningLogging(e + "");
        }
        byte[] bytes = md.digest(s.getBytes());
        StringBuffer sb = new StringBuffer();

        for(byte b : bytes){
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    /**
     * Let the user LogIn if exists or create a new user account if it doesn't
     * @throws IOException
     */
    @FXML public void register() throws IOException {
        String name = tfUserName.getText();
        String password = pfUserPassword.getText();
        if(!name.equals("") && !password.equals("")){
            User u = new User(0, name, password);
            UserDAO uDAO = new UserDAO();
            u.setPassword(encrypt(password));
            if(!uDAO.get(name).getName().equals(u.getName())){
                Message m = new ConfirmMessage("The user doesn't exist.\n Do you want to create a new user?");
                m.showMessage();
                if(((ConfirmMessage)m).getBt()== ButtonType.OK){
                    uDAO.insert(u);
                    new InfoMessage("User added").showMessage();
                    changeGame();
                }
            }else{
                if(uDAO.get(name).getName().equals(name) && uDAO.get(name).getPassword().equals(encrypt(password))){
                    Message m = new InfoMessage("Logged in");
                    m.showMessage();
                    changeGame();
                }else{
                    new ErrorMessage("Name or password are wrong").showMessage();
                }
            }
        }else{
            new ErrorMessage("The fields can't be void, please enter the information").showMessage();
        }
    }

    /**
     * Change the window
     * @throws IOException
     */
    @FXML
    public void changeGame() throws IOException {
        this.stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("game.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 480);
        Stage s = new Stage();
        s.setScene(scene);
        s.setResizable(false);
        s.show();
    }

    /**
     * It permits to close the app
     */
    @FXML public void closeApp(){
        //stage.close();
        System.exit(0);
    }
}
