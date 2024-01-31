package objects;

import entity.Entity;
import principal.GamePanel;

public class OBJ_Potion_Red extends Entity {

    GamePanel gp;

    public OBJ_Potion_Red(GamePanel gp){
        super(gp);
        this.gp = gp;
        value = 5;
        type = type_consumable;
        name = "Poção Vermelha";
        down1 = setup("/objects/potion_red",gp.tileSize,gp.tileSize);
        description  = "["+name+"]\n"+"Cura sua vida.";
        price = 25;
    }

    public void use(Entity e){
        gp.gameState = gp.dialogState;
        gp.ui.currentDialog ="Você bebeu "+name+" !\n" 
        +"Sua vida foi recuperada.";
        e.life += value;
      
        gp.playSE(2);
    }
}
