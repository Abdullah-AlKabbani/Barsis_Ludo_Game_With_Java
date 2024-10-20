/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barsis_game;

import java.util.Random;

/**
 *
 * @author Csmooo
 */
public class Dice {
    private Random random;
    public int last_value;

    public Dice() {
        random = new Random();
    }

    public int roll() {
        return random.nextInt(7);
    }
}
