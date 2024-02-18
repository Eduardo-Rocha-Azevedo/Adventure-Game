package objects;

import entity.Entity;
import principal.GamePanel;

public class OBJ_Potion_Red extends Entity {

    GamePanel gp;
    public static final String objName = "Poção Vermelha";
    public OBJ_Potion_Red(GamePanel gp){
        super(gp);
        this.gp = gp;
        value = 5;
        type = type_consumable;
        name = objName;
        down1 = setup("/objects/potion_red",gp.tileSize,gp.tileSize);
        description  = "["+name+"]\n"+"Cura sua vida.";
        price = 25;
        stackable = true;
        setDialogue();
    }

    public void setDialogue(){
        dialogues[0][0] = "Você bebeu "+name+" !\n" 
        +"Sua vida foi recuperada.";
    }

    public boolean use(Entity e){
        startDialogue(this, 0);
        e.life += value;
        gp.playSE(2);
        return true;
    }
}
