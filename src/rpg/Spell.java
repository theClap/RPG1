/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg;

/**
 *
 * @author ADMIN
 */
public class Spell {

    int id, hp, mp, bp, power, magic;
    String name;

    public Spell(int id) {
        this.id = id;
        hp = mp = bp = power = magic = 0;
    }

    public Spell(int id, String name) {
        this.id = id;
        this.name = name;
        hp = mp = bp = power = magic = 0;
    }
}
