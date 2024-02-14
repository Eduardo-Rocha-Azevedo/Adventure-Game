package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import entity.Entity;
import objects.OBJ_Axe;
import objects.OBJ_Chest;
import objects.OBJ_Door;
import objects.OBJ_Key;
import objects.OBJ_Lantern;
import objects.OBJ_Potion_Blue;
import objects.OBJ_Potion_Red;
import objects.OBJ_Shield_Blue;
import objects.OBJ_Shield_Wood;
import objects.OBJ_Sword_Iron;
import objects.OBJ_Sword_Normal;
import objects.OBJ_Tent;
import principal.GamePanel;

public class SaveLoad {
    GamePanel gp;
    public SaveLoad(GamePanel gp){
        this.gp = gp;
    }

    public Entity getObject(String itemName){
        Entity obj  = null;

        switch(itemName){
            case "Machado": obj = new OBJ_Axe(gp); break;
            case "Chest": obj = new OBJ_Chest(gp); break;
            case "Chave": obj = new OBJ_Key(gp); break;
            case "Poção Vermelha": obj = new OBJ_Potion_Red(gp); break;
            case "Poção Azul": obj = new OBJ_Potion_Blue(gp); break;
            case "Lanterna": obj = new OBJ_Lantern(gp); break;
            case "Escudo de Ferro": obj = new OBJ_Shield_Blue(gp); break;
            case "Escudo de Madeira": obj = new OBJ_Shield_Wood(gp); break;
            case "Espada de Ferro": obj = new OBJ_Sword_Iron(gp); break;
            case "Espada normal": obj = new OBJ_Sword_Normal(gp); break;
            case "Tenda": obj = new OBJ_Tent(gp); break;
            case "Door": obj = new OBJ_Door(gp); break;
        }
        return obj;
    }
    public void save(){
        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));
            DataStorage ds = new DataStorage();
            //PLAYER STATUS
            ds.level = gp.player.level;
            ds.nextLevel = gp.player.nextLevelExp;
            ds.maxLife = gp.player.maxLife;
            ds.life = gp.player.life;
            ds.maxCosmo = gp.player.maxCosmo;
            ds.cosmo = gp.player.cosmo;
            ds.strength = gp.player.strength;
            ds.dexterity = gp.player.dexterity;
            ds.exp = gp.player.exp;
            ds.coin = gp.player.coin;
            //PLAYER INVENTORY
            for(int i = 0; i < gp.player.inventory.size(); i++){
                ds.itemNames.add(gp.player.inventory.get(i).name);
                ds.itemAmouts.add(gp.player.inventory.get(i).amout);
            }
            //Write the DataStorage object
            oos.writeObject(ds);
        }
        catch(Exception e){
           System.out.println("Error saving game");
        }
    }

    public void load(){
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));

            //Read the DataStorage object
            DataStorage ds = (DataStorage)ois.readObject();
            //PLAYER STATUS
            gp.player.level = ds.level;
            gp.player.nextLevelExp = ds.nextLevel;
            gp.player.maxLife = ds.maxLife;
            gp.player.life = ds.life;
            gp.player.maxCosmo = ds.maxCosmo;
            gp.player.cosmo = ds.cosmo;
            gp.player.strength = ds.strength;
            gp.player.dexterity = ds.dexterity;
            gp.player.exp = ds.exp;
            gp.player.coin = ds.coin;


            //PLAYER INVENTORY
            gp.player.inventory.clear();
            for(int i = 0; i < ds.itemNames.size(); i++){
                gp.player.inventory.add(getObject(ds.itemNames.get(i)));
                gp.player.inventory.get(i).amout = ds.itemAmouts.get(i);
            }
            
        }
        catch(Exception e){
            System.out.println("Error loading game");
        }
    }
}
