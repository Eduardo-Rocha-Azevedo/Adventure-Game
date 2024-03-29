package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import principal.GamePanel;
import principal.UtiliyTool;



public class Entity {
	public GamePanel gp;

	public int worldX, worldY;

	
	// IMAGES CONFIGS
	public BufferedImage up1,up2, down1, down2, left1, left2, right1, right2;
	public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2,
	guardUp,guardDown,guardLeft,guardRight;
	public BufferedImage image, image2, image3;
	public String direction="down";

	public Rectangle attackArea = new Rectangle(0,0, 0,0);
	public Rectangle solidArea = new Rectangle(0,0, 48,48);
	public int solidAreaDefultX, solidAreaDefultY;

	// COUNTER AND COLLISSION
	public boolean collisioOn = false;
	public int actionLockCounter = 0;
	public boolean invincible = false;
	public int invincibleCounter = 0;
	public int spriteCouter = 0;
	public int spriteNum = 1;
	int dyainCounter = 0;
	public int hpBarCounter = 0;
	public int shotAvailabelCounter = 0;
	int knockBackCounter = 0;
	public Entity attacker;
	public Entity linkedEntity;
	public int guardCounter = 0;
	int offBalanceCounter = 0;
	public int dialogueSet = 0;
	public boolean temp = false;

	//state
	public boolean collision = false;
	public boolean attacking = false;
	public boolean alive = true;
	public boolean dyain = false;
	public boolean hpBarOn = false;
	public boolean onPath = false;
	public boolean knockBack = false;
	public String knockBackDirection;
	public boolean guarding = false;
	public boolean transparent = false;
	public boolean offBalance = false;
	public boolean inRange = false;
	public boolean sleep = false;
	public boolean drawing = true;

	//DIALOG
	public String dialogues[][] = new String[20][20];
	
	public int dialogIndex = 0;

	//CHARACTER STATUS
	public int maxLife;
	public int life;
	public String name;
	public int defaultSpeed;
	public int speed;
	public int level;
	public int strength;
	public int dexterity;
	public int attack;
	public int defense;
	public int exp;
	public int nextLevelExp;
	public int coin;
	public int value;
	public int maxCosmo;
	public int cosmo;
	public Entity currentLight;
	public Entity currentWeapon;
	public Entity currentShield;
	public Projectile projectile;
	public ArrayList <Entity> inventory = new ArrayList<>();
	public final int maxInventorySize = 20;
	public int motion1_duration;
	public int motion2_duration;
	public boolean boss = false;

	// ITEM ATTRIBUTES
	public int attackValue;
	public int defenseValue;
	public Entity loot;
    public boolean opened = false;
	public String description = "";
	public int useCost;
	public int price;
	public int knockBackPower = 0;
	public boolean stackable = false;
	public int amout = 1;
	public int lightRadius = 0;
	

	// TYPE
	public int type; 
	public final int type_player = 0;
	public final int type_npc = 1;
	public final int type_monster = 2;
	public final int type_sword_normal = 3;
	public final int type_axe = 4;
	public final int type_shield = 5;
	public final int type_consumable = 6;
	public final int type_pickOnly = 7;
	public final int type_obstacle = 8;
	public final int type_light = 9;
	public final int type_sword_iron = 10;
	public final int type_pickaxe = 11;

