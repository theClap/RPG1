/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg;

/**
 *
 * @author ADMIN
 */
public class Player {

    int id, hp, mp, bp, power, magic, level, adef, mdef;
    int x, y;
    String name;
    int battles, exp;//total experience and number of battles
    Item[] carry;//items you are holding
    Spell[] spells;
    int money;
    //add defenses

    public Player(int id) {
        this.id = id;
        level = exp = money = 0;
    }

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
        level = exp = money = 0;
    }

    public Player(int id, int hp, int mp) {
        this.id = id;
        this.hp = hp;
        this.mp = mp;
        bp = magic = power = level = exp = money = 0;
    }

    public Player(int id, int hp, int mp, int power, int magic) {
        this.id = id;
        this.hp = hp;
        this.mp = mp;
        this.power = power;
        this.magic = magic;
        bp = level = exp = money = 0;
    }

    public Player(int id, int level) {
        this.id = id;
        this.level = level;
        hp = mp = bp = power = magic = exp = money = 0;
    }
}
