package objects;

import entity.Entity;
import principal.GamePanel;


public class OBJ_Door extends Entity {

    public OBJ_Door(GamePanel gp){
        super(gp);
        name = "Door";
        down1 = setup("/objects/door", gp.tileSize, gp.tileSize);
        collision = true;
        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefultX = solidArea.x;
        solidAreaDefultY = solidArea.y;
    }
}
