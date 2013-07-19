//unit test
package rpg;

import java.util.Random;
import java.util.Scanner;

public class UnitTest {

    public static final int cap = 99;
    public static int population = 0;

    public static void main(String[] args) {
        Player pc;

        Scanner kb = new Scanner(System.in);
        String playerName = "";

        System.out.println("Welcome to RPG"); //welcome message

        /*
         * assume new session
         */
        pc = addPlayer(kb); //name the player
        setUpPlayer(kb, pc);
        
        int size=25;
        World[][] worlds = generateWorld(size);
        
        /*RANDOM GENERATE WORLD*/
        
        pc.x = 0;
        pc.y = 0;

        System.out.printf("You have...\n%d class point in Power\n%d class point in Magic\n", pc.power, pc.magic);
        System.out.printf("%d HP and %d MP\n", pc.hp, pc.mp);
        System.out.println("=================================================================");
        System.out.println("=================================================================");
        System.out.println("======================Entering World=============================");
        System.out.println("=================================================================");
        System.out.printf("Welcome %s\n", pc.name);

        /*
         * GAME LOOP
         */
        for (int i = 0; i == i; i++) {
            int answer = -1;
            int x = pc.x;
            int y = pc.y;

            while (answer < 0 || answer > 4) { //which direction or enter location
                System.out.printf("Would you like to go:\n1) North\n2) South\n3) East\n4) West\nfrom your current location?\nOr type 0 to enter your current location\n");
                System.out.printf("Your current location is %s\n", worlds[x][y].name);
                answer = kb.nextInt();
            }
            //kb.nextLine();

            switch (answer) {
                case 0: //Enter current location
                    System.out.println("Welcome to " + worlds[x][y].name);
                    if (worlds[x][y].isTown) {//check if town or woods by boolean
                        //enterTown(worlds[x][y]);//enter a town
                    } else {
                        //enterWild(worlds[x][y]);//enter the wild
                    }
                    break;
                //else just move position in array
                case 1:
                    pc.x = (pc.x + 1) % worlds[0].length;
                    break;
                case 2:
                    pc.x = (pc.x - 1) % worlds[0].length;
                    break;
                case 3:
                    pc.y = (pc.y + 1) % worlds[0].length;
                    break;
                case 4:
                    pc.y = (pc.y - 1) % worlds[0].length;
                    break;
            }

            //TEST of position (x,y)
            System.out.println("Positioning Test");
            System.out.println(pc.x + " , " + pc.y);
            System.out.println(x + " , " + y);
            //END TEST of position
        }
    }

    public static void setUpPlayer(Scanner kb, Player pc) {
        String ans = "";

        while (!ans.equalsIgnoreCase("magic") && !ans.equalsIgnoreCase("power") && !ans.equalsIgnoreCase("custom")) {
            System.out.println("type magic, power, or custom: ");
            ans = kb.nextLine();
        }

        //each class gets 300 points total between hp/mp
        //2 points per level in class points
        if (ans.equalsIgnoreCase("magic")) {
            pc.hp = 125;
            pc.mp = 175;
            pc.magic = 2;
            pc.power = 0;
        } else if (ans.equalsIgnoreCase("power")) {
            pc.hp = 225;
            pc.mp = 75;
            pc.magic = 0;
            pc.power = 2;
        } else if (ans.equalsIgnoreCase("custom")) {
            int a = 0;

            while (a < 1 || a > 300) {
                System.out.println("You have 300 points to spend on health and mana");
                System.out.println("How much would you like to spend on health?");
                System.out.println("The rest will go towards your mana");
                System.out.println("You must have atleast 1 health");
                a = kb.nextInt();
            }
            pc.hp = a;
            pc.mp = 300 - a;
            //kb.nextLine(); //might be needed
            a = -1;
            while (a != 1 && a != 2 && a != 3) {
                System.out.println("You have 2 class points (power/magic)");
                System.out.printf("Would you like to\n1) Add 2 points to power\n2) Add 2 points to magic\n3) Add 1 point to each\n");
                a = kb.nextInt();
            }
            switch (a) {
                case 1:
                    pc.power = 2;
                    pc.magic = 0;
                    break;
                case 2:
                    pc.magic = 2;
                    pc.power = 0;
                    break;
                case 3:
                    pc.magic = 1;
                    pc.power = 1;
                    break;
            }
            pc.adef = 5;
            pc.mdef = 5;
            //TODO: add a restart option
            //
        } else {
            System.out.println("Something went wrong here");
        }
    }

