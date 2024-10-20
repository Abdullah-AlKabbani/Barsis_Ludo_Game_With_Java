/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barsis_game;

import java.util.*;

/**
 *
 * @author Csmooo
 */
public class Game {
    /*
     * +1 : مقلوب للأسفل هو المعتمد
     * 
     * الشكة 0
     * الدست 1
     * الدواق 2
     * ثلاثة 3
     * 4 اربعة
     * البنج 5
     * البارا 6
     * 
     */

    public Dice dice;
    public Patch patch;
    public String currentPlayer;
    public int roll_count;

    public BinomialDistribution[] binomialDistribution;
    public double cost;
    public int npnoods;
    public int ngnoods;
    public Game parent = null;

    public Pawn human_pawn1 = new Pawn("H", 1);
    public Pawn human_pawn2 = new Pawn("H", 2);
    public Pawn human_pawn3 = new Pawn("H", 3);
    public Pawn human_pawn4 = new Pawn("H", 4);

    public Pawn computer_pawn1 = new Pawn("C", 1);
    public Pawn computer_pawn2 = new Pawn("C", 2);
    public Pawn computer_pawn3 = new Pawn("C", 3);
    public Pawn computer_pawn4 = new Pawn("C", 4);

    public Stack<Pawn> pawnHumanStack = new Stack<>();
    public Stack<Pawn> pawnComputerStack = new Stack<>();

    public Stack<Pawn> pawnHumanFinish = new Stack<>();
    public Stack<Pawn> pawnComputerFinish = new Stack<>();

    public Stack<Pawn> pawnHumanStackIn = new Stack<>();
    public Stack<Pawn> pawnComputerStackIn = new Stack<>();

    public Game() {
        cost = 0;
        npnoods = 0;
        ngnoods = 0;

        dice = new Dice();
        patch = new Patch();
        currentPlayer = "H";
        roll_count = 1;
        binomialDistribution = new BinomialDistribution[7];
        for (int i = 0; i < 7; i++) {
            binomialDistribution[i] = new BinomialDistribution();
        }

        pawnHumanStack.push(human_pawn4);
        pawnHumanStack.push(human_pawn3);
        pawnHumanStack.push(human_pawn2);
        pawnHumanStack.push(human_pawn1);

        pawnComputerStack.push(computer_pawn4);
        pawnComputerStack.push(computer_pawn3);
        pawnComputerStack.push(computer_pawn2);
        pawnComputerStack.push(computer_pawn1);
    }

    Scanner scanner = new Scanner(System.in);

    public void playGame() {

        boolean gameEnded = false;

        System.out.println("\n" + "\u001B[35mEnter your choice from below: \u001B[0m" + "\n");
        System.out.println("If you want to Play first press: 1,");
        System.out.println("If not press: 2.");
        int choice = scanner.nextInt();

        if (choice == 1) {
            currentPlayer = "H";
        } else {
            currentPlayer = "C";
        }

        while (!gameEnded) {
            gameEnded = gameEnded();
            patch.printPatch();
            move();
            if (!pawnHumanStackIn.empty()) {
                humanPawnsFinish();
            }
            if (!pawnComputerStackIn.empty()) {
                computerPawnsFinish();
            }
            changePlayer();
        }
    }

    public void changePlayer() {
        if ((dice.last_value == 0 || dice.last_value == 1 || dice.last_value == 5 || dice.last_value == 6)) {
            if (currentPlayer == "H" && !pawnHumanStackIn.empty()) {
                currentPlayer = "H";
            } else if (currentPlayer == "C" && !pawnComputerStackIn.empty()) {
                currentPlayer = "C";
            } else {
                if (currentPlayer == "H")
                    currentPlayer = "C";
                else
                    currentPlayer = "H";
            }

        } else {
            if (currentPlayer == "H")
                currentPlayer = "C";
            else
                currentPlayer = "H";
        }
    }

    public boolean checkPawnFinish(Stack<Pawn> s) {
        for (Pawn p : s) {
            if (p.counter == 73) {
                return true;
            }
        }
        return false;
    }

    public Pawn findPawnInStack(Pawn p, Stack<Pawn> s) {
        for (Pawn i : s) {
            if (i.pawnID == p.pawnID) {
                return i;
            }
        }
        System.out.println("there is error in findPawnInStack function");
        return p;
    }

