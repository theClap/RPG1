/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg;

/**
 *
 * @author ADMIN
 */


public class World {

  int id;  
  String name;
  Player[] creeps;
  Player[] npcs;
  Item[] treasures;
  char weather;
  boolean isTown;

  public World(int id) {
    this.id = id;
  }
  public World(int id, String name) {
    this.id = id;
    this.name = name;    
  }
  public World(int id, String name,boolean isTown) {
    this.id = id;
    this.name = name;    
	this.isTown = isTown;
  }
}