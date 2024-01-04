package objects;

import entity.Entity;
import principal.GamePanel;

public class OBJ_Sword_Normal extends Entity{
   
    GamePanel gp;
    public OBJ_Sword_Normal(GamePanel gp){
        super(gp);
        type = type_sword;
        name = "Normal Sword";
        down1 = setup("/objects/sword_normal", gp.tileSize, gp.tileSize);
        attackValue = 1;
        attackArea.width = 36;
        attackArea.height = 36;
        description  ="["+ name+ "]\n An old sword.";  
    }

}
