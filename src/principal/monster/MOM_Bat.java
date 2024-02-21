package principal.monster;
import java.util.Random;

import entity.Entity;
import objects.OBJ_Coin_gold;
import objects.OBJ_CosmoCrystal;
import objects.OBJ_Heart;
import principal.GamePanel;

public class MOM_Bat extends Entity{
    public MOM_Bat(GamePanel gp){   
        super(gp);

        type = type_monster;
        name = "Morcego";
        defaultSpeed = 4;
        speed = defaultSpeed;
        maxLife = 2;
        life = maxLife;
       
        attack = 1;
        defense = 0;
        exp = 2;

        solidArea.x = 3;
        solidArea.y = 15;
        solidArea.width = 42;
        solidArea.height = 21;
        solidAreaDefultX = solidArea.x;
        solidAreaDefultY = solidArea.y;

        getImage();
    }

    public void getImage(){
        up1 = setup("/monster/bat_down_1", gp.tileSize, gp.tileSize);
        up2 = setup("/monster/bat_down_2", gp.tileSize, gp.tileSize);
        down1 = setup("/monster/bat_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/monster/bat_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/monster/bat_down_1", gp.tileSize, gp.tileSize);
        left2 = setup("/monster/bat_down_2", gp.tileSize, gp.tileSize);
        right1 = setup("/monster/bat_down_1", gp.tileSize, gp.tileSize);
        right2 = setup("/monster/bat_down_2", gp.tileSize, gp.tileSize);
    }

    //IA A* PathFinder
    public void setAction(){
    
        if(onPath == true){
            //Check if it stops chasing
           // checkStopChassingOrNot(gp.player, 10, 100);
           
            //Search the direction to go 
			//searchPath(getGoalCol(gp.player), getGoalRow(gp.player));

            // Check if it shoots a projectile
           // checkShootOrNot(200, 30);  
        }
        else{
            //checkStartChasingOrNOt(gp.player, 5, 100);
            // Get a random direction
            getRandomDirection(10);
        } 
        
    }

    public void damageReaction(){
       actionLockCounter = 0;
       //direction = gp.player.direction;
       //onPath = true;
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
