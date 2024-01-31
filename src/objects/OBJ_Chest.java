package objects;

import java.awt.Rectangle;

import entity.Entity;
import principal.GamePanel;
import principal.UtiliyTool;

public class OBJ_Chest extends Entity {

    GamePanel gp;
    UtiliyTool uTool = new UtiliyTool();

    public OBJ_Chest(GamePanel gp){
        super(gp);
        name = "Chest";
        solidArea = new Rectangle();
        solidArea.x = 8;
		solidArea.y = 16;
        
        solidAreaDefultX = solidArea.x;
		solidAreaDefultY = solidArea.y;

		solidArea.width = 32;
		solidArea.height = 32;
        down1 = setup("/objects/chest", gp.tileSize, gp.tileSize); 
    }
}
