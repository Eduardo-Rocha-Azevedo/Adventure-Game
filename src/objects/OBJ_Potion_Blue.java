package objects;

import entity.Entity;
import principal.GamePanel;

public class OBJ_Potion_Blue extends Entity{
    GamePanel gp;
    public OBJ_Potion_Blue(GamePanel gp){
        super(gp);
        this.gp = gp;
        value = 5;
        type = type_consumable;
        name = "Poção Azul";
        down1 = setup("/objects/potion_blue",gp.tileSize,gp.tileSize);
        description  = "[Poçaõ azul]\nRestaura seu cosmos";
        price = 25;
        stackable = true;
    }

    public boolean use(Entity entity){
        gp.gameState = gp.dialogState;
        gp.ui.currentDialog ="Você bebeu "+name+" !\n" 
        +"Sua vida foi recuperada.";
        entity.cosmo += value;
      
        gp.playSE(2);
        return true;
    }
}
