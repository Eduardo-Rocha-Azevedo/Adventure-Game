package objects;

import entity.Entity;
import principal.GamePanel;
import principal.UtiliyTool;

public class OBJ_Chest extends Entity {

    GamePanel gp;
    UtiliyTool uTool = new UtiliyTool();

    public OBJ_Chest(GamePanel gp){
        super(gp);
        name = "Chest";
        down1 = setup("/objects/chest");    
    }
}
