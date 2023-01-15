package com.jgomwal111.proyectomultihilo_psp.model.dataObject;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "USER")
public class User {

    /**
     * Atributos de user
     */
    @Id
    @Column(name = "ID")
    private int id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "PASSWORD")
    private String password;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Character> characters;

    /**
     * Constructores de User por defecto y fullBuild
     */
    public User(){
    }
    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    /**
     * Getters & Setters de User
     * @return
     */
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Método toString de User
     * @return
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * Método equals & hashCode de User
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId() == user.getId() && getName().equals(user.getName());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