    public void humanPawnsFinish() {
        Pawn pawn = new Pawn("H", 0);
        if (checkPawnFinish(pawnHumanStackIn)) {
            for (Pawn p : pawnHumanStackIn) {
                if (p.counter == 73) {
                    pawn.pawnID = p.pawnID;
                }
            }
            pawnHumanFinish.push(removePawnFromStack(findPawnInStack(pawn, pawnHumanStackIn), pawnHumanStackIn));
            patch.grid[9][9] = (char) (pawnHumanFinish.size() + '0');
        }
    }

    public void computerPawnsFinish() {
        Pawn pawn = new Pawn("C", 0);
        if (checkPawnFinish(pawnComputerStackIn)) {
            for (Pawn p : pawnComputerStackIn) {
                if (p.counter == 73) {
                    deepCopyPawn(pawn, p);
                }
            }
            pawnComputerFinish
                    .push(removePawnFromStack(findPawnInStack(pawn, pawnComputerStackIn), pawnComputerStackIn));
            patch.grid[7][9] = (char) (pawnComputerFinish.size() + '0');
        }
    }

    public Pawn removePawnFromStack(Pawn p, Stack<Pawn> sp) {
        Stack<Pawn> tempStack = new Stack<>();
        Pawn currentPawn = new Pawn(p.playerType, p.pawnID);

        while (!sp.isEmpty()) {
            currentPawn = sp.pop();
            if (!currentPawn.equals(p)) {
                tempStack.push(currentPawn);
            } else {
                break; // Stop when the specified pawn is found
            }
        }

        // Pop elements from the temporary stack and push them back to the original
        // stack
        while (!tempStack.isEmpty()) {
            sp.push(tempStack.pop());
        }
        return currentPawn;
    }

    public boolean check_khal(int n) {
        return (n == 5 || n == 1);
    }

    public boolean gameEnded() {
        if (pawnHumanStack.empty() && pawnHumanFinish.size() == 4) {
            System.out.println("\n" + "\u001B[35m**** Human Win ****\u001B[0m" + "\n");
            return true;
        } else if (pawnComputerStack.empty() && pawnComputerFinish.size() == 4) {
            System.out.println("\n" + "\u001B[35m**** Computer Win ****\u001B[0m" + "\n");
            return true;
        } else {
            return false;
        }

    }

    public int steps(int num) {
        if (num == 0)
            return 6;
        else if (num == 1)
            return 11;
        else if (num == 2)
            return 2;
        else if (num == 3)
            return 3;
        else if (num == 4)
            return 4;
        else if (num == 5)
            return 25;
        else if (num == 6)
            return 12;
        else
            return -100;
    }

    public void move() {
        roll_count = 1;

        if (currentPlayer == "H") {
            humanRoll();
        } else if (currentPlayer == "C") {
            computerRoll();
        }
    }

    public double setBinomial(int value) {
        int n, k;
        double p;

        for (int i = 0; i <= 6; i++) {
            if (i == value) {
                binomialDistribution[i].number_of_successes++;
            }
            binomialDistribution[i].number_of_trials++;
        }
        n = binomialDistribution[value].number_of_trials;
        k = binomialDistribution[value].number_of_successes;
        p = binomialDistribution[value].probability_of_success;

        binomialDistribution[value].probability_of_success = BinomialDistribution.calculateBinomialProbability(n, p, k);
        System.out.println("The Binomial Distribution For value (" + value + ") = "
                + binomialDistribution[value].probability_of_success);
        return binomialDistribution[value].probability_of_success;
    }

    public void humanRoll() {
        System.out.println("press any key to roll the dice.");

        if (scanner.hasNext()) {
            String input = scanner.next();
        } else {
            System.out.println("No input available.");
        }

        dice.last_value = dice.roll();

        //setBinomial(dice.last_value);
        System.out.println("roll count = " + roll_count + ".");
        System.out.println("the dice value = " + dice.last_value + ".\n");

        if (pawnHumanStack.size() == 4) {
            if (dice.last_value != 5 && dice.last_value != 1) {
                if (roll_count != 3) {
                    roll_count++;
                    humanRoll();
                } else {
                    return;
                }
            } else {
                enterHumanPawn();
            }
        } else {
            humanMove();
        }
    }

    public void computerRoll() {
        dice.last_value = dice.roll();
        //setBinomial(dice.last_value);
        System.out.println("the dice value = " + dice.last_value + ".\n");

        if (pawnComputerStack.size() == 4) {
            if (dice.last_value != 5 && dice.last_value != 1) {
                if (roll_count != 3) {
                    roll_count++;
                    computerRoll();
                } else {
                    return;
                }
            } else {
                enterComputerPawn();
            }
        } else {
            computerMove();
        }
    }

