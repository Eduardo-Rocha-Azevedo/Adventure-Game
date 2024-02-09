package entity;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import objects.OBJ_Axe;
import objects.OBJ_Fireball;
import objects.OBJ_Shield_Wood;
import principal.GamePanel;
import principal.KeyHandler;


public class Player extends Entity{
	
	KeyHandler keyH;
	Graphics2D g2;
	public final int screenX;
	public final int screenY;
	int standCounter = 0;
	public boolean attackCanceled = false;
	public boolean lightUpdated = false;
	public final int maxInventorySize = 20;

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
		
		setDefultValues();
		getPlayerImage();
		getPlayerAttackImage();
		setItems();
		
	}
	
	public void setDefultValues() {
		
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		//worldX = gp.tileSize * 12;
		//worldY = gp.tileSize * 9;

		defaultSpeed = 4;
		speed = defaultSpeed;
		direction = "down";

		//PLAYER STATUS
		level = 1;
		maxLife = 6;
		life = maxLife;	
		strength = 1; //The more strength he has, the more damage
		dexterity = 1; // the more dexterity he has, the more defense
		exp = 0;
		nextLevelExp = 5;
		coin = 1000;
		maxCosmo = 4;
		cosmo = maxCosmo;
		
		//ITEMS
	
		currentWeapon = new OBJ_Axe(gp);
        currentShield = new OBJ_Shield_Wood(gp);
		projectile = new OBJ_Fireball(gp);
		attack = getAttack(); //the total attack value is decided by strength and Weapon
		defense = getDefense(); //the total defense value is decided by dexterity and Shield

	}
	
	//add items inicially
	public void setItems(){
		inventory.clear();
		inventory.add(currentWeapon);
		inventory.add(currentShield);
	
	}
	public void setDefultPositions(){
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		direction = "down";
	}
	public void retoreLifeAndCosmo(){
		life = maxLife;
		cosmo = maxCosmo;
		invincible = false;
	}
	public int getAttack(){
		attackArea = currentWeapon.attackArea;
		return attack = strength * currentWeapon.attackValue;
	}

	public int getDefense(){
		
		return defense = dexterity * currentShield.defenseValue;
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
		
        if(currentWeapon.type == type_sword){
        
            attackUp1 = setup("/player/boy_attack_up_1",gp.tileSize, gp.tileSize*2);         // 16x32 px
            attackUp2 = setup("/player/boy_attack_up_2",gp.tileSize, gp.tileSize*2);         // 16x32 px
            attackDown1 = setup("/player/boy_attack_down_1",gp.tileSize, gp.tileSize * 2);     // 16x32 px
            attackDown2 = setup("/player/boy_attack_down_2",gp.tileSize, gp.tileSize * 2);     // 16x32 px
            attackLeft1 = setup("/player/boy_attack_left_1",gp.tileSize * 2, gp.tileSize);      // 32x16 px
            attackLeft2 = setup("/player/boy_attack_left_2",gp.tileSize * 2, gp.tileSize);      // 32x16 px
            attackRight1 = setup("/player/boy_attack_right_1",gp.tileSize * 2, gp.tileSize);    // 32x16 px
            attackRight2 = setup("/player/boy_attack_right_2",gp.tileSize * 2, gp.tileSize);    // 32x16 px
        }
        else if(currentWeapon.type == type_axe) {
       
            attackUp1 = setup("/player/boy_axe_up_1",gp.tileSize, gp.tileSize * 2);         // 16x32 px
            attackUp2 = setup("/player/boy_axe_up_2",gp.tileSize, gp.tileSize * 2);         // 16x32 px
            attackDown1 = setup("/player/boy_axe_down_1",gp.tileSize, gp.tileSize * 2);     // 16x32 px
            attackDown2 = setup("/player/boy_axe_down_2",gp.tileSize, gp.tileSize * 2);     // 16x32 px
            attackLeft1 = setup("/player/boy_axe_left_1",gp.tileSize * 2, gp.tileSize);      // 32x16 px
            attackLeft2 = setup("/player/boy_axe_left_2",gp.tileSize * 2, gp.tileSize);      // 32x16 px
            attackRight1 = setup("/player/boy_axe_right_1",gp.tileSize * 2, gp.tileSize);    // 32x16 px
            attackRight2 = setup("/player/boy_axe_right_2",gp.tileSize * 2, gp.tileSize);    // 32x16 px
        }
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

			//CHECK INTERACTIVE TILE COLLISION
			int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);

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
			if(keyH.enterPressed == true && attackCanceled == false){
				gp.playSE(7);
				attacking = true;
				spriteCouter = 0;
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

		//PROJECTILE
		if(gp.keyH.shotKeyPressed == true && projectile.alive == false && shotAvailabelCounter == 30 && projectile.haveResource(this) == true){

			//set defult coordinates, direction and user
			projectile.set(worldX, worldY, direction,true, this);

			//substract the cost (cosmo)
			projectile.substracResource(this);
			
			// Check vacancy
			for(int i = 0; i < gp.projectile[1].length; i++){
				if(gp.projectile[gp.currentMap][i] == null){
					gp.projectile[gp.currentMap][i] = projectile;
					break;
				}
			}
			shotAvailabelCounter = 0;
			gp.playSE(10);
		}

		//This needs to be outside of key if statement
		if(invincible == true){
			invincibleCounter++;
			if(invincibleCounter > 60){
				invincible = false;
				invincibleCounter = 0;
			}
		}
		if(shotAvailabelCounter < 30){
			shotAvailabelCounter++;
		}
		if(life > maxLife){
            life = maxLife;
        }
		if(cosmo > maxCosmo){
			cosmo = maxCosmo;
		}

		if(life <= 0){
			gp.gameState = gp.gameOverState;
			gp.ui.commandNum--;
			//gp.stopMusic();
			gp.playSE(13);

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
			damageMonster(monsterIndex, attack, currentWeapon.knockBackPower);

			int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
			damageInteractiveTile(iTileIndex);

			int projectileIndex = gp.cChecker.checkEntity(this, gp.projectile);
			damageProjectile(projectileIndex);
			 

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
			//PICKUP ONLY ITEMS
			if(gp.obj[gp.currentMap][i].type == type_pickOnly){
				gp.obj[gp.currentMap][i].use(this);
				gp.obj[gp.currentMap][i] = null;
			}

			//Obstacle
			else if(gp.obj[gp.currentMap][i].type == type_obstacle){
				if(keyH.enterPressed == true){
					attackCanceled = true;
					gp.obj[gp.currentMap][i].interact();
				}

			}
			//INVENTRY ITEMS
			else {
				String text;
				if(canObtainItem(gp.obj[gp.currentMap][i]) == true){
					
					gp.playSE(1);
					text = "Pegou " + gp.obj[gp.currentMap][i].name +"!";
				}
				else{
					text = "Inventário cheio!";
				}
				gp.ui.addMessage(text);
				gp.obj[gp.currentMap][i] = null; // fixed don't forget this 
			}
		}
	}

	public void interactNPC(int i){

		if(gp.keyH.enterPressed == true){

			if(i != 999){
				attackCanceled = true;
				gp.gameState = gp.dialogState;
				gp.npc[gp.currentMap][i].speak();
			}else{
				attacking = true;
			}

		}
	}

	public void contactMonster(int i){
		if(i != 999){
			if(invincible == false && gp.monster[gp.currentMap][i].dyain == false){
				gp.playSE(6);
				int damage = gp.monster[gp.currentMap][i].attack - defense;

				if(damage < 0){
					damage = 0;
				}

				life -= damage;
				invincible = true;
			}
		}
	}
	
	public void damageMonster(int i, int attack, int knockBackPower) {

        if (i != 999) {
            if (gp.monster[gp.currentMap][i].invincible == false) {

                gp.playSE(5);

				if(knockBackPower > 0){
					knockBack(gp.monster[gp.currentMap][i], knockBackPower);
				}
				
                int damage = attack - gp.monster[gp.currentMap][i].defense;
                if (damage < 0) {
                    damage = 0;
                }

                gp.monster[gp.currentMap][i].life -= damage;
                gp.ui.addMessage(damage + " Dano!");

                gp.monster[gp.currentMap][i].invincible = true;
                gp.monster[gp.currentMap][i].damageReaction();

                if (gp.monster[gp.currentMap][i].life <= 0) {
                    gp.monster[gp.currentMap][i].life = 0;
                    gp.monster[gp.currentMap][i].dyain = true;
                    gp.ui.addMessage("Matou " + gp.monster[gp.currentMap][i].name + "!");
                    gp.ui.addMessage("Exp " + gp.monster[gp.currentMap][i].exp);
                    exp += gp.monster[gp.currentMap][i].exp;
                    checkLevelUp();
                }
            }
        }
    }

	public void knockBack(Entity entity, int knockBackPower){
		entity.direction = direction;
		entity.speed += knockBackPower;
		entity.knockBack = true;
	}

	public void damageInteractiveTile(int i){
		if( i != 999 && gp.iTile[gp.currentMap][i].destructible == true && gp.iTile[gp.currentMap][i].isCorrectItem(this) == true && gp.iTile[gp.currentMap][i].invincible == false){
			gp.iTile[gp.currentMap][i].playSE();
			gp.iTile[gp.currentMap][i].life--;
			gp.iTile[gp.currentMap][i].invincible = true;

			generateParticle(gp.iTile[gp.currentMap][i], gp.iTile[gp.currentMap][i]);
			if(gp.iTile[gp.currentMap][i].life == 0){
				gp.iTile[gp.currentMap][i] = gp.iTile[gp.currentMap][i].getDestroyedForm();
			}
		}
	}

	public void damageProjectile(int i){
		if(i != 999){
			Entity projectile = gp.projectile[gp.currentMap][i];
			projectile.alive = false;
			generateParticle(projectile, projectile);
		}
	}

	public void checkLevelUp(){
		if(exp >= nextLevelExp){
			level++;
			cosmo++;
			nextLevelExp = nextLevelExp *5;
			maxLife += 2; 
			maxCosmo++;
			strength++;
			dexterity++;
			//attack = getAttack();
			//defense = getDefense();
			gp.playSE(8);
			gp.gameState = gp.dialogState;
			gp.ui.currentDialog = "Você estava no nível " + level + "\nVocê ficou mais forte!";
		}
	}
	
	public void selectItem(){
		int itemIndex = gp.ui.getItemIndexOnSlot(gp.ui.playerSlotCol, gp.ui.playerSlotRow);
		if(itemIndex < inventory.size()){
			Entity selectedItem = inventory.get(itemIndex);

			if(selectedItem.type ==  type_sword || selectedItem.type == type_axe){
				currentWeapon = selectedItem;
				attack = getAttack();
				getPlayerAttackImage();
			}
			if(selectedItem.type == type_shield){
				currentShield = selectedItem;
				defense = getDefense();
			}
			if(selectedItem.type == type_light){
				if(currentLight == selectedItem){
					currentLight = null;
				}
				else{
					currentLight = selectedItem;
				}
				lightUpdated= true;
			}
			if(selectedItem.type == type_consumable){
				if(selectedItem.use(this) == true){
					if(selectedItem.amout > 1){
						selectedItem.amout--;
					}
					else{
						inventory.remove(itemIndex);
					}
					
				}				
			}
		}
	}
	public int searchItemInventory(String itemNme){
		int itemIndex = 999;
		for(int i = 0; i < inventory.size(); i++){
			if(inventory.get(i).name.equals(itemNme)){
				itemIndex = i;
				break;
			}
		}
		return itemIndex;
	}
	public boolean canObtainItem(Entity item){
		boolean canObtain = false;

		// CHECK IF STACKABLE
		if(item.stackable == true){
			int index = searchItemInventory(item.name);

			if(index != 999){
				inventory.get(index).amout++;
				canObtain = true;
			}
			else { //New item so need to check vacancy
				if(inventory.size() != maxInventorySize){
					inventory.add(item);
					canObtain = true;
				}
			}
		}
		else { // NOT STACKABLE so check vacancy
			if(inventory.size() != maxInventorySize){
				inventory.add(item);
				canObtain = true;
			}
		}
		return canObtain;
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











