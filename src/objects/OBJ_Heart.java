package objects;

import entity.Entity;
import principal.GamePanel;

public class OBJ_Heart extends Entity{
    public static final String objName = "Heart";

    public OBJ_Heart(GamePanel gp){
        super(gp);
        name = objName;
        type = type_pickOnly;
        value = 2;
        down1 = setup("/objects/heart_full", gp.tileSize, gp.tileSize);
        image = setup("/objects/heart_full", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/heart_half", gp.tileSize, gp.tileSize);
        image3 = setup("/objects/heart_blank", gp.tileSize, gp.tileSize);
       
    }

    public boolean use(Entity e){
        gp.playSE(1);
        gp.ui.addMessage("life +"+ value);
        e.life += value;
        return true;
    }
}
