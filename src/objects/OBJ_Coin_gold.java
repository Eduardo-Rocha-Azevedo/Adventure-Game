package objects;

import entity.Entity;
import principal.GamePanel;

public class OBJ_Coin_gold extends Entity {
    public static final String objName = "Moeda de Ouro";
    public OBJ_Coin_gold(GamePanel gp){

        super(gp);
        type = type_pickOnly;
        name = objName;
        value = 1;
        down1 = setup("/objects/coin_gold", gp.tileSize, gp.tileSize);

    }
   
    public boolean use(Entity e){
        gp.playSE(1);
        gp.ui.addMessage("Moeda +"+ value);
        gp.player.coin += value;
        return true;
    }
}
