package objects;


import entity.Entity;
import principal.GamePanel; 
public class OBJ_Key extends Entity{
	public OBJ_Key(GamePanel gp){
        super(gp);
        name = "Chave";

        down1 = setup("/objects/key", gp.tileSize, gp.tileSize);
        description  ="["+ name+ "]\nAbre uma porta.";
    }
}
