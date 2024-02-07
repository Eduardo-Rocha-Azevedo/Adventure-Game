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
        defaultSpeed = 1;
        speed = defaultSpeed;
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

    public void update(){
        super.update();
        int xDistance = Math.abs(worldX - gp.player.worldX);
        int yDistance = Math.abs(worldY - gp.player.worldY);
        int tileDistance = (xDistance + yDistance)/gp.tileSize;

        if(onPath == false && tileDistance < 5){
            int i = new Random().nextInt(100)+1;
            if(i>50){
                onPath = true;  
            }
        }
        if(onPath == true && tileDistance > 10){
            onPath = false;
        } 
    }

    //IA A* PathFinder
    public void setAction(){
        if(onPath ){
			
			int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;// para seguir o player
			int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;
			searchPath(goalCol, goalRow);

            int i = new Random().nextInt(200)+1; // pick up a number from  1 to 100
            if(i > 197 && projectile.alive == false && shotAvailabelCounter == 30){
            projectile.set(worldX, worldY, direction, true, this);
            //gp.projectileList.add(projectile);

            //Check vacancy
            for(int j = 0; j < gp.projectile[1].length; j++){
                if(gp.projectile[gp.currentMap][j] == null){
                    gp.projectile[gp.currentMap][j] = projectile;
                    break;
                }
            }
            shotAvailabelCounter = 0;
        }
		}
		else{
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
		}
        
    }

    public void damageReaction(){
       actionLockCounter = 0;
       //direction = gp.player.direction;
       onPath = true;
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
