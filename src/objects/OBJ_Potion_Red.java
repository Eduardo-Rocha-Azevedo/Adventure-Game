package objects;

import entity.Entity;
import principal.GamePanel;

public class OBJ_Potion_Red extends Entity {
    int value = 5;
    GamePanel gp;

    public OBJ_Potion_Red(GamePanel gp){
        super(gp);
        this.gp = gp;
        type = type_consumable;
        name = "Poção Vermelha";
        down1 = setup("/objects/potion_red",gp.tileSize,gp.tileSize);
        description  = "["+name+"]\n"+"Cura sua vida " + value+".";
    }

    public void use(Entity e){
        gp.gameState = gp.dialogState;
        gp.ui.currentDialog ="Você bebeu "+name+" !\n" 
        +"Sua vida foi recuperada " + value + ".";
        e.life += value;
        if(gp.player.life > gp.player.maxLife){
            gp.player.life = gp.player.maxLife;
             
        }
           
        gp.playSE(2);
    }
}
