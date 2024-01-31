package objects;

import java.awt.Color;

import entity.Entity;
import entity.Projectile;
import principal.GamePanel;

public class OBJ_Rock  extends Projectile{
    public OBJ_Rock(GamePanel gp){
        super(gp);
        name = "rock"; 
        maxLife = 50;
        speed = 5;
        life = maxLife;
        useCost = 1;
        alive = false;
        attack = 1;
        
        
        getImage();
    }

    public void getImage(){
        up1 = setup("/projectile/rock_up_1", gp.tileSize, gp.tileSize); 
        up2 = setup("/projectile/rock_up_1", gp.tileSize, gp.tileSize);
        down1 = setup("/projectile/rock_up_1", gp.tileSize, gp.tileSize);
        down2 = setup("/projectile/rock_up_1", gp.tileSize, gp.tileSize);
        left1 = setup("/projectile/rock_up_1", gp.tileSize, gp.tileSize);
        left2 = setup("/projectile/rock_up_1", gp.tileSize, gp.tileSize);
        right1 = setup("/projectile/rock_up_1", gp.tileSize, gp.tileSize);
        right2 = setup("/projectile/rock_up_1", gp.tileSize, gp.tileSize);

    }

  

    public Color getParticlesColor(){
        Color color = new Color(65,50,30);
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
