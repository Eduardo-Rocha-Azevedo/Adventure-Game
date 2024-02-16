package objects;

import entity.Entity;
import principal.GamePanel;

public class OBJ_Chest extends Entity {

    GamePanel gp;
    

    public OBJ_Chest(GamePanel gp) {
        super(gp);
        this.gp = gp;
        

        type = type_obstacle;
        name = "Chest";
        image = setup("/objects/chest", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/chest_opened", gp.tileSize, gp.tileSize);
        down1 = image;
        collision = true;

        solidArea.x = 16;
        solidArea.y = 16;
        solidArea.width = 40;
        solidArea.height = 40;
        solidAreaDefultX  = solidArea.x;
        solidAreaDefultY  = solidArea.y;
    }

    public void setLoot(Entity loot){
        this.loot = loot;
        setDialogue();
    }

    public void setDialogue(){
        dialogues[0][0] = "Você encontrou: " + loot.name + "!"+"\n...Mas você não tem espaço no inventário.";
        dialogues[1][0] = "\nVocê pegou: " + loot.name + "!";
        dialogues[2][0] = "O baú está vazio.";
    }

    public void interact(){
        
        if(opened == false){
            gp.playSE(3);

            if(gp.player.canObtainItem(loot) == false){
                startDialogue(this, 0);
            }
            else {
                startDialogue(this, 1);
                //gp.player.inventory.add(loot);
                down1 = image2;
                opened = true;
            }  
        }
        else{
            startDialogue(this, 2);
        }
    }
}
