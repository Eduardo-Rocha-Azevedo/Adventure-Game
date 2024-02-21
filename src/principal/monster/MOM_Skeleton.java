package principal.monster;

import java.util.Random;

import entity.Entity;
import objects.OBJ_Coin_gold;
import objects.OBJ_CosmoCrystal;
import objects.OBJ_Heart;
import objects.OBJ_Key;
import principal.GamePanel;

public class MOM_Skeleton extends Entity{
    public static final String monName = "Skeleton Lord";
    public MOM_Skeleton(GamePanel gp){   
        super(gp);

        type = type_monster;
        name = monName;
        defaultSpeed = 4;
        speed = defaultSpeed;
        maxLife = 50;
        life = maxLife;
       
        attack = 10;
        defense = 2;
        exp = 150;

        int size = gp.tileSize*5;
        solidArea.x = 48;
        solidArea.y = 48;
        solidArea.width = size - 48 *2;
        solidArea.height = size - 48;
        solidAreaDefultX = solidArea.x;
        solidAreaDefultY = solidArea.y;

        attackArea.width = 170;
        attackArea.height = 170;
        motion1_duration = 25;
        motion2_duration = 50;

        getImage();
        getAttackImage();
    }
     public void getImage() {
        int  i = 5;
        up1 = setup("/monster/skeletonlord_up_1", gp.tileSize*i, gp.tileSize*i);
        up2 = setup("/monster/skeletonlord_up_2", gp.tileSize*i, gp.tileSize*i);
        down1 = setup("/monster/skeletonlord_down_1", gp.tileSize*i, gp.tileSize*i);
        down2 = setup("/monster/skeletonlord_down_2", gp.tileSize*i, gp.tileSize*i);
        left1 = setup("/monster/skeletonlord_left_1", gp.tileSize*i, gp.tileSize*i);
        left2 = setup("/monster/skeletonlord_left_2", gp.tileSize*i, gp.tileSize*i);
        right1 = setup("/monster/skeletonlord_right_1", gp.tileSize*i, gp.tileSize*i);
        right2 = setup("/monster/skeletonlord_right_2", gp.tileSize*i, gp.tileSize*i);
    }

    public void getAttackImage() {
        int  i = 5;

        attackUp1 = setup("/monster/skeletonlord_attack_up_1", gp.tileSize*i, gp.tileSize*i*2);
        attackUp2 = setup("/monster/skeletonlord_attack_up_2", gp.tileSize*i, gp.tileSize*i*2);
        attackDown1 = setup("/monster/skeletonlord_attack_down_1", gp.tileSize*i, gp.tileSize*i*2);
        attackDown2 = setup("/monster/skeletonlord_attack_down_2", gp.tileSize*i, gp.tileSize*i*2);
        attackLeft1 = setup("/monster/skeletonlord_attack_left_1", gp.tileSize*i*2, gp.tileSize*i);
        attackLeft2 = setup("/monster/skeletonlord_attack_left_2", gp.tileSize*i*2, gp.tileSize*i);
        attackRight1 = setup("/monster/skeletonlord_attack_right_1", gp.tileSize*i*2, gp.tileSize*i);
        attackRight2 = setup("/monster/skeletonlord_attack_right_2", gp.tileSize*i*2, gp.tileSize*i);
    }

    public void setAction() {
        if (onPath) {
           
        } else {
            // Get a random direction
            getRandomDirection(120);
        }

        // Check if it attacks
        if (!attacking) {
            checkAttackOrNot(30, gp.tileSize*10, gp.tileSize*5);
        }
    }

    public void damageReaction() {
        actionLockCounter = 0;
//        direction = gp.player.direction;
        onPath = true;
    }

    public void checkDrop() {
        // CAST A DIE
        int i = new Random().nextInt(100) + 1;

        // SET THE MONSTER DROP
        if (i < 50) {
            dropItem(new OBJ_Coin_gold(gp));
            dropItem(new OBJ_Key(gp));
        }
        if (i >= 50 && i < 75) {
            dropItem(new OBJ_Heart(gp));
            dropItem(new OBJ_Key(gp));
        }
        if (i >= 75 && i < 100) {
            dropItem(new OBJ_CosmoCrystal(gp));
            dropItem(new OBJ_Coin_gold(gp));
            dropItem(new OBJ_Key(gp));
        }
    }
}
