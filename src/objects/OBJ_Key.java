package objects;


import entity.Entity;
import principal.GamePanel; 
public class OBJ_Key extends Entity{
    GamePanel gp;
	public OBJ_Key(GamePanel gp){
        super(gp);
        this.gp = gp;
        name = "Chave";
        type = type_consumable;
        down1 = setup("/objects/key", gp.tileSize, gp.tileSize);
        description  ="["+ name+ "]\nAbre uma porta.";
        price = 100;
        stackable = true;
    }
    public boolean use(Entity entity){
       
        int objIndex = getDetected(entity, gp.obj, "Door");
        if(objIndex != 999){ 
            gp.ui.addMessage("Você usou: " + name+" e abriu a porta!");
            gp.playSE(3);
            gp.obj[gp.currentMap][objIndex] = null;
            return true;
        }
        else{
            gp.gameState = gp.dialogState;
            gp.ui.currentDialog = "Oque você está fazendo!";
           
        }
        return false;
    }
}
