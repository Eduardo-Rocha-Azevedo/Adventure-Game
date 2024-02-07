package objects;

import entity.Entity;
import principal.GamePanel;

public class OBJ_Chest extends Entity {

    GamePanel gp;
    Entity loot;
    boolean opened = false;

    public OBJ_Chest(GamePanel gp, Entity loot) {
        super(gp);
        this.gp = gp;
        this.loot = loot;

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

    public void interact(){
        gp.gameState = gp.dialogState;
        if(opened == false){
            gp.playSE(3);
            StringBuilder sb = new StringBuilder();
            sb.append("Você encontrou: " + loot.name + "!");

            if(gp.player.canObtainItem(loot) == false){
                sb.append("\n...Mas você não tem espaço no inventário.");
            }
            else {
                sb.append("\nVocê pegou: " + loot.name + "!");
                //gp.player.inventory.add(loot);
                down1 = image2;
                opened = true;
            }
            gp.ui.currentDialog = sb.toString();
        }
        else{
            gp.ui.currentDialog = "O baú está vazio.";
        }
    }
}
