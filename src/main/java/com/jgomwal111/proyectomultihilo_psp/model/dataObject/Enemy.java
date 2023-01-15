package com.jgomwal111.proyectomultihilo_psp.model.dataObject;

import java.util.Objects;

public class Enemy {

    private int id;
    private int health;
    private int attack;
    private int defense;

    public Enemy() {
    }

    public Enemy(int id, int health, int damage, int defense) {
        this.id = id;
        this.health = health;
        this.attack = damage;
        this.defense = defense;
    }

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

    @Override
    public String toString() {
        return "Enemy{" +
                "health=" + health +
                ", damage=" + attack +
                ", defense=" + defense +
                '}';
    }

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
