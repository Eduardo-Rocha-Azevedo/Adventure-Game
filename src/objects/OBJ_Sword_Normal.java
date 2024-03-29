package objects;

import entity.Entity;
import principal.GamePanel;

public class OBJ_Sword_Normal extends Entity{
    public static final String objName = "Espada normal";
    GamePanel gp;
    public OBJ_Sword_Normal(GamePanel gp){
        super(gp);
        type = type_sword_normal;
        name = objName;
        down1 = setup("/objects/sword_normal", gp.tileSize, gp.tileSize);
        attackValue = 2;
        attackArea.width = 36;
        attackArea.height = 36;
        description  ="["+ name+ "]\nUma velha espada."; 
        price = 50; 
        knockBackPower = 6;
        motion1_duration = 5;
        motion2_duration = 25;
    }

}
