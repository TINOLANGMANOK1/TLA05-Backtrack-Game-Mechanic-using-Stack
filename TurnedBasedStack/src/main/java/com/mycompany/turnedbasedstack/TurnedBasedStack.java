/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.turnedbasedstack;

/**
 *
 * @author Students Account
 */
import java.util.*;

public class TurnedBasedStack {

    public static void main(String[] args) {

        Random random = new Random();
        Stack<Integer> lastHp = new Stack<>();
        Scanner jm = new Scanner(System.in);

        int playerHp = 100;
        int botHp = 100;
        int playerMaxDmg = 10;
        int playerMinDmg = 5;
        int botMaxDmg = 5;
        int botMinDmg = 1;
        boolean monsterStunned = false;
        
        System.out.println("YOU ENCOUNTERED AN ENEMY!");

        while (true) {
            System.out.println("Player Hp: " + playerHp);
            System.out.println("Monster Hp: " + botHp);
            System.out.println();

            if (playerHp <= 0 && botHp <= 0) {
                System.out.println("It's a draw!");
                break;
            } else if (playerHp <= 0) {
                System.out.println("You lost! The monster wins!");
                break;
            } else if (botHp <= 0) {
                System.out.println("Congratulations! You defeated the monster!");
                break;
            }
            // PLayer's turn
            System.out.println("PLAYER'S TURN!");
            System.out.println("What would you like to do?");
            System.out.println(" 1. Normal Attack ");
            System.out.println(" 2. Stun  Attack ");
            System.out.println(" 3. Skip Turn ");
            System.out.print("Choose (or type exit): ");

            String input = jm.next();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("You exited the game.");
                break;
            }

            if (input.equals("1")) {
                int playerDmg = playerMinDmg + random.nextInt(playerMaxDmg - playerMinDmg + 1);
                System.out.println("You dealt " + playerDmg + " damage to the enemy");
                lastHp.push(botHp);
                botHp = botHp - playerDmg;
                if (botHp < 0) botHp = 0;
                System.out.println("Monster remaining hp: " + botHp);
                System.out.println();
            } else if (input.equals("2")) {
                
                int stunChance = random.nextInt(4); 
                if (stunChance == 0) {
                    monsterStunned = true;
                    System.out.println("You used stun attack! The monster is stunned and will skip its next turn.");
                    System.out.println();
                } else {
                    System.out.println("Stun failed! The monster is not stunned.");
                    System.out.println("");
                }
            } else if (input.equals("3")) {
                System.out.println("You skipped your turn! ");
                System.out.println();
            } else {
                System.out.println("Invalid input! Please enter 1, 2, 3 or 'exit'.");
                System.out.println();
                continue;
            }

            // Monster's turn
            if (botHp > 0) { 
                if (monsterStunned) {
                    System.out.println("Monster is stunned and cannot attack this turn.");
                    System.out.println();
                    monsterStunned = false;
                } else {
                    int botDmg = botMinDmg + random.nextInt(botMaxDmg - botMinDmg + 1);
                    System.out.println("MONSTER'S TURN!");
                    System.out.println("The Monster attacks and dealt " + botDmg + " damage to you");
                    playerHp = playerHp - botDmg;
                    if (playerHp < 0) playerHp = 0;
                    System.out.println("Your remaining hp: " + playerHp);
                    System.out.println();

                    int passiveRoll = random.nextInt(4);
                    if (!lastHp.isEmpty() && passiveRoll == 1) {
                        int prevHp = lastHp.peek();
                        System.out.println("Monster activates passive: 'Regeneration'!");
                        System.out.println("Regeneration: Monster restores its HP to " + prevHp);
                        botHp = prevHp;
                        System.out.println();
                    }
                }
            }
        }
    }
}
  
 