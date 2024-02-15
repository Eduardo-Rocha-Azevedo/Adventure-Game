package data;

import java.io.Serializable;
import java.util.ArrayList;

public class DataStorage implements Serializable{
    //PLAYER STATUS
    int level;
    int maxLife;
    int maxCosmo;
    int cosmo;
    int life;
    int strength;
    int dexterity;
    int exp;
    int nextLevel;
    int coin;

    //PLAYER INVENTORY
    ArrayList<String> itemNames = new ArrayList<String>();
    ArrayList<Integer> itemAmouts = new ArrayList<Integer>();
    int currentWeaponSlot;
    int currentShieldSlot;

    // Objects on map
    String mapObjectNames[][];
    int mapObjectWorldX[][];
    int mapObjectWorldY[][];
    String mapObjectLootNames[][];
    boolean mapObjectOpened[][];
}
