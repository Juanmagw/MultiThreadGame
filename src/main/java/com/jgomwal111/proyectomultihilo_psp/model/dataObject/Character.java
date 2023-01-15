package com.jgomwal111.proyectomultihilo_psp.model.dataObject;

import com.jgomwal111.proyectomultihilo_psp.log.Log;
import com.jgomwal111.proyectomultihilo_psp.utils.chronometer.SynchronizedMethods;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "character")
public class Character implements Runnable {
    @Id
    @Column(name = "ID")
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
    @JoinColumn(name = "ID_USER")
    @ManyToOne(fetch = FetchType.LAZY)
    private int owner;
    private SynchronizedMethods wait = new SynchronizedMethods();


    public Character() {
    }
    public Character(String name, int health, int damage, int defense, int position, SynchronizedMethods wait) {
        this.name = name;
        this.health = health;
        this.attack = damage;
        this.defense = defense;
        this.position = position;
        this.wait.setWait(false);
    }

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

    public void run(){
        if(this.position>=0){
            while(this.position<=position++){
                try {
                    Thread.sleep(1);
                    this.position++;
                } catch (InterruptedException e) {
                    Log.warningLogging("Error at move");
                }
            }
        }
    }
}
