package objects;

import entity.Entity;
import principal.GamePanel;

public class OBJ_Axe extends Entity {
    public OBJ_Axe(GamePanel gp){
        super(gp);
        type = type_axe;
        name = "Machado";
        down1 = setup("/objects/axe", gp.tileSize, gp.tileSize);
        attackValue = 1;
        attackArea.width = 36;
        attackArea.height = 36;
        description  ="["+ name+ "]\nPode cortar algumas\nárvores.";  
        price = 75;
        knockBackPower = 4;
        motion1_duration = 20;
        motion2_duration = 40;
        
    }
}
