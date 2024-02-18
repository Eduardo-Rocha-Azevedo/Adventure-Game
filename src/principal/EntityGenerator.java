package principal;

import entity.Entity;
import objects.OBJ_Axe;
import objects.OBJ_Chest;
import objects.OBJ_Coin_gold;
import objects.OBJ_CosmoCrystal;
import objects.OBJ_Door;
import objects.OBJ_Door_Iron;
import objects.OBJ_Fireball;
import objects.OBJ_Key;
import objects.OBJ_Lantern;
import objects.OBJ_Pickaxe;
import objects.OBJ_Potion_Blue;
import objects.OBJ_Potion_Red;
import objects.OBJ_Shield_Blue;
import objects.OBJ_Shield_Wood;
import objects.OBJ_Sword_Iron;
import objects.OBJ_Sword_Normal;
import objects.OBJ_Tent;
import tile_interactive.IT_MetalPlate;

public class EntityGenerator {
    GamePanel gp;

    public EntityGenerator(GamePanel gp){
        this.gp = gp;
    }
    public Entity getObject(String itemName){
        Entity obj  = null;

        switch(itemName){
            case OBJ_Axe.objName: obj = new OBJ_Axe(gp); break;
            case OBJ_Chest.objName: obj = new OBJ_Chest(gp); break;
            case OBJ_Key.objName: obj = new OBJ_Key(gp); break;
            case OBJ_Potion_Red.objName: obj = new OBJ_Potion_Red(gp); break;
            case OBJ_Potion_Blue.objName: obj = new OBJ_Potion_Blue(gp); break;
            case OBJ_Lantern.objName: obj = new OBJ_Lantern(gp); break;
            case OBJ_Shield_Blue.objName: obj = new OBJ_Shield_Blue(gp); break;
            case OBJ_Shield_Wood.objName: obj = new OBJ_Shield_Wood(gp); break;
            case OBJ_Sword_Iron.objName: obj = new OBJ_Sword_Iron(gp); break;
            case OBJ_Sword_Normal.objName: obj = new OBJ_Sword_Normal(gp); break;
            case OBJ_Tent.objName: obj = new OBJ_Tent(gp); break;
            case OBJ_Door.objName: obj = new OBJ_Door(gp); break;
            case OBJ_Coin_gold.objName: obj = new OBJ_Coin_gold(gp); break;
            case OBJ_Fireball.objName: obj = new OBJ_Fireball(gp); break;
            case OBJ_CosmoCrystal.objName: obj = new OBJ_CosmoCrystal(gp); break;
            case OBJ_Pickaxe.objName: obj = new OBJ_Pickaxe(gp); break;
            case OBJ_Door_Iron.objName: obj = new OBJ_Door_Iron(gp); break;
            //case IT_MetalPlate.objName: obj = new IT_MetalPlate(gp, 0, 0); break;
        }
        return obj;
    }
}
