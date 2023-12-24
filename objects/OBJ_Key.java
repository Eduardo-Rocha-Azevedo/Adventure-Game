package objects;

import entity.Entity;
import principal.GamePanel; 
public class OBJ_Key extends Entity{
	public OBJ_Key(GamePanel gp){
        super(gp);
        name = "key";
        down1 = setup("/objects/key");
    }
}
