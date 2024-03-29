package principal.monster;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import entity.Entity;
import objects.OBJ_Coin_gold;
import objects.OBJ_CosmoCrystal;
import objects.OBJ_Heart;
import objects.OBJ_Key;

import principal.GamePanel;

public class MON_Orc extends Entity {

    GamePanel gp;

    public MON_Orc(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_monster;
        name = "Ogro";
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 10;
        life = maxLife;
        attack = 5;
        defense = 2;
        exp = 15;
        knockBackPower = 5;

        solidArea.x = 4;
        solidArea.y = 4;
        solidArea.width = 40;
        solidArea.height = 44;
        solidAreaDefultX = solidArea.x;
        solidAreaDefultY = solidArea.y;
        attackArea.width = 48;
        attackArea.height = 48;

        motion1_duration = 40;
        motion2_duration = 85;

        getImage();
        getAttackImage();
    }

    public void getImage() {
        up1 = setup("/monster/orc_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/monster/orc_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/monster/orc_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/monster/orc_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/monster/orc_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/monster/orc_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/monster/orc_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/monster/orc_right_2", gp.tileSize, gp.tileSize);
    }

    public void getAttackImage() {
        attackUp1 = setup("/monster/orc_attack_up_1", gp.tileSize, gp.tileSize*2);
        attackUp2 = setup("/monster/orc_attack_up_2", gp.tileSize, gp.tileSize*2);
        attackDown1 = setup("/monster/orc_attack_down_1", gp.tileSize, gp.tileSize*2);
        attackDown2 = setup("/monster/orc_attack_down_2", gp.tileSize, gp.tileSize*2);
        attackLeft1 = setup("/monster/orc_attack_left_1", gp.tileSize*2, gp.tileSize);
        attackLeft2 = setup("/monster/orc_attack_left_2", gp.tileSize*2, gp.tileSize);
        attackRight1 = setup("/monster/orc_attack_right_1", gp.tileSize*2, gp.tileSize);
        attackRight2 = setup("/monster/orc_attack_right_2", gp.tileSize*2, gp.tileSize);
    }

    public void setAction() {
        if (onPath) {
            // Check if it stops chasing
            checkStopChassingOrNot(gp.player, 15, 100);

            // Search the direction to go
            searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
        } else {
            // Check if it starts chasing
            checkStartChasingOrNOt(gp.player, 5, 100);

            // Get a random direction
            getRandomDirection(120);
        }

        // Check if it attacks
        if (!attacking) {
            checkAttackOrNot(30, gp.tileSize*4, gp.tileSize);
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
