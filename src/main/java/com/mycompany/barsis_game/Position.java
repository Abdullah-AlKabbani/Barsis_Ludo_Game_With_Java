/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barsis_game;

/**
 *
 * @author Csmooo
 */
public class Position {
    public int i;
    public int j;

    Position(){
        i = 0;
        j = 0;
    }
    
    public boolean checkSafeCell(){
        if(this.i == 1 && this.j == 7) return true;
        if(this.i == 1 && this.j == 9) return true;
        if(this.i == 7 && this.j == 1) return true;
        if(this.i == 9 && this.j == 1) return true;
        if(this.i == 7 && this.j == 15) return true;
        if(this.i == 9 && this.j == 15) return true;
        if(this.i == 15 && this.j == 7) return true;
        if(this.i == 15 && this.j == 9) return true;
        return false;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public void setI(int i) {
        this.i = i;
    }

    public void setJ(int j) {
        this.j = j;
    }
    
    public void printPosition(){
        System.out.println("\nPosition is");
        System.out.println("i = " + this.i);
        System.out.println("j = " + this.j +"\n");
    }
}
