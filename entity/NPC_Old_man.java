package entity;

import java.util.Random;

import principal.GamePanel;


public class NPC_Old_man extends Entity {
    
    public NPC_Old_man(GamePanel gp){
        super(gp);

        direction = "down";
        speed = 1;
        getImage();
		setDialog();
    }

    	public void getImage(){
	
		up1 = setup("/npc/oldman_up_1");
		up2 = setup("/npc/oldman_up_2");
		down1 = setup("/npc/oldman_down_1");
		down2 = setup("/npc/oldman_down_2");
		left1 = setup("/npc/oldman_left_1");
		left2 = setup("/npc/oldman_left_2");
		right1 = setup("/npc/oldman_right_1");
		right2 = setup("/npc/oldman_right_2");
	}
	//Dialog
	public void setDialog(){
		dialogues[0] = "Olá, rapaz!";
		dialogues[1] = "Então, você veio para esta ilha\nem busca do tesouro?";
		dialogues[2] = "Eu costumava ser um grande caçador,\nmas agora... estou um pouco velho\npara falar de aventuras.";
		dialogues[3] = "Bem, boa sorte para você.";
	}

	//IA
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
	}

	public void speak(){
		if(dialogues[dialogIndex] == null){
			dialogIndex = 0;
		}
		gp.ui.currentDialog = dialogues[dialogIndex];
		dialogIndex++;

		switch(gp.player.direction){
			case "up":
			direction = "down";
			break;
			case "down":
			direction = "up";
			break;
			case "left":
			direction = "right";
			break;
			case "right":
			direction = "left";
			break;

		}
	}
}
