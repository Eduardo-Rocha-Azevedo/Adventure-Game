package principal.monster;

import java.util.Random;

import data.Progress;
import entity.Entity;
import objects.OBJ_Door_Iron;
import principal.GamePanel;

public class MOM_Skeleton extends Entity{
    public static final String monName = "Skeleton Lord";
    public MOM_Skeleton(GamePanel gp){   
        super(gp);

        type = type_monster;
        name = monName;
        defaultSpeed = 2;
        speed = defaultSpeed;
        maxLife = 150;
        life = maxLife;
        boss = true;
        sleep = true;
        knockBackPower = 5;
        attack = 10;
        defense = 5;
        exp = 4;

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
        setDialogue();
    }
     public void getImage() {
        int  i = 5;
        if(inRange == false){
            up1 = setup("/monster/skeletonlord_up_1", gp.tileSize*i, gp.tileSize*i);
            up2 = setup("/monster/skeletonlord_up_2", gp.tileSize*i, gp.tileSize*i);
            down1 = setup("/monster/skeletonlord_down_1", gp.tileSize*i, gp.tileSize*i);
            down2 = setup("/monster/skeletonlord_down_2", gp.tileSize*i, gp.tileSize*i);
            left1 = setup("/monster/skeletonlord_left_1", gp.tileSize*i, gp.tileSize*i);
            left2 = setup("/monster/skeletonlord_left_2", gp.tileSize*i, gp.tileSize*i);
            right1 = setup("/monster/skeletonlord_right_1", gp.tileSize*i, gp.tileSize*i);
            right2 = setup("/monster/skeletonlord_right_2", gp.tileSize*i, gp.tileSize*i);
        }
        if(inRange == true){
            up1 = setup("/monster/skeletonlord_phase2_up_1", gp.tileSize*i, gp.tileSize*i);
            up2 = setup("/monster/skeletonlord_phase2_up_2", gp.tileSize*i, gp.tileSize*i);
            down1 = setup("/monster/skeletonlord_phase2_down_1", gp.tileSize*i, gp.tileSize*i);
            down2 = setup("/monster/skeletonlord_phase2_down_2", gp.tileSize*i, gp.tileSize*i);
            left1 = setup("/monster/skeletonlord_phase2_left_1", gp.tileSize*i, gp.tileSize*i);
            left2 = setup("/monster/skeletonlord_phase2_left_2", gp.tileSize*i, gp.tileSize*i);
            right1 = setup("/monster/skeletonlord_phase2_right_1", gp.tileSize*i, gp.tileSize*i);
            right2 = setup("/monster/skeletonlord_phase2_right_2", gp.tileSize*i, gp.tileSize*i);
        }
        
    }

    public void getAttackImage() {
        int  i = 5;
        if(inRange == false){
            attackUp1 = setup("/monster/skeletonlord_attack_up_1", gp.tileSize*i, gp.tileSize*i*2);
            attackUp2 = setup("/monster/skeletonlord_attack_up_2", gp.tileSize*i, gp.tileSize*i*2);
            attackDown1 = setup("/monster/skeletonlord_attack_down_1", gp.tileSize*i, gp.tileSize*i*2);
            attackDown2 = setup("/monster/skeletonlord_attack_down_2", gp.tileSize*i, gp.tileSize*i*2);
            attackLeft1 = setup("/monster/skeletonlord_attack_left_1", gp.tileSize*i*2, gp.tileSize*i);
            attackLeft2 = setup("/monster/skeletonlord_attack_left_2", gp.tileSize*i*2, gp.tileSize*i);
            attackRight1 = setup("/monster/skeletonlord_attack_right_1", gp.tileSize*i*2, gp.tileSize*i);
            attackRight2 = setup("/monster/skeletonlord_attack_right_2", gp.tileSize*i*2, gp.tileSize*i);
        }
        if(inRange == true){
            attackUp1 = setup("/monster/skeletonlord_phase2_attack_up_1", gp.tileSize*i, gp.tileSize*i*2);
            attackUp2 = setup("/monster/skeletonlord_phase2_attack_up_2", gp.tileSize*i, gp.tileSize*i*2);
            attackDown1 = setup("/monster/skeletonlord_phase2_attack_down_1", gp.tileSize*i, gp.tileSize*i*2);
            attackDown2 = setup("/monster/skeletonlord_phase2_attack_down_2", gp.tileSize*i, gp.tileSize*i*2);
            attackLeft1 = setup("/monster/skeletonlord_phase2_attack_left_1", gp.tileSize*i*2, gp.tileSize*i);
            attackLeft2 = setup("/monster/skeletonlord_phase2_attack_left_2", gp.tileSize*i*2, gp.tileSize*i);
            attackRight1 = setup("/monster/skeletonlord_phase2_attack_right_1", gp.tileSize*i*2, gp.tileSize*i);
            attackRight2 = setup("/monster/skeletonlord_phase2_attack_right_2", gp.tileSize*i*2, gp.tileSize*i);
        }  
    }

    public void setDialogue(){
        dialogues[0][0] = "Ninguém pode roubar meu tesouro";
        dialogues[0][1] = "Você vai morrer aqui!";
        dialogues[0][2] = "Este será o seu fim!";
        dialogues[0][2] = "Mwahahaha Mwahahaha!";
    }
    public void setAction() {
        if(inRange == false && life < maxLife/2){
            inRange = true;
            getImage();
            getAttackImage();
            defaultSpeed++;
            speed = defaultSpeed;
            attack *= 2;
        }
        if (getTileDistance(gp.player) < 10) {
           moveTowardPlayer(60);
        } else {
            // Get a random direction
            getRandomDirection(120);
        }

        // Check if it attacks
        if (!attacking) {
            checkAttackOrNot(60, gp.tileSize*7, gp.tileSize*5);
        }
    }

    public void checkDrop(){
        gp.bossBattleOn = false;
        Progress.skeletonLordDefeated = true;
        // Restore the previous music
        gp.stopMusic();
       

        //Remove the iron doors
        for(int i = 0; i < gp.obj[1].length; i++){
            if(gp.obj[gp.currentMap][i] != null && gp.obj[gp.currentMap][i].name.equals(OBJ_Door_Iron.objName)){
                gp.playSE(21);
                gp.obj[gp.currentMap][i] = null;
                gp.playSE(21);
                gp.playMusic(19);
            }
        }
    }

    public void damageReaction() {
        actionLockCounter = 0;
//        direction = gp.player.direction;
        onPath = true;
    }

}
