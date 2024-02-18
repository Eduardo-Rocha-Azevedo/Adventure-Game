package tile_interactive;

import java.awt.Color;

import entity.Entity;
import principal.GamePanel;

public class IT_DestructibleWall extends InteractiveTile{
    public IT_DestructibleWall(GamePanel gp, int col, int row){
        super(gp, col, row);
        this.gp = gp;
        this.worldX = gp.tileSize*col;
        this.worldY = gp.tileSize*row;
        life = 3;
        down1 = setup("/tiles_interatives/destructiblewall",gp.tileSize,gp.tileSize);
        destructible = true;    
    }

    public boolean isCorrectItem(Entity e){
        boolean isCorrectItem = false;

        if(e.currentWeapon.type == type_pickaxe){
            isCorrectItem = true;
        }
        return isCorrectItem;
    }
    public void playSE(){
        gp.playSE(11);
    }

    /*public InteractiveTile getDestroyedForm(){
        InteractiveTile tile = new IT_Trunk(gp, worldX/gp.tileSize, worldY/gp.tileSize);
        return tile;
    }*/

    public Color getParticlesColor(){
        Color color = new Color(65,65,65);
        return color;
    }

    public int getParticlesSize(){
        int size = 6;
        return size;
    }

    public int getParticlesSpeed(){
        int speed = 1;
        return speed;
    }

    public int getParticlesMaxLife(){
        int maxLife = 20;
        return maxLife;
    }
}