    public void enterHumanPawn() {
        countSafeHuman = 0;
        if (pawnHumanStack.empty() || !check_khal(dice.last_value)) {
            System.out.println("Invalid move (all pawn on patch or not Khal) , please try again.");
            humanMove();
        }
        System.out.println("new pawn for " + currentPlayer + " enter the Game.");
        patch.grid[pawnHumanStack.peek().position.i][pawnHumanStack.peek().position.j] = 'H';
        pawnHumanStackIn.push(pawnHumanStack.pop());
        System.out.println("StackH size = " + pawnHumanStack.size() + ".\n");
    }

    public void enterComputerPawn() {
        if (pawnComputerStack.empty() || !check_khal(dice.last_value)) {
            System.out.println("Invalid move (all pawn on patch or not Khal) , please try again.");
            computerMove();
        } else {
            System.out.println("new pawn for " + currentPlayer + " enter the Game.");
            patch.grid[pawnComputerStack.peek().position.i][pawnComputerStack.peek().position.j] = 'C';
            pawnComputerStackIn.push(pawnComputerStack.pop());
            System.out.println("StackC size = " + pawnComputerStack.size() + ".\n");
        }
    }

    public boolean inStack(Stack<Pawn> pawnStack, int choice) {
        for (Pawn item : pawnStack) {
            if (item.pawnID == choice) {
                System.out.println("Invalid move (this pawn not on patch), please try again.");
                return true;
            }
        }
        return false;
    }

    public boolean checkSamePlace(Pawn pawn, Stack<Pawn> s) {
        for (Pawn i : s) {
            if (i.pawnID != pawn.pawnID && i.counter == pawn.counter) {
                return true;
            }
        }
        return false;
    }

    public boolean checkSafeCellEnemy(Pawn p) {
        if (p.path.getPosition((p.counter + steps(dice.last_value))).checkSafeCell()) {

            if (p.playerType == "H") {
                for (Pawn i : pawnComputerStackIn) {
                    if (i.position == p.path.getPosition(p.counter + steps(dice.last_value))) {
                        return true;
                    }
                }
                return false;
            } else {
                for (Pawn i : pawnHumanStackIn) {
                    if (i.position == p.path.getPosition(p.counter + steps(dice.last_value))) {
                        return true;
                    }
                }
                return false;
            }
        }
        return false;
    }

    int countSafeHuman = 0;

    public void pawnIncreaseHuman(Pawn pawn) {
        if (checkSafeCellEnemy(pawn)) {
            countSafeHuman++;
            System.out.println("There is Safe Cell Reserved.\n");
            if (pawnHumanStackIn.size() >= countSafeHuman) {
                humanMove();
            } else {
                return;
            }
        } else {
            countSafeHuman = 0;
            if (!checkSamePlace(pawn, pawnHumanStackIn)) {
                if (pawn.position.checkSafeCell()) {
                    patch.grid[pawn.position.i][pawn.position.j] = 'X';
                } else {
                    patch.grid[pawn.position.i][pawn.position.j] = '*';
                }
            }
            pawn.increaseCounter(steps(dice.last_value));
            patch.grid[pawn.position.i][pawn.position.j] = 'H';
            if (pawn.path.getPosition(pawn.counter) == computer_pawn1.path.getPosition(computer_pawn1.counter)) {
                pawnComputerStack.push(removePawnFromStack(computer_pawn1, pawnComputerStackIn));
                computer_pawn1.resetCounter();
            }
            if (pawn.path.getPosition(pawn.counter) == computer_pawn2.path.getPosition(computer_pawn2.counter)) {
                pawnComputerStack.push(removePawnFromStack(computer_pawn2, pawnComputerStackIn));
                computer_pawn2.resetCounter();
            }
            if (pawn.path.getPosition(pawn.counter) == computer_pawn3.path.getPosition(computer_pawn3.counter)) {
                pawnComputerStack.push(removePawnFromStack(computer_pawn3, pawnComputerStackIn));
                computer_pawn3.resetCounter();
            }
            if (pawn.path.getPosition(pawn.counter) == computer_pawn4.path.getPosition(computer_pawn4.counter)) {
                pawnComputerStack.push(removePawnFromStack(computer_pawn4, pawnComputerStackIn));
                computer_pawn4.resetCounter();
            }
        }
    }

