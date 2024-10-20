/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barsis_game;

/**
 *
 * @author Csmooo
 */
public class Patch {
    public char[][] grid;
    public Position position;

    public Patch() {
        position = new Position();
        grid = new char[18][18];
        initialize();
    }

    public void initialize() {
        for (int i = 0; i < 17 ; i++) {
            position.i = i;
            for (int j = 0; j < 17; j++) {
                position.j = j;
                if(position.checkSafeCell()){
                    grid[i][j] = 'X';
                }
                else if((i == 7 || i == 8 || i == 9) && (j==7 || j==8 || j==9)){
                    grid[i][j] = ' ';
                }
                else if(i == 7 || i == 8 || i == 9 || j==7 || j==8 || j==9){
                    grid[i][j] = '*';
                }
                else{
                    grid[i][j] = ' ';
                }
            }
        }
    }

    public void printPatch() {
        System.out.println("\n\033[0;31m----------------------------------------\u001B[0m");
        for (int i = 0; i <= 17; i++) {
            for (int j = 0; j < 17; j++) {
                if(grid[i][j] == 'H'){
                    System.out.print("\033[0;32m");
                }
                else if(grid[i][j] == 'C'){
                    System.out.print("\033[0;33m");
                }
                else if(grid[i][j] == 'X'){
                    System.out.print("\u001B[35m");
                }
                else if(grid[i][j] == '*'){
                    System.out.print("\033[0;34m");
                }
                else if((i == 9 || i == 7) && j == 9){
                    System.out.print("\033[0;31m");
                }
                else{
                    System.out.print("\u001B[0m");
                }

               System.out.print(grid[i][j] + "\u001B[0m");
            }
            System.out.println();
        }
        System.out.println("\n\033[0;31m----------------------------------------\u001B[0m");
    }
}