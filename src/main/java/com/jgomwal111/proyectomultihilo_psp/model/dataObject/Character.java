package com.jgomwal111.proyectomultihilo_psp.model.dataObject;

import com.jgomwal111.proyectomultihilo_psp.log.Log;
import com.jgomwal111.proyectomultihilo_psp.utils.chronometer.SynchronizedMethods;
import javafx.application.Platform;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "character")
public class Character implements Runnable, Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Attributes of this
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "HEALTH")
    private int health;
    @Column(name = "ATTACK")
    private int attack;
    @Column(name = "DEFENSE")
    private int defense;
    @Column(name = "POSITION")
    private int position;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", referencedColumnName = "ID")
    private User owner;
    @Transient
    private SynchronizedMethods wait = new SynchronizedMethods();

    /**
     * Constructors
     */
    public Character() {
    }
    public Character(String name, int health, int damage, int defense, int position) {
        this.name = name;
        this.health = health;
        this.attack = damage;
        this.defense = defense;
        this.position = position;
    }
    public Character(String name, int health, int damage, int defense, int position, SynchronizedMethods wait) {
        this.name = name;
        this.health = health;
        this.attack = damage;
        this.defense = defense;
        this.position = position;
        this.wait.setWait(false);
    }

    /**
     * Getters and Setters
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
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public int getAttack() {
        return attack;
    }
    public void setAttack(int attack) {
        this.attack = attack;
    }
    public int getDefense() {
        return defense;
    }
    public void setDefense(int defense) {
        this.defense = defense;
    }
    public int getPosition() {
        return position;
    }
    public void setPosition(int position) {
        this.position = position;
    }
    public SynchronizedMethods getWait() {
        return wait;
    }
    public void setWait(SynchronizedMethods wait) {
        this.wait = wait;
    }
    public User getOwner() {
        return owner;
    }
    public void setOwner(User owner) {
        this.owner = owner;
    }

    /**
     * ToString of this
     * @return
     */
    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", health=" + health +
                ", damage=" + attack +
                ", defense=" + defense +
                ", position=" + position +
                '}';
    }

    /**
     * Equals and HashCode
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Character)) return false;
        Character player = (Character) o;
        return getId() == player.getId();
    }
    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    /**
     * Method that permit to start a Thread when it calls start()
     */
    public void run(){
        Platform.runLater(() -> {
            boolean walking = false;
            while(!walking){
                int randomSteps = (int)Math.floor(Math.random() * 999);
                int rSCont = 0;
                while(this.position<=position++){
                    try {
                        Thread.sleep(1000);
                        this.position++;
                    } catch (InterruptedException e) {
                        Log.warningLogging("Error at move");
                    }
                }
            }
        });
    }
}