    int checkSafeComputer = 0;

    public void pawnIncreaseComputer(Pawn pawn) {
        if (checkSafeCellEnemy(pawn)) {
            checkSafeComputer++;
            System.out.println("There is Safe Cell Reserved.\n");
            if (pawnComputerStackIn.size() >= checkSafeComputer) {
                Stack<Pawn> temp = new Stack<>();
                Pawn t;
                temp.addAll(pawnComputerStackIn);
                t = removePawnFromStack(pawn, pawnComputerStackIn);
                computerMove();
                pawnComputerStackIn.push(t);
            } else {
                return;
            }
        } else {
            if (!checkSamePlace(pawn, pawnComputerStackIn)) {
                if (pawn.position.checkSafeCell()) {
                    patch.grid[pawn.position.i][pawn.position.j] = 'X';
                } else {
                    patch.grid[pawn.position.i][pawn.position.j] = '*';
                }
            }
            pawn.increaseCounter(steps(dice.last_value));
            patch.grid[pawn.position.i][pawn.position.j] = 'C';
            if (pawn.path.getPosition(pawn.counter) == human_pawn1.path.getPosition(human_pawn1.counter)) {
                pawnHumanStack.push(removePawnFromStack(human_pawn1, pawnHumanStackIn));
                human_pawn1.resetCounter();
            }
            if (pawn.path.getPosition(pawn.counter) == human_pawn2.path.getPosition(human_pawn2.counter)) {
                pawnHumanStack.push(removePawnFromStack(human_pawn2, pawnHumanStackIn));
                human_pawn2.resetCounter();
            }
            if (pawn.path.getPosition(pawn.counter) == human_pawn3.path.getPosition(human_pawn3.counter)) {
                pawnHumanStack.push(removePawnFromStack(human_pawn3, pawnHumanStackIn));
                human_pawn3.resetCounter();
            }
            if (pawn.path.getPosition(pawn.counter) == human_pawn4.path.getPosition(human_pawn4.counter)) {
                pawnHumanStack.push(removePawnFromStack(human_pawn4, pawnHumanStackIn));
                human_pawn4.resetCounter();
            }
        }
    }

    public void movePawnIncrease(String type, int ID) {
        if (type == "H") {
            if (human_pawn1.pawnID == ID)
                pawnIncreaseHuman(human_pawn1);
            if (human_pawn2.pawnID == ID)
                pawnIncreaseHuman(human_pawn2);
            if (human_pawn3.pawnID == ID)
                pawnIncreaseHuman(human_pawn3);
            if (human_pawn4.pawnID == ID)
                pawnIncreaseHuman(human_pawn4);
        } else if (type == "C") {
            if (computer_pawn1.pawnID == ID)
                pawnIncreaseComputer(computer_pawn1);
            if (computer_pawn2.pawnID == ID)
                pawnIncreaseComputer(computer_pawn2);
            if (computer_pawn3.pawnID == ID)
                pawnIncreaseComputer(computer_pawn3);
            if (computer_pawn4.pawnID == ID)
                pawnIncreaseComputer(computer_pawn4);
        } else {
            return;
        }
    }

    public void movePawn() {

        if (pawnHumanStackIn.empty()) {
            System.out.println("Invalid move (there is no pawn on patch), please try again.");
            if (check_khal(dice.last_value)) {
                enterHumanPawn();
            } else {
                return;
            }
        } else {
            System.out.print("Chose pawn to move it : ");
            for (Pawn i : pawnHumanStackIn) {
                System.out.print(i.pawnID + ", ");
            }
            System.out.println();
            int choice2 = scanner.nextInt();

            if (inStack(pawnHumanStack, choice2)) {
                movePawn();
            } else {
                movePawnIncrease("H", choice2);
            }
        }
    }

    public void humanMove() {
        if (dice.last_value == 5 || dice.last_value == 1) {
            System.out.println("If you want to enter new pawn press: 1,");
            System.out.println("If you want to move pawn press: 2.");
            int choice = scanner.nextInt();

            if (choice == 1) {
                enterHumanPawn();
            }

            else if (choice == 2) {
                movePawn();
            } else {
                System.out.println("Invaliad move please choose {1,2}.");
                humanMove();
            }
        } else {
            movePawn();
        }
    }

