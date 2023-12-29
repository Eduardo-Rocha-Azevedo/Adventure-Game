package entity;


import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.*;
import java.awt.image.BufferedImage;

import principal.GamePanel;
import principal.KeyHandler;


public class Player extends Entity{
	
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	

	public Player(GamePanel gp, KeyHandler keyH) {
		super(gp);
		this.keyH = keyH;

		screenX = gp.screenWith/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);

		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;

		solidAreaDefultX = solidArea.x;
		solidAreaDefultY = solidArea.y;

		solidArea.width = 32;
		solidArea.height = 32;
		
		attackArea.width = 36;
		attackArea.height = 36;

		getPlayerAttackImage();
		setDefultValues();
		getPlayerImage();
		
	}
	
	public void setDefultValues() {
		
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		speed = 4;
		direction = "down";

		//PLAYER STATUS
		maxLife = 6;
		life = maxLife;	
	}

	public void getPlayerImage(){
	
		up1 = setup("/player/boy_up_1",gp.tileSize, gp.tileSize);
		up2 = setup("/player/boy_up_2",gp.tileSize, gp.tileSize);
		down1 = setup("/player/boy_down_1",gp.tileSize,gp.tileSize);
		down2 = setup("/player/boy_down_2",gp.tileSize,gp.tileSize);
		left1 = setup("/player/boy_left_1",gp.tileSize,gp.tileSize);
		left2 = setup("/player/boy_left_2",gp.tileSize,gp.tileSize);
		right1 = setup("/player/boy_right_1",gp.tileSize,gp.tileSize);
		right2 = setup("/player/boy_right_2",gp.tileSize,gp.tileSize);
	}

	public void getPlayerAttackImage(){
		 attackUp1 = setup("/player/boy_attack_up_1",gp.tileSize, gp.tileSize * 2);         // 16x32 px
         attackUp2 = setup("/player/boy_attack_up_2",gp.tileSize, gp.tileSize * 2);         // 16x32 px
         attackDown1 = setup("/player/boy_attack_down_1",gp.tileSize, gp.tileSize * 2);     // 16x32 px
         attackDown2 = setup("/player/boy_attack_down_2",gp.tileSize, gp.tileSize * 2);     // 16x32 px
         attackLeft1 = setup("/player/boy_attack_left_1",gp.tileSize * 2, gp.tileSize);      // 32x16 px
         attackLeft2 = setup("/player/boy_attack_left_2",gp.tileSize * 2, gp.tileSize);      // 32x16 px
         attackRight1 = setup("/player/boy_attack_right_1",gp.tileSize * 2, gp.tileSize);    // 32x16 px
         attackRight2 = setup("/player/boy_attack_right_2",gp.tileSize * 2, gp.tileSize);    // 32x16 px
	}
	
	public void update() {
		
		if(attacking == true){
			attacking();
			
		}
		else if(keyH.up == true || keyH.down == true ||
				 keyH.left == true || keyH.right == true || keyH.enterPressed == true){
				
			if(keyH.up == true) {
				direction = "up";
			}
			else if(keyH.down == true){
				direction = "down";
			}	
			else if(keyH.left == true){
				direction = "left";
			}
			else if(keyH.right == true){
				direction = "right";
			}
			

			//CHECK TILE COLLISION
			collisioOn = false;
			gp.cChecker.checkTile(this);

			//CHECK OBJECT COLLISION
			int objIndex = gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);

			//CHECK NPC COLLISION
			int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
			interactNPC(npcIndex);

			//CHECK MONSTER COLLISION
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			contactMonster(monsterIndex);

			//CHECK EVENT 
			gp.eHandler.checkEvent();
			

			//IF COLLISION IS FALSE, PLAYER CAN MOVE
			if(collisioOn == false && keyH.enterPressed == false){
				switch(direction){
					case "up":worldY -= speed;break;
					case "down":worldY += speed;break;
					case "left":worldX -= speed;break;
					case "right":worldX += speed;break;
				}
			}
			gp.keyH.enterPressed = false;
			spriteCouter++;

			if(spriteCouter > 12){
				if(spriteNum == 1){
					spriteNum = 2;
				}
				else if(spriteNum == 2){
					spriteNum = 1;
				}
				spriteCouter = 0;
			}
		}

		//This needs to be outside of key if statement
		if(invincible == true){
			invincibleCounter++;
			if(invincibleCounter > 60){
				invincible = false;
				invincibleCounter = 0;
			}
		}
	}

	public void attacking(){
		spriteCouter++;
		
		if(spriteCouter <= 5){
			spriteNum = 1;
		}
		if(spriteCouter > 5 && spriteCouter <= 25){
			spriteNum = 2;

			//Save current position
			int currentWorldX = worldX;
			int currentWorldY = worldY;
			int solidAreaWidth = solidArea.width;
			int solidAreaHeight = solidArea.height;

			//Adjust player's wordX/Y for the attack
			switch(direction){
				case "up": worldX -= attackArea.height;break;
				case "down": worldX += attackArea.height;break;
				case "left": worldY -= attackArea.width;break;
				case "right": worldY += attackArea.width;break;
			}
			//attackArea becomes solidArea
			solidArea.width = attackArea.width;
			solidArea.height = attackArea.height;

			//check collision with the update worldX/Y and solidArea
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			damageMonster(monsterIndex);

			//After attack, reset player's worldX/Y and solidArea
			worldX = currentWorldX;
			worldY = currentWorldY;
			solidArea.width = solidAreaWidth;
			solidArea.height = solidAreaHeight;
		}
		if(spriteCouter > 25){
			spriteNum = 1;
			spriteCouter = 0;
			attacking = false;
		}
	}

	public void pickUpObject(int i){
		if(i != 999){
		}
	}

	public void interactNPC(int i){

		if(gp.keyH.enterPressed == true){

			if(i != 999){

				gp.gameState = gp.dialogState;
				gp.npc[i].speak();
			}
			else {
				gp.playSE(7);
				attacking = true;
			}
		}
	}

	public void contactMonster(int i){
		if(i != 999){
			if(invincible == false){
				gp.playSE(6);
				life--;
				invincible = true;
			}
		}
	}
	
	public void damageMonster(int i){
		if(i != 999){

			if(gp.monster[i].invincible == false){

				gp.playSE(5);
				gp.monster[i].life--;
				gp.monster[i].invincible = true;
				gp.monster[i].damageReaction();

				if(gp.monster[i].life <= 0){
					gp.monster[i].dyain = true;
				}
			}
		}
	}

	public void draw(Graphics g2) {

		BufferedImage image = null;
		int tempScreenX = screenX;
		int tempScreenY = screenY;
		switch(direction){

			case "up": 
				if(attacking == false) {
					if(spriteNum == 1) { image = up1;}
					if(spriteNum == 2) { image = up2;}
				}
				if(attacking == true)  {
					tempScreenY = screenY - gp.tileSize;
					if(spriteNum == 1) {image = attackUp1;}
					if(spriteNum == 2) {image = attackUp2;}
				}
				break;

			case "down": 
					if(attacking == false){

						if(spriteNum == 1) {image = down1;}
						if(spriteNum == 2) {image = down2;}
					}
					if(attacking == true){

						if(spriteNum == 1) {image = attackDown1;}
						if(spriteNum == 2) {image = attackDown2;}
					}
					break;
 				
			case "left":
					if(attacking == false){

						if(spriteNum == 1) {image = left1;}
						if(spriteNum == 2) {image = left2;}
					}
					if(attacking == true){
						tempScreenX = screenX - gp.tileSize;
						if(spriteNum == 1) {image = attackLeft1;}
						if(spriteNum == 2) {image = attackLeft2;}
					}
					break;

			case "right": 
					if(attacking == false){

						if(spriteNum == 1) {image = right1;}
						if(spriteNum == 2) {image = right2;}
					}
					if(attacking == true){
						if(spriteNum == 1) {image = attackRight1;}
						if(spriteNum == 2) {image = attackRight2;}
					}
					break;	
		}
		if(invincible == true){
			
			// g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.4f));
		}
		
		g2.drawImage(image, tempScreenX, tempScreenY, null);

		//reset alpha
		//g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));

		//DEBUG

	}

}











