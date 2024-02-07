package objects;

import java.awt.Color;

import entity.Entity;
import entity.Projectile;
import principal.GamePanel;

public class OBJ_Fireball extends Projectile{
    GamePanel gp;

    public OBJ_Fireball(GamePanel gp){
        super(gp);
        this.gp = gp;

        name = "Fireball";
        speed = 7;
        maxLife = 80;
        life = maxLife;
        attack = 5;
        useCost = 1;
        alive = false;
        knockBackPower = 10;
        getImage();

    }
    public void getImage(){
        up1 = setup("/projectile/fireball_up_1", gp.tileSize, gp.tileSize); 
        up2 = setup("/projectile/fireball_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/projectile/fireball_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/projectile/fireball_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/projectile/fireball_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/projectile/fireball_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/projectile/fireball_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/projectile/fireball_right_2", gp.tileSize, gp.tileSize);
    }

    public boolean haveResource(Entity user){
        boolean haveResource = false;
        if(user.cosmo >= useCost){
            haveResource = true;
        }
        return haveResource;
    }

    public void substracResource(Entity user){
        user.cosmo -= useCost;
    }

    public Color getParticlesColor(){
        Color color = new Color(0,71,171);
        return color;
    }

    public int getParticlesSize(){
        int size = 7;
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