    public boolean runFromEnemes(Pawn p1, Pawn p2) {
        if (p2.path.getPosition(p2.counter + 2) == p1.position)
            return true;
        else if (p2.path.getPosition(p2.counter + 3) == p1.position)
            return true;
        else if (p2.path.getPosition(p2.counter + 4) == p1.position)
            return true;
        else if (p2.path.getPosition(p2.counter + 6) == p1.position)
            return true;
        else if (p2.path.getPosition(p2.counter + 11) == p1.position)
            return true;
        else if (p2.path.getPosition(p2.counter + 12) == p1.position)
            return true;
        else if (p2.path.getPosition(p2.counter + 25) == p1.position)
            return true;
        else
            return false;
    }

    public void deepCopyPawn(Pawn p1, Pawn p2) {
        p1.counter = p2.counter;
        p1.inGame = p2.inGame;
        p1.playerType = p2.playerType;
        p1.pawnID = p2.pawnID;
        p1.position = p2.position;
        p1.path = p2.path;
    }

    public Pawn increaseMax(Pawn max) {
        for (Pawn p : pawnComputerStackIn) {
            if (max.pawnID == p.pawnID) {
                return p;
            }
        }
        System.out.println("Error increaseMax function");
        return max;
    }

    public void expectBestMove() {
        Pawn maxCount = new Pawn("C", 0);
        if (check_khal(dice.last_value)) {
            if (pawnComputerStackIn.empty()) {
                enterComputerPawn();
                return;
            } else {
                for (Pawn i : pawnComputerStackIn) {
                    for (Pawn j : pawnHumanStackIn) {
                        if ((i.path.getPosition(i.counter + steps(dice.last_value))) == j.position) {
                            pawnIncreaseComputer(i);
                            return;
                        }
                        if (runFromEnemes(i, j)) {
                            pawnIncreaseComputer(i);
                            return;
                        }
                    }
                    if (maxCount.counter <= i.counter) {
                        deepCopyPawn(maxCount, i);
                    }
                }
                if (!pawnComputerStack.empty()) {
                    enterComputerPawn();
                    return;
                }
                pawnIncreaseComputer(increaseMax(maxCount));
                maxCount.counter = 0;
                return;
            }
        } else {
            for (Pawn i : pawnComputerStackIn) {
                for (Pawn j : pawnHumanStackIn) {
                    if ((i.counter + steps(dice.last_value)) == j.counter) {
                        pawnIncreaseComputer(i);
                        return;
                    }
                    if (runFromEnemes(i, j)) {
                        pawnIncreaseComputer(i);
                        return;
                    }
                }
                if (maxCount.counter <= i.counter) {
                    deepCopyPawn(maxCount, i);
                }
            }
            pawnIncreaseComputer(increaseMax(maxCount));
            maxCount.counter = 0;
            return;
        }
    }

    public void computerMove() {
        expectBestMove();
        
    }

    // ************************************************************************************************
    // */
    public int evaluateState() {
        Pawn maxCount = new Pawn("C", 0);
        if (check_khal(dice.last_value)) {
            if (pawnComputerStackIn.empty()) {
                return 1000;
            } else {
                for (Pawn i : pawnComputerStackIn) {
                    for (Pawn j : pawnHumanStackIn) {
                        if ((i.path.getPosition(i.counter + steps(dice.last_value))) == j.position) {
                            return 100;
                        }
                        if (runFromEnemes(i, j)) {
                            return 100;
                        }
                    }
                }
                if (!pawnComputerStack.empty()) {
                    return 10;
                }
                return 1;
            }
        } else {
            for (Pawn i : pawnComputerStackIn) {
                for (Pawn j : pawnHumanStackIn) {
                    if ((i.counter + steps(dice.last_value)) == j.counter) {
                        return 100;
                    }
                    if (runFromEnemes(i, j)) {
                        return 100;
                    }
                }
            }
            return 1;
        }
    }

