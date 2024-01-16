package tile_interactive;

import entity.Entity;
import principal.GamePanel;

public class IT_DryTree extends InteractiveTile {
    GamePanel gp;
    public IT_DryTree(GamePanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;
        this.worldX = gp.tileSize*col;
        this.worldY = gp.tileSize*row;
        life = 3;
        down1 = setup("/tiles_interatives/drytree",gp.tileSize,gp.tileSize);
        destructible = true;    
    }

    public boolean isCorrectItem(Entity e){
        boolean isCorrectItem = false;

        if(e.currentWeapon.type == type_axe){
            isCorrectItem = true;
        }
        return isCorrectItem;
    }
    public void playSE(){
        gp.playSE(11);
    }

    public InteractiveTile getDestroyedForm(){
        InteractiveTile tile = new IT_Trunk(gp, worldX/gp.tileSize, worldY/gp.tileSize);
        return tile;
    }

}
