package com.jgomwal111.proyectomultihilo_psp.model.dao;

import com.jgomwal111.proyectomultihilo_psp.connection.DBConnection;
import com.jgomwal111.proyectomultihilo_psp.model.dataObject.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class UserDAO extends User{

    /**
     * Attributes of this
     */
    private static EntityManager manager;
    private User u;

    /**
     * Method that creates an User on the DataBase
     * @return true it creates the User o false it doesn't
     */
    public static boolean insert(User u) {
        manager = DBConnection.getConnect().createEntityManager();
        boolean result=false;
        manager.getTransaction().begin();
        manager.persist(u);
        result = true;
        manager.getTransaction().commit();
        manager.close();
        return result;
    }

    /**
     * Method that searchs an User with his name on the DataBase
     * @param name Name of User who is searching
     * @return User that is searching or null if the User is not find
     */
    public static User get(String name) {
        manager = DBConnection.getConnect().createEntityManager();
        User aux = new User();
        aux.setName(name);
        Query q = manager.createNativeQuery("Select * FROM user WHERE name="+name);
        manager.close();
        return aux;
    }
}
