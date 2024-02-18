package objects;

import entity.Entity;
import principal.GamePanel;

public class OBJ_Door_Iron extends Entity{
     public static final String objName = "Door_Iron";
    GamePanel gp;
    public OBJ_Door_Iron(GamePanel gp){
        super(gp);
        this.gp = gp;
        name = objName;
        down1 = setup("/objects/door_iron", gp.tileSize, gp.tileSize);
        collision = true;
        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefultX = solidArea.x;
        solidAreaDefultY = solidArea.y;
        type = type_obstacle;
        setDialogue();
    }
    public void setDialogue(){
        dialogues[0][0] = "Não vai abrir...";
    }

    public void interact(){
       startDialogue(this, 0);
    }
}
