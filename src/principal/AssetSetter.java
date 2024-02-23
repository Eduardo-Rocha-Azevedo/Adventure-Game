package principal;
import principal.GamePanel;
import data.Progress;
import entity.NPC_BigRock;
import entity.NPC_Merchant;
import entity.NPC_Old_man;
import objects.OBJ_BlueHeart;
import objects.OBJ_Chest;
import objects.OBJ_Door;
import objects.OBJ_Door_Iron;
import objects.OBJ_Key;
import objects.OBJ_Lantern;
import objects.OBJ_Pickaxe;
import objects.OBJ_Potion_Blue;
import objects.OBJ_Potion_Red;
import objects.OBJ_Sword_Iron;
import objects.OBJ_Tent;
import principal.monster.MOM_Bat;
import principal.monster.MOM_RedSlime;
import principal.monster.MOM_Skeleton;
import principal.monster.MON_GreenSlime;
import principal.monster.MON_Orc;
import tile_interactive.IT_DestructibleWall;
import tile_interactive.IT_DryTree;
import tile_interactive.IT_MetalPlate;

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
        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Key(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*30;
        gp.obj[mapNum][i].worldY = gp.tileSize*29;
        i++;
        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Key(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*39;
        gp.obj[mapNum][i].worldY = gp.tileSize*45;
        i++;

        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Potion_Blue(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*17;
        gp.obj[mapNum][i].worldY = gp.tileSize*20;
        i++;

        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Tent(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*16;
        gp.obj[mapNum][i].worldY = gp.tileSize*20;
        i++;


        //tent
        gp.obj[mapNum][i] = new OBJ_Tent(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*21;
        gp.obj[mapNum][i].worldY = gp.tileSize*20;
        i++;

        //DOUGEON===================================>
        mapNum = 2;
        i = 0;

        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Pickaxe(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*40;
        gp.obj[mapNum][i].worldY = gp.tileSize*41;
        i++;

        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Potion_Blue(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*13;
        gp.obj[mapNum][i].worldY = gp.tileSize*16;
        i++;

        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Potion_Red(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*41;
        gp.obj[mapNum][i].worldY = gp.tileSize*41;
        i++;

        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Potion_Blue(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*27;
        gp.obj[mapNum][i].worldY = gp.tileSize*18;
        i++;


        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Potion_Red(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*26;
        gp.obj[mapNum][i].worldY = gp.tileSize*33;
        i++;

        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Sword_Iron(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*15;
        gp.obj[mapNum][i].worldY = gp.tileSize*12;
        i++;

        gp.obj[mapNum][i] = new OBJ_Door_Iron(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*18;
        gp.obj[mapNum][i].worldY = gp.tileSize*23;
        i++;

        
        // DOUNGEON FINAL BATTLE===================================>
        mapNum = 3;
        i = 0;
       
        gp.obj[mapNum][i] = new OBJ_Door_Iron(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*25;
        gp.obj[mapNum][i].worldY = gp.tileSize*15;
        i++;  

        gp.obj[mapNum][i] = new OBJ_BlueHeart(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*25;
        gp.obj[mapNum][i].worldY = gp.tileSize*8;
        i++; 

        gp.obj[mapNum][i] = new OBJ_BlueHeart(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*25;
        gp.obj[mapNum][i].worldY = gp.tileSize*9;
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
        
        //DOUNGEON===================================>
        mapNum = 2;
        i = 0;
        //Big Rock
        gp.npc[mapNum][i] = new NPC_BigRock(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*20;
        gp.npc[mapNum][i].worldY = gp.tileSize*25;
        i++;
        
        gp.npc[mapNum][i] = new NPC_BigRock(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*8;
        gp.npc[mapNum][i].worldY = gp.tileSize*20;
        i++;
        
        gp.npc[mapNum][i] = new NPC_BigRock(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*23;
        gp.npc[mapNum][i].worldY = gp.tileSize*14;
        i++;

    }

    public void setMonster(){
        //MAP (0) INICIAL=========================>
        //Slime =======
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
        // ORC =================
        gp.monster[mapNum][i] = new MON_Orc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*12;
        gp.monster[mapNum][i].worldY = gp.tileSize*33;
        i++;
        //MAP (2) DANGEON ==================================>
        mapNum = 2;
        i = 0;
        gp.monster[mapNum][i] = new MOM_RedSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*36;
        gp.monster[mapNum][i].worldY = gp.tileSize*26;
        i++;

        gp.monster[mapNum][i] = new MOM_RedSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*36;
        gp.monster[mapNum][i].worldY = gp.tileSize*28;
        i++;

        gp.monster[mapNum][i] = new MOM_RedSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*38;
        gp.monster[mapNum][i].worldY = gp.tileSize*15;
        i++;

        gp.monster[mapNum][i] = new MOM_RedSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*38;
        gp.monster[mapNum][i].worldY = gp.tileSize*17;
        i++;
        //BAT =================
        gp.monster[mapNum][i] = new MOM_Bat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*34;
        gp.monster[mapNum][i].worldY = gp.tileSize*39;
        i++;

        gp.monster[mapNum][i] = new MOM_Bat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*36;
        gp.monster[mapNum][i].worldY = gp.tileSize*25;
        i++;

        gp.monster[mapNum][i] = new MOM_Bat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*39;
        gp.monster[mapNum][i].worldY = gp.tileSize*26;
        i++;

        gp.monster[mapNum][i] = new MOM_Bat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*28;
        gp.monster[mapNum][i].worldY = gp.tileSize*11;
        i++;

        gp.monster[mapNum][i] = new MOM_Bat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*10;
        gp.monster[mapNum][i].worldY = gp.tileSize*19;
        i++;
        
        //DAUGEON FINAL BATTLE===================================>
        mapNum = 3;
        i = 0;
       
            gp.monster[mapNum][i] = new MOM_Skeleton(gp);
            gp.monster[mapNum][i].worldX = gp.tileSize*23;
            gp.monster[mapNum][i].worldY = gp.tileSize*16;
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

        //DOUNGEON===================================>
        mapNum = 2;
        i = 0;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,18,30); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,17,31); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,17,32); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,17,34); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,18,34); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,10,25); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,10,24); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,38,18); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,38,19); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,38,20); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,38,21); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,18,13); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,18,14); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,30,28); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,32,28); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,40,36); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,40,33); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,22,25); i++;

        gp.iTile[mapNum][i] = new IT_MetalPlate(gp,20,22); i++;
        gp.iTile[mapNum][i] = new IT_MetalPlate(gp,8,17); i++;
        gp.iTile[mapNum][i] = new IT_MetalPlate(gp,39,31); i++;

        // DOUNGEON FINAL BATTLE===================================>
        mapNum = 3;
        i = 0;        
       
    }
}
