package objects;

import entity.Entity;
import principal.GamePanel;

public class OBJ_Axe extends Entity {
    public OBJ_Axe(GamePanel gp){
        super(gp);
        type = type_axe;
        name = "Machado de lenhador";
        down1 = setup("/objects/axe", gp.tileSize, gp.tileSize);
      
        attackValue = 2;
        attackArea.width = 30;
        attackArea.height = 30;
        description  ="["+ name+ "]\nPode cortar algumas\nárvores.";  
    }
}
