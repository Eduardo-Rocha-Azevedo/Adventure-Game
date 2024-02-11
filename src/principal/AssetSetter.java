package principal;

import entity.NPC_Merchant;
import entity.NPC_Old_man;
import objects.OBJ_Chest;
import objects.OBJ_Door;
import objects.OBJ_Key;
import objects.OBJ_Lantern;
import objects.OBJ_Tent;
import principal.monster.MON_GreenSlime;
import tile_interactive.IT_DryTree;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){
        
        int i = 0;
        int mapNum = 0;
        
        //Door ===================================
        gp.obj[mapNum][i] = new OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*14;
        gp.obj[mapNum][i].worldY = gp.tileSize*28;
        i++;
        gp.obj[mapNum][i] = new OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*10;
        gp.obj[mapNum][i].worldY = gp.tileSize*28;
        i++;
        gp.obj[mapNum][i] = new OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*11;
        gp.obj[mapNum][i].worldY = gp.tileSize*18;
        i++;
    
        //Chest ===================================
        gp.obj[mapNum][i] = new OBJ_Chest(gp, new OBJ_Key(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*30;
        gp.obj[mapNum][i].worldY = gp.tileSize*29;
        i++;
        gp.obj[mapNum][i] = new OBJ_Chest(gp, new OBJ_Key(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*39;
        gp.obj[mapNum][i].worldY = gp.tileSize*45;
        i++;

        //tent
        gp.obj[mapNum][i] = new OBJ_Tent(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*21;
        gp.obj[mapNum][i].worldY = gp.tileSize*20;
        i++;
      
    }

    public void setNPC(){
        
        //MAP 0 (main)===================================
        int mapNum = 0;
        int i = 0;
        gp.npc[mapNum][i] = new NPC_Old_man(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*21;
        gp.npc[mapNum][i].worldY = gp.tileSize*21; 
        i++;

        //MAP 1 (shop)===================================
        mapNum = 1;
        i = 0;
        gp.npc[mapNum][i] = new NPC_Merchant(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*10;
        gp.npc[mapNum][i].worldY = gp.tileSize*34; 
        i++;

    }

    public void setMonster(){
        
        //Slime ===================================
        int i = 0;
        int mapNum = 0;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*23;
        gp.monster[mapNum][i].worldY = gp.tileSize*36;
        i++;

        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*23;
        gp.monster[mapNum][i].worldY = gp.tileSize*38;
        i++;

        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*23;
        gp.monster[mapNum][i].worldY = gp.tileSize*35;
        i++;

        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*34;
        gp.monster[mapNum][i].worldY = gp.tileSize*42;
        i++;

        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*38;
        gp.monster[mapNum][i].worldY = gp.tileSize*37;
        i++;

        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*37;
        gp.monster[mapNum][i].worldY = gp.tileSize*43;
        i++;
    }

    public void setInteractiveTile(){
        int i = 0;
        int mapNum = 0;
        //Dry Tree ===================================
        gp.iTile[mapNum][i] = new IT_DryTree(gp,27,12); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,28,12); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,29,12); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,30,12); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,31,12); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,25,27); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,26,27); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,27,27); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,27,28); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,27,29); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,27,30); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,27,31); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,28,31); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,29,31); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,30,31); i++;
        //loja
        gp.iTile[mapNum][i] = new IT_DryTree(gp,13,40); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,14,40); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,15,40); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,16,40); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,17,40); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,18,40); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,10,41); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,11,41); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,12,41); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,13,41); i++;
    }
}