    public int generateMoves(String s) {
        Pawn maxCount = new Pawn("C", 0);
        if (check_khal(dice.last_value)) {
            if (pawnComputerStackIn.empty()) {
                enterComputerPawn();
                return 1;
            } else {
                for (Pawn i : pawnComputerStackIn) {
                    for (Pawn j : pawnHumanStackIn) {
                        if ((i.path.getPosition(i.counter + steps(dice.last_value))) == j.position) {
                            pawnIncreaseComputer(i);
                            return 2;
                        }
                        if (runFromEnemes(i, j)) {
                            pawnIncreaseComputer(i);
                            return 3;
                        }
                    }
                    if (maxCount.counter <= i.counter) {
                        deepCopyPawn(maxCount, i);
                    }
                }
                if (!pawnComputerStack.empty()) {
                    enterComputerPawn();
                    return 4;
                }
                pawnIncreaseComputer(increaseMax(maxCount));
                maxCount.counter = 0;
                return 5;
            }
        } else {
            for (Pawn i : pawnComputerStackIn) {
                for (Pawn j : pawnHumanStackIn) {
                    if ((i.counter + steps(dice.last_value)) == j.counter) {
                        pawnIncreaseComputer(i);
                        return 6;
                    }
                    if (runFromEnemes(i, j)) {
                        pawnIncreaseComputer(i);
                        return 7;
                    }
                }
                if (maxCount.counter <= i.counter) {
                    deepCopyPawn(maxCount, i);
                }
            }
            pawnIncreaseComputer(increaseMax(maxCount));
            maxCount.counter = 0;
            return 8;
        }
    }

    public void undoMove(int move) {
        if (move == 1) {
            if (pawnComputerStackIn.peek().position.checkSafeCell()) {
                patch.grid[pawnComputerStackIn.peek().position.i][pawnComputerStackIn.peek().position.j] = 'X';
            } else {
                patch.grid[pawnComputerStackIn.peek().position.i][pawnComputerStackIn.peek().position.j] = '*';
            }
            pawnComputerStack.push(pawnComputerStackIn.pop());
        }
    }

    public void updateGameState(int outcome) {
        Pawn maxCount = new Pawn("C", 0);
        if (check_khal(dice.last_value)) {
            if (pawnComputerStackIn.empty()) {
                enterComputerPawn();
                return;
            } else {
                for (Pawn i : pawnComputerStackIn) {
                    for (Pawn j : pawnHumanStackIn) {
                        if ((i.path.getPosition(i.counter + steps(dice.last_value))) == j.position) {
                            pawnIncreaseComputer(i);
                            return;
                        }
                        if (runFromEnemes(i, j)) {
                            pawnIncreaseComputer(i);
                            return;
                        }
                    }
                    if (maxCount.counter <= i.counter) {
                        deepCopyPawn(maxCount, i);
                    }
                }
                if (!pawnComputerStack.empty()) {
                    enterComputerPawn();
                    return;
                }
                pawnIncreaseComputer(increaseMax(maxCount));
                maxCount.counter = 0;
                return;
            }
        } else {
            for (Pawn i : pawnComputerStackIn) {
                for (Pawn j : pawnHumanStackIn) {
                    if ((i.counter + steps(dice.last_value)) == j.counter) {
                        pawnIncreaseComputer(i);
                        return;
                    }
                    if (runFromEnemes(i, j)) {
                        pawnIncreaseComputer(i);
                        return;
                    }
                }
                if (maxCount.counter <= i.counter) {
                    deepCopyPawn(maxCount, i);
                }
            }
            pawnIncreaseComputer(increaseMax(maxCount));
            maxCount.counter = 0;
            return;
        }
    }

    public void resetGameState(int outcome) {
        Pawn maxCount = new Pawn("C", 0);
        for (Pawn i : pawnComputerStackIn) {
            for (Pawn j : pawnHumanStackIn) {
                if ((i.counter + steps(dice.last_value)) == j.counter) {
                    pawnIncreaseComputer(i);
                    return;
                }
                if (runFromEnemes(i, j)) {
                    pawnIncreaseComputer(i);
                    return;
                }
            }
            if (maxCount.counter <= i.counter) {
                deepCopyPawn(maxCount, i);
            }
        }
    }

    public int expectiminimax(int depth, boolean isMaxPlayer) {
        if (depth == 0 || gameEnded()) {
            // Evaluate the current state and return a heuristic value
            return evaluateState();
        }

        if (isMaxPlayer) {
            int bestValue = Integer.MIN_VALUE;
            // Generate possible moves for the current player

            int move = generateMoves(currentPlayer);
            int value = expectiminimax(depth - 1, false);
            bestValue = Math.max(bestValue, value);
            undoMove(move);

            return bestValue;
        } else {
            int expectedValue = 0;
            // Calculate expected value for random events (e.g., rolling dice)

            int outcome = dice.roll();
            updateGameState(outcome);
            expectedValue += setBinomial(outcome) * expectiminimax(depth - 1, true);
            resetGameState(outcome);

            return expectedValue;
        }
    }

}
