package com.jgomwal111.proyectomultihilo_psp.connection;

import com.jgomwal111.proyectomultihilo_psp.log.Log;
import com.jgomwal111.proyectomultihilo_psp.utils.message.ErrorMessage;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBConnection {

    /**
     * Attributes of this
     */
    private static DBConnection _newInstance;
    private static EntityManagerFactory emf;

    /**
     * Method to connect with the DB
     */
    private DBConnection() {
        emf = Persistence.createEntityManagerFactory("mysql");
        if(emf==null) {
            new ErrorMessage("The connection couldn't works").showMessage();
            Log.warningLogging("The connection couldn't works");
        }
    }

    /**
     * Method to instance only one of this
     * @return The connection with the DB
     */
    public static EntityManagerFactory getConnect() {
        if(_newInstance==null) {
            _newInstance=new DBConnection();
        }
        return emf;
    }

    /**
     * It closes the connection
     */
    public static void close() {
        if(emf != null) {
            emf.close();
        }
    }
}
