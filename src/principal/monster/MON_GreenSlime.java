package principal.monster;

import java.util.Random;

import entity.Entity;
import objects.OBJ_Coin_gold;
import objects.OBJ_CosmoCrystal;
import objects.OBJ_Heart;
import objects.OBJ_Rock;
import principal.GamePanel;

public class MON_GreenSlime extends Entity{
    public MON_GreenSlime(GamePanel gp){
        super(gp);

        type = type_monster;
        name = "Slime Verde";
        speed = 1;
        maxLife = 4;
        life = maxLife;
        projectile = new OBJ_Rock(gp);
        attack = 2;
        defense = 0;
        exp = 2;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefultX = solidArea.x;
        solidAreaDefultY = solidArea.y;

        getImage();
    }

    public void getImage(){
        up1 = setup("/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
        up2 = setup("/monster/greenslime_down_2", gp.tileSize, gp.tileSize);
        down1 = setup("/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/monster/greenslime_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
        left2 = setup("/monster/greenslime_down_2", gp.tileSize, gp.tileSize);
        right1 = setup("/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
        right2 = setup("/monster/greenslime_down_2", gp.tileSize, gp.tileSize);
    }

    //IA simple
    public void setAction(){
        actionLockCounter++;
       
		if(actionLockCounter == 120){
			 Random random = new Random();
			
			int i = random.nextInt(100)+1; // pick up a number from  1 to 100

			if(i <= 25){
				direction = "up";
			}
			if(i > 25 && i <= 50){
				direction = "down";
			}
			if(i > 50 && i <= 75){
				direction = "left";
			}
			if(i > 75 && i <= 100){
				direction = "right";
			}

			actionLockCounter = 0;
		}
        int i = new Random().nextInt(100)+1; // pick up a number from  1 to 100
        if(i > 99 && projectile.alive == false && shotAvailabelCounter == 30){
            projectile.set(worldX, worldY, direction, true, this);
            gp.projectileList.add(projectile);
            shotAvailabelCounter = 0;
        }
    }

    public void damageReaction(){
       actionLockCounter = 0;
       direction = gp.player.direction;
       
    }

    public void checkDrop(){
        //CAST A DIE
        int i = new Random().nextInt(100)+1; // pick up a number from  1 to 100

        //SET THE MONSTER DROP 
       /*   Drop de OBJ_Coin_gold: 50%
            Drop de OBJ_Heart: 25%
            Drop de OBJ_CosmoCrystal: 25% */ 

        if( i < 50){
            dropItem(new OBJ_Coin_gold(gp));
        }
        if(i > 50 && i < 75){
            dropItem(new OBJ_Heart(gp));
        }
        if(i >= 75 && i < 100){
            dropItem(new OBJ_CosmoCrystal(gp));
        }


    }
}
