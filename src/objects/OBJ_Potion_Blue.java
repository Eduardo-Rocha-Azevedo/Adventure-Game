package objects;

import entity.Entity;
import principal.GamePanel;

public class OBJ_Potion_Blue extends Entity{
    GamePanel gp;
    public static final String objName = "Poção Azul";
    public OBJ_Potion_Blue(GamePanel gp){
        super(gp);
        this.gp = gp;
        value = 5;
        type = type_consumable;
        name = objName;
        down1 = setup("/objects/potion_blue",gp.tileSize,gp.tileSize);
        description  = "[Poçaõ azul]\nRestaura seu cosmos";
        price = 25;
        stackable = true;
        setDialogue();
    }

    public void setDialogue(){
        dialogues[0][0] = "Você bebeu "+name+" !\n" 
        +"Seu cosmos foi recuperado.";
    }

    public boolean use(Entity entity){
      
        startDialogue(this, 0);
        entity.cosmo += value;
      
        gp.playSE(2);
        return true;
    }
}