    /*
     * namePlayer(Scanner): names the player first time return String
     */
    public static Player addPlayer(Scanner kb) {
        String name = "";

        while (name.length() < 1) {
            System.out.println("What is your player's  name?: ");
            name = kb.nextLine();
        }
        population++;

        return new Player(population, name);
    }

    private static World[][] generateWorld(int size) {
        World[][] w = new World[size][size];
        Random r = new Random();
        int rLocationX, rLocationY, rLand;
        String[] landArray = {"moutain","sea","plain"};
        int totalCount = 0;
        
        for(int i = 0;i < 3;) { //set key locations for random different land lays
            rLocationX = r.nextInt(size);
            rLocationY = r.nextInt(size);
            if(rLocationX != 0 && rLocationY !=0) {
                rLand = r.nextInt(landArray.length);
                totalCount++;
                w[rLocationX][rLocationY] = new World(totalCount,landArray[i],false);
                i++;
            }
        }
        
        //build out from there

        w[0][0] = new World(0,"Home");
        
        return w;
    }

    public void enterWild(World w, Player pc) {
        int ans = 0;
        Scanner kb = new Scanner(System.in);

        while (ans != 1 || ans != 2) {
            System.out.printf("Would you like to \n1) Fight\n2) Move forward\n");
            ans = kb.nextInt();
        }

        if (ans == 2) {
            //go back to last method
            return;
        } //else if ans == 1

        //fight loop
        String answer = "y";
        int i = 0;
        Random r = new Random();
        int next;

        for (; answer.equalsIgnoreCase("y"); i++) {
            //pick a random creep from the list of enemies in the current area
            next = r.nextInt(w.creeps.length);
            Player creep = w.creeps[next];

            //set battle variables
            int ehp = creep.hp, emp = creep.mp, ep = creep.power, em = creep.magic;
            int php = pc.hp, pmp = pc.mp, pp = pc.power, pm = pc.magic;

            //battle loop
            boolean isPlayerTurn = true;
            while (ehp > 0 && php > 0) {
                if (isPlayerTurn) {
                    int a = 0;

                    while (a != 1 || a != 2 || a != 3) {
                        System.out.printf("It is your turn\n1) Attack\n2) Spell\n3) Item\n");
                        a = kb.nextInt();
                    }

                    switch (a) {
                        case 1://attack

                            break;
                        case 2:
                            //pick the spell
                            if (pc.spells == null || pc.spells.length == 0) {
                                System.out.printf("No spells!\n");
                            } else {
                                int j = 0;
                                int spl = -1;//spell
                                //user pick
                                for (; j < pc.spells.length; j++) {
                                    System.out.printf("%d) %s\n", j, pc.spells[j].name);
                                }
                                while (spl < 0 || spl > pc.spells.length - 1) {
                                    spl = kb.nextInt();
                                }
                                //cast spell
                            }
                            break;
                        case 3://Item
                            break;
                    }

                    isPlayerTurn = false;
                } else { //enemies turn


                    isPlayerTurn = true;
                }
            }

            //somebody died
            //if you are alive and enemy is dead
            if (php > 0) {
                int exp = 500;
                exp = exp + (creep.level - pc.level) * 2;
                //add any extra exp modifications here

                System.out.println("Player defeated " + creep.name);
                System.out.printf("You earned %d experience points\n", exp);
                pc.exp += exp;

                //check for level up
                /*if ((pc.exp - totalLevelExp(pc.level)) >= 0) {
                    pc.level++;
                    System.out.println("Player leveled up to level " + pc.level);
                    levelUp(pc);
                }*/
            } else {
                System.out.println("You have died.");
            }
            System.out.println("Continue?(y/n)");
            answer = kb.nextLine();
        }
    }

    public void enterTown(World w) {
        Scanner kb = new Scanner(System.in);
        int i = 0;
        int ans = -1;

        System.out.printf("Talk to: \n");
        for (; i < w.npcs.length; i++) {
            System.out.printf("%d) %s\n", i, w.npcs[i]);

        }
        System.out.printf("%d) Exit\n", i + 1);

        while (ans < 0 || ans > i + 1) {
            System.out.println("type in the corresponding number");
            ans = kb.nextInt();
        }
        if (ans == i + 1) {
            return;
        }

        Player npc = w.npcs[i];

        //TODO
    }
}