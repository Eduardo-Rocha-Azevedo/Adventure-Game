package objects;

import entity.Entity;
import principal.GamePanel;

public class OBJ_Pickaxe extends Entity{
    public static final String objName = "pickaxe";
    public OBJ_Pickaxe(GamePanel gp){
        super(gp);
        this.gp = gp;
        type = type_pickaxe;
        name = objName;
        down1 = setup("/objects/pickaxe", gp.tileSize, gp.tileSize);
        attackArea.width = 30;
        attackArea.height = 30;
        attackValue = 2;
        description = "[Picareta]\nUma picareta de ferro"; 
        knockBackPower = 1;
        motion1_duration = 10;
        motion2_duration = 20;
        
    }
}
