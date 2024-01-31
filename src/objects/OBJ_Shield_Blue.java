package objects;

import entity.Entity;
import principal.GamePanel;

public class OBJ_Shield_Blue extends Entity{
   public OBJ_Shield_Blue(GamePanel gp){
    super(gp);
    type = type_shield;
    name = "Escudo de Ferro";
    down1 = setup("/objects/shield_blue", gp.tileSize, gp.tileSize);
    defenseValue = 1;
    description  ="["+ name+ "]\nFeito de ferro.";
    price = 250;
   }
}
