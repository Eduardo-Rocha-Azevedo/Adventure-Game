package objects;

import entity.Entity;
import principal.GamePanel;

public class OBJ_Lantern extends Entity{

    public OBJ_Lantern(GamePanel gp){
        super(gp);

        type = type_light;
        name = "Lanterna";
        down1 = setup("/objects/lantern",gp.tileSize, gp.tileSize);
        description = "["+name+"]"+"\nIlimina a escuridão";
        price = 200;
        lightRadius = 150;

    }
}
