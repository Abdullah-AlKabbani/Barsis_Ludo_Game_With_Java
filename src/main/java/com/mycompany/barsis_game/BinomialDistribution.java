/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barsis_game;

/**
 *
 * @author Csmooo
 */
public class BinomialDistribution {
    public int number_of_trials;
    public double probability_of_success;
    public int number_of_successes;

    public void BinomialDistribution(){
        number_of_trials = 1;
        probability_of_success = 1;
        number_of_successes = 1;
    }

    public void setK(int number_of_successes) {
        this.number_of_successes = number_of_successes;
    }

    public void setP(double probability_of_success) {
        this.probability_of_success = probability_of_success;
    }

    public void setN(int number_of_trials) {
        this.number_of_trials = number_of_trials;
    }

    public static double calculateBinomialProbability(int n, double p, int k) {
        return (factorial(n) / (factorial(k) * factorial(n - k))) * Math.pow(p, k) * Math.pow(1 - p, n - k);
    }
    
    public static long factorial(int num) {
        if (num == 0 || num == 1) {
            return 1;
        } else {
            return num * factorial(num - 1);
        }
    }
}
