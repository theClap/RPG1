/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg;

/**
 *
 * @author ADMIN
 */
public class Item {

    int id, hp, mp, bp, power, magic;
    String name;

    public Item(int id) {
        this.id = id;
        hp = mp = bp = power = magic = 0;
    }

    public Item(int id, String name) {
        this.id = id;
        this.name = name;
        hp = mp = bp = power = magic = 0;
    }
}
