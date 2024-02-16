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
    public void setDialogue(){
        dialogues[0][0] = "Você usou: " + name +" e abriu a porta!";
        dialogues[1][0] = "Oque você está fazendo!";
    }
    public boolean use(Entity entity){
       
        int objIndex = getDetected(entity, gp.obj, "Door");
        if(objIndex != 999){ 
            startDialogue(this, 0);
            gp.playSE(3);
            gp.obj[gp.currentMap][objIndex] = null;
            return true;
        }
        else{
           startDialogue(this, 1);
           
        }
        return false;
    }
}
