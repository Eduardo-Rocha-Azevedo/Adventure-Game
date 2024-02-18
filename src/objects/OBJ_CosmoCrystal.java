package objects;

import entity.Entity;
import principal.GamePanel;

public class OBJ_CosmoCrystal extends Entity{
    public static final String objName = "Cosmo Crystal";
    public OBJ_CosmoCrystal(GamePanel gp){
        super(gp);
        type = type_pickOnly;
        name = objName;
        value = 1;  
        down1 = setup("/objects/cosmo_crystal_full", gp.tileSize, gp.tileSize);
        image = setup("/objects/cosmo_crystal_full", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/cosmo_crystal_blank", gp.tileSize, gp.tileSize);
    }
    public boolean use(Entity e){
        gp.playSE(1);
        gp.ui.addMessage("Cosmo +"+ value);
        e.cosmo += value;
        return true;
    }
}