	public Entity(GamePanel gp){
		this.gp = gp;
		solidArea = new Rectangle();
        solidArea.x = 8;
		solidArea.y = 16;
        
        solidAreaDefultX = solidArea.x;
		solidAreaDefultY = solidArea.y;

		solidArea.width = 32;
		solidArea.height = 32;
	}
	public int getLeftX(){

		return worldX + solidArea.x;
	}
	public int getRightX(){

		return worldX + solidArea.x + solidArea.width;
	}
	public int getTopY(){

		return worldY + solidArea.y;
	}
	public int getBottomY(){

		return worldY + solidArea.y + solidArea.height;
	}
	public int getCoL(){
		return (worldX + solidArea.x) / gp.tileSize;
	}
	public int getRoW(){
		return (worldY + solidArea.y) / gp.tileSize;
	}
	public int getCenterX(){
		int centerX = worldX + left1.getWidth()/2;
		return centerX;
	}
	public int getCenterY(){
		int centerY = worldY + up1.getHeight()/2;
		return centerY;
	}
	public int getXDistance(Entity target){
		int xDistance = Math.abs(getCenterX() - target.worldX);
		return xDistance;
	}
	public int getYDistance(Entity target){
		int yDistance = Math.abs(getCenterY() - target.worldY);
		return yDistance;
	}
	public int getTileDistance(Entity target){
		int tileDistance = (getXDistance(target) + getYDistance(target))/ gp.tileSize;
		return tileDistance;
	}
	public int getGoalCol(Entity target){
		int goalCol = (target.worldX + target.solidArea.x)/gp.tileSize;// para seguir o player
		return goalCol;
	}
	public int getGoalRow(Entity target){
		int goalRow = (target.worldY + target.solidArea.y)/gp.tileSize;
		return goalRow;
	}
	public int getScreenX(){
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		return screenX;
	}
	public int getScreenY(){
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		return screenY;
	}
	public void move(String direction){}
	public void setAction(){}
	public void damageReaction(){}
	public void speak(){
		
	}
	public void getFacePlayer(){
		switch (gp.player.direction) {
			case "up": direction = "down"; break;
			case "down": direction = "up"; break;
			case "left": direction = "right"; break;
			case "right": direction = "left"; break;
		}
	}
	public void startDialogue(Entity entity, int setNum){
		gp.gameState = gp.dialogState;
		gp.ui.npc = entity;
		dialogueSet = setNum;
	}
	public void interact(){}
	public boolean use(Entity e){
		return false;
	}
	public void checkDrop(){}
	public void dropItem(Entity droppedItem){
		for(int i = 0; i < gp.obj[1].length; i++){
			if(gp.obj[gp.currentMap][i] == null){
				gp.obj[gp.currentMap][i] = droppedItem;
				gp.obj[gp.currentMap][i].worldX = worldX; // the dead monster's worldX
				gp.obj[gp.currentMap][i].worldY = worldY; // the dead monster's worldY
				break;
			}
		}
	}
	public void checkCollision(){
		collisioOn = false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkEntity(this,gp.npc);
		gp.cChecker.checkEntity(this,gp.monster);
		gp.cChecker.checkEntity(this, gp.iTile);
		boolean contactPlayer = gp.cChecker.checkPlayer(this);
		
		if(this.type == type_monster && contactPlayer == true){
			damagePlayer(attack);
		}
	}
	public void update(){
		if(sleep == false){
			if(knockBack == true){
			checkCollision();

			if(collisioOn == true){
				knockBackCounter = 0;
				knockBack = false;
				speed = defaultSpeed;
			}
			else{
				switch(knockBackDirection){
					case "up":worldY -= speed;
					case "down":worldY += speed;
					case "left":worldX -= speed;
					case "right":worldX += speed;
				}
			}
			knockBackCounter++;
			if(knockBackCounter == 10){
				knockBackCounter = 0;
				knockBack = false;
				speed = defaultSpeed;
			}
		}
		else if(attacking == true){
			attacking();
		}
		else {

		setAction();
		checkCollision();
		//IF COLLISION IS FALSE, PLAYER CAN MOVE
			if(!collisioOn){
				switch(direction){
					case "up":worldY -= speed;break;
					case "down":worldY += speed;break;
					case "left":worldX -= speed;break;
					case "right":worldX += speed;break;
				}
			}
			spriteCouter++;
			if(spriteCouter > 24){
				if(spriteNum == 1){
					spriteNum = 2;
				}
			else if(spriteNum == 2){
				spriteNum = 1;
				}
			spriteCouter = 0;
			}
		}

		if(invincible == true){
			invincibleCounter++;
			if(invincibleCounter > 30){
				invincible = false;
				transparent = false;
				invincibleCounter = 0;
			}
		}
		if(shotAvailabelCounter < 30){
			shotAvailabelCounter++;
		}
		if(offBalance){
			offBalanceCounter++;
			if(offBalanceCounter > 60){
					offBalance = false;
					offBalanceCounter = 0;
					
				}
			}
		}
	}
	public void checkStopChassingOrNot(Entity target, int distance, int rate){
		if(getTileDistance(target) > distance){
			int i = new Random().nextInt(rate);
			if(i == 0){
				onPath = false;
			} 
		}
	}
	public void checkStartChasingOrNOt(Entity target, int distance, int rate){
		if(getTileDistance(target) < distance){
			int i = new Random().nextInt(rate);
			if(i == 0){
				onPath = true;
			} 
		}
	}
	public void checkShootOrNot(int rate, int shotInterval){
 	//Check if it shoots aprojectile
	int i = new Random().nextInt(rate); // pick up a number from  1 to 100

	 if(i == 0 && projectile.alive == false && shotAvailabelCounter == shotInterval){
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
	public void getRandomDirection(int interval){
		actionLockCounter++;
		if(actionLockCounter == interval){
			
			Random random = new Random();
			int i = random.nextInt(100)+1; // pick up a number from  1 to 100

			if(i <= 25){direction = "up";}
			if(i > 25 && i <= 50){direction = "down";}
			if(i > 50 && i <= 75){direction = "left";}
			if(i > 75 && i <= 100){direction = "right";}
			actionLockCounter = 0;
		}
	}
	public void attacking(){
		spriteCouter++;
		
		if(spriteCouter <= motion1_duration){
			spriteNum = 1;
		}
		if(spriteCouter > motion1_duration && spriteCouter <= motion2_duration){
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

			if(type == type_monster){
				if(gp.cChecker.checkPlayer(this) == true){
					damagePlayer(attackValue);
				}
			}
			else{
				//check collision with the update worldX/Y and solidArea
				int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
				gp.player.damageMonster(monsterIndex, this,attack, currentWeapon.knockBackPower);

				int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
				gp.player.damageInteractiveTile(iTileIndex);

				int projectileIndex = gp.cChecker.checkEntity(this, gp.projectile);
				gp.player.damageProjectile(projectileIndex);
			}
	
			//After attack, reset player's worldX/Y and solidArea
			worldX = currentWorldX;
			worldY = currentWorldY;
			solidArea.width = solidAreaWidth;
			solidArea.height = solidAreaHeight;
		}
		if(spriteCouter > motion2_duration){
			spriteNum = 1;
			spriteCouter = 0;
			attacking = false;
		}
	}
	public void checkAttackOrNot(int rate, int straingth, int horizontal){
		boolean targetInRate = false;
		int xDis = getXDistance(gp.player);
		int yDis = getYDistance(gp.player);

		switch(direction){
			case "up":
				if(gp.player.getCenterY()< getCenterY() && yDis < straingth && xDis < horizontal){
					targetInRate = true;
				}
				break;
			case "down":
				if(gp.player.getCenterY() > getCenterY() && yDis < straingth && xDis < horizontal){
					targetInRate = true;
				}
			break;
			case "left":
				if(gp.player.getCenterX()< getCenterX() && xDis < straingth && yDis < horizontal){
					targetInRate = true;
				}
				break;
			case "right":
				if(gp.player.getCenterX() > getCenterX() && xDis < straingth && yDis < horizontal){
					targetInRate = true;
				}
			break;
		}

		if(targetInRate == true){
			//Check if it initiates an attack
			int i = new Random().nextInt(rate);
			if(i == 0){
				attacking = true;
				spriteNum = 1;
				spriteCouter = 0;
				shotAvailabelCounter = 0;
			}
		}
	}
	public void damagePlayer(int attack){
		if(gp.player.invincible == false){

			int damage = attack - gp.player.defense;

			//Get an opposite direction of thid attacker
			String canGuardDirection = getOppositeDirection(direction);

			if(gp.player.guarding == true && gp.player.direction.equals(canGuardDirection)){
				// PARRY
				if(gp.player.guardCounter < 10){
					damage = 0;
					gp.playSE(16);
					setKnockBack(this, gp.player, knockBackPower);
					offBalance = true;
					spriteCouter -= 60;
				}
				else{
					//Normal guard
					damage /= 3;
					gp.playSE(16);
				}
				
			}
			else{
				//Not guarding we can give damage
				gp.playSE(6);
				if(damage < 1){
					damage = 1;
				}
			}
			if(damage != 0){
				gp.player.transparent = true;
				setKnockBack(gp.player, this, knockBackPower);
			}

			gp.player.life -= damage;
			gp.player.invincible = true; 
		}
	}
	public void moveTowardPlayer(int interval){
		actionLockCounter++;
		if(actionLockCounter > interval){
			if(getXDistance(gp.player) > getYDistance(gp.player)){
				if(gp.player.getCenterX() < getCenterX()){
					direction = "left";
				}
				else if(gp.player.getCenterX() > getCenterX()){
					direction = "right";
				}
			}
			else if(getXDistance(gp.player) < getYDistance(gp.player)){
				if(gp.player.getCenterY() < getCenterY()){
					direction = "up";
				}
				else {
					direction = "down";
				}
			}
			actionLockCounter = 0;
		}
	}
	public String getOppositeDirection(String direction){
		String oppositeDirection = "";
		switch(direction){
			case "up": oppositeDirection = "down"; break;
			case "down": oppositeDirection = "up"; break;
			case "left": oppositeDirection = "right"; break;
			case "right": oppositeDirection = "left"; break;
		}
		return direction;
	}
	public void setKnockBack(Entity target, Entity attacker, int knockBackPower){
		this.attacker = attacker;
		target.knockBackDirection = attacker.direction;
		target.speed += knockBackPower;
		target.knockBack = true;
	}

	public boolean inCamera(){
		boolean inCamera = false;

		if(worldX + gp.tileSize *5 > gp.player.worldX - gp.player.screenX && 
			worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && 
			worldY + gp.tileSize *5> gp.player.worldY - gp.player.screenY && 
			worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){

			inCamera = true;
		}
		return inCamera;
	}
	
	public void draw(Graphics2D g2){
		
		BufferedImage image = null;
		
		int tempScreenX = getScreenX();
		int tempScreenY = getScreenY();

        if(inCamera() == true){

			switch(direction){
				case "up": 
				if(attacking == false) {
					if(spriteNum == 1) { image = up1;}
					if(spriteNum == 2) { image = up2;}
				}
				if(attacking == true)  {
					tempScreenY = getScreenY() - up1.getHeight();
					if(spriteNum == 1) {image = attackUp1;}
					if(spriteNum == 2) {image = attackUp2;}
				}
				if(guarding == true){
					image = guardUp;
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
					if(guarding == true){
						image = guardDown;
					}
					break;
 				
			case "left":
					if(attacking == false){

						if(spriteNum == 1) {image = left1;}
						if(spriteNum == 2) {image = left2;}
					}
					if(attacking == true){
						tempScreenX = getScreenX() - left1.getWidth();
						if(spriteNum == 1) {image = attackLeft1;}
						if(spriteNum == 2) {image = attackLeft2;}
					}
					if(guarding == true){
						image = guardLeft;
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
					if(guarding == true){
						image = guardRight;
					}
					break;	
		}
		
		if(invincible == true){
			hpBarOn = true;
			hpBarCounter = 0;
			changeAlpha(g2, 0.4f);
		}

		if(dyain == true){
			dyainAnimation(g2);
		}

		if(drawing == true){
			g2.drawImage(image, tempScreenX, tempScreenY, null);
		}
		changeAlpha(g2, 1f);
        }
		
	}
	
	
	//animacao de dano
	public void dyainAnimation(Graphics2D g2){
		dyainCounter++;

		int i= 5;

		if(dyainCounter <= i)					  {changeAlpha(g2, 0f);}
		if(dyainCounter > i    && dyainCounter <= i *2){changeAlpha(g2, 1f);}
		if(dyainCounter > i *2 && dyainCounter <= i *3){changeAlpha(g2, 0f);}	
		if(dyainCounter > i *3 && dyainCounter <= i *4){changeAlpha(g2, 1f);}
		if(dyainCounter > i *4 && dyainCounter <= i *5){changeAlpha(g2, 0f);}
		if(dyainCounter > i *5   && dyainCounter <= i *6){changeAlpha(g2, 1f);}
		if(dyainCounter > i *6   && dyainCounter <= i *7){changeAlpha(g2, 0f);}
		if(dyainCounter > i *7   && dyainCounter <= i *8){changeAlpha(g2, 1f);}	

		if(dyainCounter > i*8){
			alive = false;
			
		}
		
	}

	public void changeAlpha(Graphics2D g2, float alphaValue){
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));

	}

	//carrega a img
	public BufferedImage setup(String imagePath, int width, int height)
    {
        UtiliyTool uTool = new UtiliyTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaleImage(image,width,height);   //it scales to tilesize , will fix for player attack(16px x 32px) by adding width and height
        }
        catch (IOException e) {
        e.printStackTrace();
        }
        return image;
    }

	public void setLoot(Entity loot){}
	public void resetCounter(){
		actionLockCounter = 0;
		invincibleCounter = 0;
		spriteCouter = 0;
		dyainCounter = 0;
		hpBarCounter = 0;
		shotAvailabelCounter = 0;
		knockBackCounter = 0;
		guardCounter = 0;
		offBalanceCounter = 0;
	}
	// Particles configs
	public Color getParticlesColor(){
        Color color = null;
        return color;
    }
    public int getParticlesSize(){
        int size = 0;
        return size;
    }
	public int getParticlesSpeed(){
        int speed = 0;
        return speed;
    }
    public int getParticlesMaxLife(){
        int maxLife = 0;
        return maxLife;
    }
	public void generateParticle(Entity generator, Entity target){
		Color color = generator.getParticlesColor();
		int size = generator.getParticlesSize();
		int speed = generator.getParticlesSpeed();
		int maxLife = generator.getParticlesMaxLife();

		Particle p1 = new Particle(gp, target, color, size, speed, maxLife, -2, -1);
		Particle p2 = new Particle(gp, target, color, size, speed, maxLife, 2, -1);
		Particle p3 = new Particle(gp, target, color, size, speed, maxLife, -2, 1);
		Particle p4 = new Particle(gp, target, color, size, speed, maxLife, 2, 1);
		gp.particleList.add(p1);
		gp.particleList.add(p2);
		gp.particleList.add(p3);
		gp.particleList.add(p4);
	}
	public void searchPath(int goalCol , int goalRow){
		int startCol = (worldX + solidArea.x) / gp.tileSize;
        int startRow = (worldY + solidArea.y) / gp.tileSize;

        gp.pFinder.setNodes(startCol, startRow, goalCol, goalRow);

        if (gp.pFinder.search()) {
            // Based on the current NPC's position, find out the relative direction of
            // the next node

            // Next worldX/Y
            int nextX = gp.pFinder.pathList.get(0).col * gp.tileSize;
            int nextY = gp.pFinder.pathList.get(0).row * gp.tileSize;

            // Entity's solidArea position
            int enLeftX = worldX + solidArea.x;
            int enRightX = worldX + solidArea.x + solidArea.width;
            int enTopY = worldY + solidArea.y;
            int enBottomY = worldY + solidArea.y + solidArea.height;

            if (enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
                direction = "up";
            } else if (enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
                direction = "down";
            } else if (enTopY >= nextY && enBottomY < nextY + gp.tileSize) {
                // left or right
                if (enLeftX > nextX) {
                    direction = "left";
                }
                if (enLeftX < nextX) {
                    direction = "right";
                }
            } else if (enTopY > nextY && enLeftX > nextX) {
                // up or left
                direction = "up";
                checkCollision();
                if (collisioOn) {
                    direction = "left";
                }
            } else if (enTopY > nextY && enLeftX < nextX) {
                // up or right
                direction = "up";
                checkCollision();
                if (collisioOn) {
                    direction = "right";
                }
            } else if (enBottomY < nextY && enLeftX > nextX) {
                // down or left
                direction = "down";
                checkCollision();
                if (collisioOn) {
                    direction = "left";
                }
            } else if (enBottomY < nextY && enLeftX < nextX) {
                // down or right
                direction = "down";
                checkCollision();
                if (collisioOn) {
                    direction = "right";
                }
            }

			// If reaches the goal, stop the search

			
/* 	int nextCol = gp.pFinder.pathList.get(0).col;
		int nextRow = gp.pFinder.pathList.get(0).row;
		if(nextCol == goalCol && nextRow == goalRow){
			onPath = false;
			}*/	
		}
	}

	public int getDetected(Entity user, Entity target[][], String targetName){
		int index = 999;

		//Check the surrounding object
		int nextWorldX = user.getLeftX();
		int nextWorldY = user.getTopY();

		switch(user.direction){
			case "up": nextWorldY = user.getTopY()-gp.player.speed; break;
			case "down": nextWorldY = user.getBottomY()+gp.player.speed; break;
			case "left": nextWorldX = user.getLeftX()-gp.player.speed; break;
			case "right": nextWorldX = user.getRightX()+gp.player.speed; break;
		}
		int col = nextWorldX / gp.tileSize;
		int row = nextWorldY / gp.tileSize;

		for(int i = 0; i < target[1].length; i++){
			if(target[gp.currentMap][i] != null){
				if(target[gp.currentMap][i].getCoL() == col &&
					target[gp.currentMap][i].getRoW() == row && 
					target[gp.currentMap][i].name.equals(targetName)){

					index = i;
					break;
				}
			}
		}
		return index;	
	}
}

