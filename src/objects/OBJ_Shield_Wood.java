package objects;

import entity.Entity;
import principal.GamePanel;

public class OBJ_Shield_Wood extends Entity{
    
    public OBJ_Shield_Wood(GamePanel gp){
        super(gp);
        type = type_shield;
        name = "Escudo de Madeira";
        down1 = setup("/objects/shield_wood", gp.tileSize, gp.tileSize);
        defenseValue = 1;
        description  ="["+ name+ "]\nFeito em madeira.";
    }
}
