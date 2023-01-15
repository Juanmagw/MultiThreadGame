package com.jgomwal111.proyectomultihilo_psp.controller;

import com.jgomwal111.proyectomultihilo_psp.model.dataObject.Enemy;
import com.jgomwal111.proyectomultihilo_psp.model.dataObject.Character;
import com.jgomwal111.proyectomultihilo_psp.utils.message.ConfirmMessage;
import com.jgomwal111.proyectomultihilo_psp.utils.message.InfoMessage;
import com.jgomwal111.proyectomultihilo_psp.utils.message.Message;
import javafx.scene.control.ButtonType;

public class GameController {

    Character p = new Character();
    Thread t;
    Enemy e = new Enemy();

    /**
     * Method that shows if the Player is waiting or not
     * @param waiting boolean that says if Player is waiting
     * @return true is Player is waiting or false if not
     */
    public boolean waiting(boolean waiting){
        waiting = false;
        t = new Thread(p);
        while(!waiting){
            if(!t.isInterrupted()){
                p.getWait().setWait(true);
                waiting = true;
            }
        }
        return waiting;
    }

    /**
     * Method that shows if the Player find an Enemy
     * @return true if the Player find an Enemy or false if not
     */
    public boolean walkToEnemy(){
        boolean waiting = false;
        int pos=p.getPosition();
        t = new Thread(p);
        if(!t.isInterrupted()){
            while(!waiting(waiting)){
                p.setPosition(pos++);
                int randomSteps = (int)Math.floor(Math.random() * 999);
                int rSCont = 0;
                rSCont+=randomSteps;
                while(p.getPosition()==rSCont){
                    waiting = true;
                    new InfoMessage("The player found an enemy, it's time to fight").showMessage();

                    //battle();
                }
            }
        }
        return waiting;
    }

    public boolean attack(){
        boolean result = false;
        while(!waiting(result)){
            if(p.getAttack()>e.getDefense()){
                e.setHealth(e.getHealth()-(p.getAttack()-e.getDefense()));
                new InfoMessage("The enemy lost "+e.getHealth()).showMessage();
                result = true;
            }else{
                new InfoMessage("The attack failed").showMessage();
            }
        }
        return result;
    }

    public boolean defend(){
        boolean result = false;
        while(!waiting(result)){
            if(e.getAttack()>p.getDefense()){
                p.setHealth(p.getHealth()-(e.getAttack()-p.getDefense()));
                new InfoMessage("The player "+p.getName()+" lost "+p.getHealth()).showMessage();
                result = true;
            }else{
                new InfoMessage("The defense was succesfully").showMessage();
            }
        }
        return result;
    }

    public boolean surrend(){
        boolean result = false;
        while(!waiting(result)){
            Message m = new ConfirmMessage("Do you want to surrender?");
            m.showMessage();
            if(((ConfirmMessage)m).getBt()== ButtonType.OK){
                System.exit(0);
                result = true;
            }
        }
        return result;
    }
}
