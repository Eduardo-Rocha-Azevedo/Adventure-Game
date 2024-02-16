package entity;

import java.awt.Rectangle;
import java.util.Random;

import principal.GamePanel;


public class NPC_Old_man extends Entity {
    
    public NPC_Old_man(GamePanel gp){
    	super(gp);
        direction = "down";
        speed = 1;

        solidArea = new Rectangle();
        solidArea.x = 8;
	    solidArea.y = 16;
		solidAreaDefultX = solidArea.x;
        solidAreaDefultY = solidArea.y;
        solidArea.width = 30;
        solidArea.height = 30;

      

        // dialogueSet = -1; //For first dialogueSet(= 0)

         getImage();
         setDialog();
    }

    	public void getImage(){
	
		up1 = setup("/npc/oldman_up_1",gp.tileSize, gp.tileSize);
		up2 = setup("/npc/oldman_up_2", gp.tileSize, gp.tileSize);
		down1 = setup("/npc/oldman_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/npc/oldman_down_2", gp.tileSize, gp.tileSize);
		left1 = setup("/npc/oldman_left_1", gp.tileSize, gp.tileSize);
		left2 = setup("/npc/oldman_left_2", gp.tileSize, gp.tileSize);
		right1 = setup("/npc/oldman_right_1", gp.tileSize, gp.tileSize);
		right2 = setup("/npc/oldman_right_2", gp.tileSize, gp.tileSize);
	}
	//Dialog
	public void setDialog(){
		dialogues[0][0] = "Olá, rapaz!";
		dialogues[0][1] = "Então, você veio para esta ilha\nem busca do cristal?";
		dialogues[0][2] = "Eu costumava ser um grande cavaleiro,\nmas agora... estou um pouco velho\npara falar de aventuras.";

		dialogues[1][0] = "Existem muitos perigos nesta ilha.\nTenha cuidado!";
		dialogues[1][1] = "Para poder ataca-los é presciso\nconcentrar sua energia.\nVocê sabe como fazer isso?";
		dialogues[1][2] = "Se você não sabe, eu posso te ensinar.\nA base é destruir os atmos concentrando energia.";
		dialogues[1][3] = "Boa sorte para você.";

		dialogues[2][0] = "Eu me pergunto como abrir essa porta";


	}

	//IA
	public void setAction(){
		if(onPath ){
			int goalCol = 12;
			int goalRow = 9;
			//int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize; para seguir o player
			//int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;
			searchPath(goalCol, goalRow);
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

	public void speak(){
		getFacePlayer();
		startDialogue(this, dialogueSet);

		//onPath = true;
	}
}
