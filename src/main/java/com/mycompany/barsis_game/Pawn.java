/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barsis_game;

/**
 *
 * @author Csmooo
 */
public class Pawn {
    public String playerType;
    public int pawnID;
    public boolean inGame;
    public Path path;
    public Position position;
    public int counter;

    public Pawn(String playerType, int pawnID) {
        this.playerType = playerType;
        this.pawnID = pawnID;
        this.inGame = false;
        this.counter = 0;
        path = new Path(playerType);

        this.position = path.getPosition(0);
    }
    
    public void setPosition(Position position){
        this.position = position;
    }

    public void resetCounter() {
        this.counter = 0;
        this.position = path.getPosition(0);
    }

    // move
    public void increaseCounter(int value) {
        if((this.counter + value) <=73){
            this.counter += value;
            this.position = path.getPosition(counter);
        }
        else{
            this.counter = 73;
            this.position = path.getPosition(counter);
        }
    }

    public String getPlayerType() {
        return playerType;
    }

    public void setPlayerType(String playerType) {
        this.playerType = playerType;
    }
    
    public boolean getInGame() {
        return inGame;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    public int getCounter() {
        return counter;
    }
}
