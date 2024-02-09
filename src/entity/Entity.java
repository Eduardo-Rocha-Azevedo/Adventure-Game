package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import principal.GamePanel;
import principal.UtiliyTool;



public class Entity {
	public GamePanel gp;

	public int worldX, worldY;

	
	// IMAGES CONFIGS
	public BufferedImage up1,up2, down1, down2, left1, left2, right1, right2;
	public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
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
	int hpBarCounter = 0;
	public int shotAvailabelCounter = 0;
	int knockBackCounter = 0;

	//state
	public boolean collision = false;
	boolean attacking = false;
	public boolean alive = true;
	public boolean dyain = false;
	boolean hpBarOn = false;
	public boolean onPath = false;
	public boolean knockBack = false;
	//DIALOG
	String dialogues[] = new String[20];
	int dialogIndex = 0;

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

	// ITEM ATTRIBUTES
	public int attackValue;
	public int defenseValue;
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
	public final int type_sword = 3;
	public final int type_axe = 4;
	public final int type_shield = 5;
	public final int type_consumable = 6;
	public final int type_pickOnly = 7;
	public final int type_obstacle = 8;
	public final int type_light = 9;

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
	public void setAction(){}
	public void damageReaction(){}
	public void speak(){
		if(dialogues[dialogIndex] == null){
			dialogIndex = 0;
		}
		gp.ui.currentDialog = dialogues[dialogIndex];
		dialogIndex++;
	}
	public void interact(){}
	public boolean use(Entity e){return false;}
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
		if(knockBack == true){
			checkCollision();
			if(collisioOn == true){
				knockBackCounter = 0;
				knockBack = false;
				speed = defaultSpeed;
			}
			else if(collisioOn == false){
				switch(gp.player.direction){
					case "up":worldY -= speed;break;
					case "down":worldY += speed;break;
					case "left":worldX -= speed;break;
					case "right":worldX += speed;break;
				}
			}
			knockBackCounter++;
			if(knockBackCounter == 10){
				knockBackCounter = 0;
				knockBack = false;
				speed = defaultSpeed;
			}
		}
		else {

		setAction();
		checkCollision();
		//IF COLLISION IS FALSE, PLAYER CAN MOVE
			if(collisioOn == false){
				switch(direction){
					case "up":worldY -= speed;break;
					case "down":worldY += speed;break;
					case "left":worldX -= speed;break;
					case "right":worldX += speed;break;
				}
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

		if(invincible == true){
			invincibleCounter++;
			if(invincibleCounter > 30){
				invincible = false;
				invincibleCounter = 0;
			}
		}
		if(shotAvailabelCounter < 30){
			shotAvailabelCounter++;
		}
	}
	
	public void damagePlayer(int attack){
		if(gp.player.invincible == false){
			//we can give damage
			gp.playSE(6);
			int damage = attack - gp.player.defense;

			if(damage < 0){
				damage = 0;
			}
			gp.player.life -= damage;
			gp.player.invincible = true; 
		}
	}
	
	public void draw(Graphics2D g2){
		
		BufferedImage image = null;
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
		int tempScreenX = screenX;
		int tempScreenY = screenY;
        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
			worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && 
			worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && 
			worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){

	
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
		// Monster HP bar
		if(type == type_monster && hpBarOn == true){

			double oneScale = (double)gp.tileSize /maxLife;
			double hpBarValue = oneScale * life;

			g2.setColor(new Color(35,35,35));
			g2.fillRect(screenX - 2, screenY - 2, gp.tileSize + 4, 12);

			g2.setColor(new Color(255,0,30));
			g2.fillRect(screenX, screenY - 1, (int)hpBarValue, 10);

			hpBarCounter++;

			if(hpBarCounter > 600){
				hpBarCounter = 0;
				hpBarOn = false;	
			}
		}
		

		if(invincible == true){
			hpBarOn = true;
			hpBarCounter = 0;
			changeAlpha(g2, 0.4f);
		}

		if(dyain == true){
			dyainAnimation(g2);
		}

        g2.drawImage(image, tempScreenX, tempScreenY, null);
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
			case "up": nextWorldY = user.getTopY()-1; break;
			case "down": nextWorldY = user.getBottomY()+1; break;
			case "left": nextWorldX = user.getLeftX()-1; break;
			case "right": nextWorldX = user.getRightX()+1; break;
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

