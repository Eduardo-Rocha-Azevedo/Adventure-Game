package objects;

import entity.Entity;
import principal.GamePanel;

public class OBJ_Shield_Wood extends Entity{
    public static final String objName = "Escudo de Madeira";
    public OBJ_Shield_Wood(GamePanel gp){
        super(gp);
        type = type_shield;
        name = objName;
        down1 = setup("/objects/shield_wood", gp.tileSize, gp.tileSize);
        defenseValue = 10;
        description  ="["+ name+ "]\nFeito em madeira.";
        price = 50;
    }
}
