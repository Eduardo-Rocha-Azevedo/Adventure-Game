package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

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

	public Rectangle attackArea = new Rectangle(0,0, 48,48);
	public Rectangle solidArea = new Rectangle(0,0, 0,0);
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


	//state
	public boolean collision = false;
	boolean attacking = false;
	public boolean alive = true;
	public boolean dyain = false;
	boolean hpBarOn = false;
	//DIALOG
	String dialogues[] = new String[20];
	int dialogIndex = 0;

	//CHARACTER STATUS
	public int maxLife;
	public int life;
	public int type; //0 = player, 1 = npc, 2 = monster
	public String name;
	public int speed;
	public int level;
	public int strength;
	public int dexterity;
	public int attack;
	public int defense;
	public int exp;
	public int nextLevelExp;
	public int coin;
	public Entity currentWeapon;
	public Entity currentShield;

	// ITEM ATTRIBUTES
	public int attackValue;
	public int defenseValue;

	public Entity(GamePanel gp){
		this.gp = gp;
	}

	public void setAction(){}

	public void damageReaction(){}

	public void speak(){}

	public void update(){
		setAction();

		collisioOn = false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkEntity(this,gp.npc);
		gp.cChecker.checkEntity(this,gp.monster);
		boolean contactPlayer = gp.cChecker.checkPlayer(this);
		
		if(this.type == 2 && contactPlayer == true){
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

		//IF COLLISION IS FALSE, PLAYER CAN MOVE
		if(collisioOn == false){
			switch(direction){
				case "up":worldY -= speed;break;
				case "down":worldY += speed;break;
				case "left":worldX -= speed;break;
				case "right":worldX += speed;break;
			}
		}
		
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

		if(invincible == true){
			invincibleCounter++;
			if(invincibleCounter > 40){
				invincible = false;
				invincibleCounter = 0;
			}
		}
	}

	public void draw(Graphics2D g2){
		
		BufferedImage image = null;
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
			worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && 
			worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && 
			worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){

	
			switch(direction){
			case "up":
				if(spriteNum == 1){image = up1;}
				if(spriteNum == 2){image = up2;}
				break;
			case "down":
				if(spriteNum == 1){image = down1;}	
				if(spriteNum == 2){image = down2;}
				break;
			case "left":
				if(spriteNum == 1){image = left1;}		
				if(spriteNum == 2){image = left2;}
				break;
			case "right":
				if(spriteNum == 1){image = right1;}
				if(spriteNum == 2){	image = right2;}
			break;
					
		}
		// Monster HP bar
		if(type == 2 && hpBarOn == true){

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

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
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
			dyain = false;
		}
		
	}

	public void changeAlpha(Graphics2D g2, float alphaValue){
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));

	}

	//carrega a img
	public BufferedImage setup(String imagePath, int width, int height){	
		UtiliyTool uTool = new UtiliyTool();
		BufferedImage image = null;
		try{
			image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
			image = uTool.scaleImage(image, width, height);
		}catch(IOException e){
			e.printStackTrace();
		}
		return image;
	}
}
