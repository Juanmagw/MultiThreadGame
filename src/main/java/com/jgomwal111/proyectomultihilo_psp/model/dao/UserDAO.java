package com.jgomwal111.proyectomultihilo_psp.model.dao;

import com.jgomwal111.proyectomultihilo_psp.model.dataObject.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UserDAO extends User{

    private static EntityManagerFactory emf;
    private static EntityManager manager;

    /**
     * Mátodo que crea un User en la base de datos
     * @return true o false si lo crea o no
     */
    public static boolean insert(User u) {
        emf = Persistence.createEntityManagerFactory("mysql");
        manager = emf.createEntityManager();
        boolean result=false;
        manager.getTransaction().begin();
        manager.persist(u);
        result = true;
        manager.getTransaction().commit();
        manager.close();
        return result;
    }

    /**
     * Método que busca un User por su nombre en la base de datos
     * @param name Nombre del objeto a buscar
     * @return el User o null si lo ha encontrado o no
     */
    public static User get(String name) {
        User aux = manager.find(User.class, name);
        manager.close();
        return aux;
    }
}
