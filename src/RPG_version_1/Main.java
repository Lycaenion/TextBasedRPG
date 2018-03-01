package RPG_version_1;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //system objects

        Scanner in = new Scanner(System.in);
        Random rand = new Random();

        //Game variables
        String[] enemies = {"Tomte", "Troll", "Varg", "Järv", "Varulv", "Myling"};
        int maxEnemyHealth = 110;
        int enemyAttackDamage = 20;

        //Player variables
        int playerHealth = 120;
        int playerMana = 120;
        int playerAttackDamage = 30;
        int playerMagicDamage = 40;
        int healthPots = 3;
        int healthPotsHealAmount = 20;
        int healthPotsDropChance = 65; //how big chance for enemies to drop health pots in %
        int manaPots = 3;
        int manaPotsHealAmount = 15;
        int manaPotsDropChance = 50; //how big chance for enemies to drop mana pots in %
        int gold = 0;
        int goldDropChance = 100; //how big chance for enemies to drop gold in %

        boolean running = true;

        System.out.println("Welcome to Sweden. Be careful out there.");

        GAME:
        while (running) {
            System.out.println("-------------------------------------");

            int enemyHealth = rand.nextInt(maxEnemyHealth);
            String enemy = enemies[rand.nextInt(enemies.length)];
            System.out.println("\t# A " + enemy + " has appeared! #\n");

            while (enemyHealth > 0) {
                System.out.println("\tYour HP: " + playerHealth);
                System.out.println("\tYour mana: " + playerMana);
                System.out.println("\t" + enemy + "'s HP: " + enemyHealth);
                System.out.println("\n> What do you do?");
                System.out.println("\t1. Attack with sword");
                System.out.println("\t2. Attack with magic");
                System.out.println("\t3. Drink health potion");
                System.out.println("\t4. Drink mana potion");
                System.out.println("\t5. Run away");

                String input = in.nextLine();

                //attack with sword
                if (input.equals("1")) {
                    int damageDealt = rand.nextInt(playerAttackDamage);
                    int damageTaken = rand.nextInt(enemyAttackDamage);

                    enemyHealth -= damageDealt;
                    playerHealth -= damageTaken;

                    System.out.println("\t> You deal " + damageDealt + " damage to " + enemy + "!");
                    System.out.println("\t> " + enemy + " deals " + damageTaken + " damage to you!");

                    if (playerHealth < 1) {
                        System.out.println("\t> You are too weak! You can't go on!");
                        break;
                    }

                }
                //attack with magic
                else if (input.equals("2")) {
                    int magicDamageDealt = rand.nextInt(playerMagicDamage);
                    int damageTaken = rand.nextInt(enemyAttackDamage);

                    playerMana -= 5;
                    enemyHealth -= magicDamageDealt;
                    playerHealth -= damageTaken;

                    System.out.println("\t> You deal " + magicDamageDealt + " magical damage to " + enemy + " !");
                    System.out.println("\t> " + enemy + " deals " + damageTaken + " to you!");

                    if (playerHealth < 1) {
                        System.out.println("\t> you are too weak! You can't go on!");
                        break;
                    }
                }
                //use health pot
                else if (input.equals("3")) {
                    if (healthPots > 0) {
                        playerHealth += healthPotsHealAmount;
                        healthPots--;
                        System.out.println("\t> You drink a health potion. It heals you " + healthPotsHealAmount
                                + " HP" + "\n\t> You now have " + playerHealth + " HP"
                                + "\n\t> You have " + healthPots + " health potions left\n");
                    } else {
                        System.out.println("\t> You have not health potions left! Defeat enemies for a chance to get more potions.\n");
                    }
                }
                //use mana pot
                else if (input.equals("4")) {
                    if (manaPots > 0) {
                        playerMana += manaPotsHealAmount;
                        manaPots--;
                        System.out.println("\t> You drink a mana potion. It gives you " + manaPotsHealAmount
                                + " more mana." + "\n\t> You now have " + playerMana + " MP"
                                + "\n\t> You have " + manaPots + " mana potions left. \n");
                    } else {
                        System.out.println("\t> You have no mana potions left! Defeat enemies for a chance to get more potions.\n");
                    }
                }
                //run away
                else if (input.equals("5")) {
                    System.out.println("\t> You run away from " + enemy + "!");
                    continue GAME;
                }
                //invalid command
                else {
                    System.out.println("Invalid command! Please press 1, 2, 3 or 4 to act!");
                }

            }
            if (playerHealth < 1) {
                System.out.println("You limp away from Sweden.");
                System.out.println("You earned " + gold + " coin(s)");
                break;
            }

            System.out.println("-------------------------------------");
            System.out.println(" # " + enemy + " was defeated! # ");
            System.out.println(" # You have " + playerHealth + " HP left # ");

            //random drop for pots
            if (rand.nextInt(100) < healthPotsDropChance) {
                healthPots++;
                System.out.println(" # The " + enemy + " dropped a health potion! # ");
                System.out.println(" # You now have " + healthPots + " health potion(s)! #");
            }
            if (rand.nextInt(100) < manaPotsDropChance) {
                manaPots++;
                System.out.println(" # The " + enemy + " dropped a mana potion! # ");
                System.out.println(" # You now have " + manaPots + " mana potion(s)! #");
            }
            //random drop for gold
            if (rand.nextInt(100) < goldDropChance) {
                gold++;
                System.out.println(" # The " + enemy + " dropped a gold coin! # ");
                System.out.println(" # You now have " + gold + " gold coin(s)! #");
            }


            System.out.println("-------------------------------------");
            System.out.println("What would you like to do now?");
            System.out.println("1. Continue fighting");
            System.out.println("2. Leave Sweden");

            String input = in.nextLine();

            while (!input.equals("1") && !input.equals("2")) {
                System.out.println("Invalid command");
                input = in.nextLine();
            }
            if (input.equals("1")) {
                System.out.println("You continue your journey through Sweden.");
            } else if (input.equals("2")) {
                System.out.println("You decide to leave Sweden while you are victorious.");
                System.out.println("You earned " + gold + " gold coin(s)! Good job");
                break;
            }
        }
        System.out.println("########################");
        System.out.println("#  Thanks for Playing! #");
        System.out.println("########################");

    }
}

