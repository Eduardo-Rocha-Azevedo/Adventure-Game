package principal;

import entity.NPC_Old_man;
import objects.OBJ_Door;
import principal.monster.MON_GreenSlime;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){
        //DOOR ===================================
       
    }

    public void setNPC(){
        gp.npc[0] = new NPC_Old_man(gp);
        gp.npc[0].worldX = gp.tileSize*21;
        gp.npc[0].worldY = gp.tileSize*21; 

    }

    public void setMonster(){
        //Slime ===================================
        gp.monster[0] = new MON_GreenSlime(gp);
        gp.monster[0].worldX = gp.tileSize*23;
        gp.monster[0].worldY = gp.tileSize*36;

        gp.monster[1] = new MON_GreenSlime(gp);
        gp.monster[1].worldX = gp.tileSize*23;
        gp.monster[1].worldY = gp.tileSize*38;

        gp.monster[2] = new MON_GreenSlime(gp);
        gp.monster[2].worldX = gp.tileSize*23;
        gp.monster[2].worldY = gp.tileSize*40;
    }
}
