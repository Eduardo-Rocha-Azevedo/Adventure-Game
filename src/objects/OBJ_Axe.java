package objects;

import java.awt.Rectangle;

import entity.Entity;
import principal.GamePanel;

public class OBJ_Axe extends Entity {
    public OBJ_Axe(GamePanel gp){
        super(gp);
        type = type_axe;
        name = "Woodcutter's axe";
        down1 = setup("/objects/axe", gp.tileSize, gp.tileSize);
      
        attackValue = 2;
        attackArea.width = 30;
        attackArea.height = 30;
        description  ="["+ name+ "]\n An bit rusty but stll\n can cut some trees.";  
    }
}
