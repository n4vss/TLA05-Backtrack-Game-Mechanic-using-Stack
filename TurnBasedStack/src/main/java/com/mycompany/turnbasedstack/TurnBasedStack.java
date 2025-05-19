package com.mycompany.turnbasedstack;

import java.util.*;

public class TurnBasedStack {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Stack<Integer> lastHP = new Stack<>();
        Random rand = new Random();

        int gameTimer = 2;
        int playerHP = 100;
        int botHP = 100;
        int playerMP = 15;
        int maxMP = 20;
        int playerDmg = 10;
        int stunDMG = 5;
        int botDmg = 5;
        boolean botStunned = false;

        if (isOddorEven(gameTimer)) {
            System.out.println("------------------------------");
            System.out.println("You encountered an enemy!");
            System.out.println("Player HP: " + playerHP);
            System.out.println("Player MP: " + playerMP);
            System.out.println("Bot HP: " + botHP);
            System.out.println("------------------------------");

            while (playerHP > 0 && botHP > 0) {
                System.out.println("------------------------------");
                System.out.println("What would you like to do?");
                System.out.println(">> Attack");
                System.out.println(">> Mana");
                System.out.println(">> Use Stun (Costs 5 MP)");
                System.out.println(">> Use Heal (Costs 3 MP)");
                System.out.println(">> Skip Turn");
                System.out.println("------------------------------");

                String in = sc.nextLine();

                if (in.equalsIgnoreCase("attack")) {
                    lastHP.push(botHP);
                    System.out.println("------------------------------");
                    System.out.println("You dealt " + playerDmg + " damage to the enemy.");
                    botHP -= playerDmg;
                    if (rand.nextInt(100) < 50) {
                        if (!lastHP.isEmpty()) {
                            botHP = lastHP.pop();
                            System.out.println("Bot's passive triggered! It restores its previous HP.");
                        }
                    }
                    System.out.println("Player HP: " + playerHP);
                    System.out.println("Player MP: " + playerMP);
                    System.out.println("Bot HP: " + botHP);
                    System.out.println("------------------------------");
                } else if (in.equalsIgnoreCase("mana")) {
                    int manaGain = 3;
                    playerMP = Math.min(playerMP + manaGain, maxMP);
                    System.out.println("------------------------------");
                    System.out.println("You regenerated " + manaGain + " MP.");
                    System.out.println("Player HP: " + playerHP);
                    System.out.println("Player MP: " + playerMP);
                    System.out.println("Bot HP: " + botHP);
                    System.out.println("------------------------------");

                } else if (in.equalsIgnoreCase("stun")) {
                    if (playerMP >= 5) {
                        botStunned = true;
                        System.out.println("------------------------------");
                        System.out.println("You stunned the bot! It will skip its next turn.");
                        botHP -= stunDMG;
                        lastHP.push(botHP);
                        playerMP -= 5;
                        System.out.println("Player HP: " + playerHP);
                        System.out.println("Player MP: " + playerMP);
                        System.out.println("Bot HP: " + botHP);
                        System.out.println("------------------------------");
                    } else {
                        System.out.println("------------------------------");
                        System.out.println("Not enough MP to use stun.");
                        System.out.println("Player HP: " + playerHP);
                        System.out.println("Player MP: " + playerMP);
                        System.out.println("Bot HP: " + botHP);
                        System.out.println("------------------------------");
                    }
                } else if (in.equalsIgnoreCase("heal")) {
                    if (playerMP >= 3) {
                        int healAmount = 15;
                        playerHP += healAmount;
                        playerMP -= 3;
                        System.out.println("------------------------------");
                        System.out.println("You used heal and recovered " + healAmount + " HP.");
                        System.out.println("Player HP: " + playerHP);
                        System.out.println("Player MP: " + playerMP);
                        System.out.println("Bot HP: " + botHP);
                        System.out.println("------------------------------");
                    } else {
                        System.out.println("Not enough MP to heal.");
                        System.out.println("Player HP: " + playerHP);
                        System.out.println("Player MP: " + playerMP);
                        System.out.println("Bot HP: " + botHP);
                        System.out.println("------------------------------");
                    }
                } else if (in.equalsIgnoreCase("skip")) {
                    System.out.println("------------------------------");
                    System.out.println("You skipped your turn.");
                    System.out.println("The bot attacks and deals " + botDmg + " damage!");
                    playerHP -= botDmg;
                    System.out.println("Player HP: " + playerHP);
                    System.out.println("Player MP: " + playerMP);
                    System.out.println("Bot HP: " + botHP);
                    System.out.println("------------------------------");
                }

                if (botHP <= 0) {
                    System.out.println("------------------------------");
                    System.out.println("You defeated the enemy!");
                    System.out.println("Player HP: " + playerHP);
                    System.out.println("Player MP: " + playerMP);
                    System.out.println("Bot HP: " + botHP);
                    System.out.println("------------------------------");
                    break;
                }

                if (!botStunned) {
                    int botAction = rand.nextInt(2);
                    if (botAction == 0) {
                        System.out.println("------------------------------");
                        System.out.println("The bot attacks and deals " + botDmg + " damage!");
                        playerHP -= botDmg;
                        System.out.println("Player HP: " + playerHP);
                        System.out.println("Player MP: " + playerMP);
                        System.out.println("Bot HP: " + botHP);
                        System.out.println("------------------------------");
                    }
                } else {
                    botStunned = false;
                    System.out.println("------------------------------");
                    System.out.println("The bot is stunned and skips its turn.");
                    System.out.println("Player HP: " + playerHP);
                    System.out.println("Player MP: " + playerMP);
                    System.out.println("Bot HP: " + botHP);
                    System.out.println("------------------------------");
                }

                if (playerHP <= 0) {
                    System.out.println("------------------------------");
                    System.out.println("You have been defeated...");
                    System.out.println("Player HP: " + playerHP);
                    System.out.println("Bot HP: " + botHP);
                    System.out.println("------------------------------");
                    break;
                }
            }
        }
    }

    static boolean isOddorEven(int i) {
        boolean oddOrEven;
        if (i % 2 == 0) {
            return oddOrEven = true;
        } else {
            return oddOrEven = false;
        }
    }
}
