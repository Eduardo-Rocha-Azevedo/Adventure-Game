package objects;

import entity.Entity;
import principal.GamePanel;

public class OBJ_BlueHeart extends Entity{
    public static final String objName = "Blue Heart"; 

    public OBJ_BlueHeart(GamePanel gp){
        super(gp);
        this.gp = gp;
        name = objName;
        type = type_pickOnly;
        setDialogue();
        down1 = setup("/objects/blueheart", gp.tileSize, gp.tileSize);
    }

    public void setDialogue(){
        dialogues[0][0] = "Você pegou uma linda pedra azul!";
        dialogues[0][1] = "Você encontrou o Coração Azul,\no tesouro legendário !";
    }

    public boolean use(Entity entity){
        gp.gameState = gp.cutsceneState;
        gp.csManager.sceneNum = gp.csManager.ending;
        return true;
    }
    
}
