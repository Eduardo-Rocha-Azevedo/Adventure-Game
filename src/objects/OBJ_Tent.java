package objects;

import entity.Entity;
import principal.GamePanel;

public class OBJ_Tent extends Entity{
    GamePanel gp;
    public OBJ_Tent(GamePanel gp){
        super(gp);
        this.gp = gp;

        name = "Tenda";
        type = type_consumable;
        down1 = setup("/objects/tent",gp.tileSize, gp.tileSize);
        description = "[Tenda]\n Você pode dormir aqui\npara passar a noite.";
        price = 100;
        stackable = true;

    }

    public boolean use(Entity entity){
        gp.gameState = gp.sleepState;
        gp.playSE(15);
        gp.player.life = gp.player.maxLife;
        gp.player.cosmo = gp.player.maxCosmo;
        gp.player.getSleepImage(down1);
        return true;
    }
}
