package com.jgomwal111.proyectomultihilo_psp.model.dataObject;

import java.util.Objects;

public class Enemy {

    /**
     * Attributes of this
     */
    private int id;
    private int health;
    private int attack;
    private int defense;

    /**
     * Constructors
     */
    public Enemy() {
    }
    public Enemy(int health, int attack, int defense) {
        this.health = health;
        this.attack = attack;
        this.defense = defense;
    }

    /**
     * Getters and Setters
     */
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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

    /**
     * ToString of this
     * @return
     */
    @Override
    public String toString() {
        return "Enemy{" +
                "health=" + health +
                ", damage=" + attack +
                ", defense=" + defense +
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
        if (!(o instanceof Enemy)) return false;
        Enemy enemy = (Enemy) o;
        return getId() == enemy.getId();
    }
    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
