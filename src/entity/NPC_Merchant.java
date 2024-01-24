package entity;

import java.awt.Rectangle;

import objects.OBJ_Axe;
import objects.OBJ_Key;
import objects.OBJ_Potion_Red;
import objects.OBJ_Shield_Blue;
import objects.OBJ_Shield_Wood;
import objects.OBJ_Sword_Normal;
import principal.GamePanel;

public class NPC_Merchant extends Entity{
    public NPC_Merchant(GamePanel gp){
        super(gp);
        direction = "down";
        speed = 1;

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;

        solidAreaDefultX = 8;
        solidAreaDefultY = 16;

        getImage();
        setDialogue();
        setItem();
    }

    public void getImage(){
	
		up1 = setup("/npc/merchant_down_1",gp.tileSize, gp.tileSize);
		up2 = setup("/npc/merchant_down_2", gp.tileSize, gp.tileSize);
		down1 = setup("/npc/merchant_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/npc/merchant_down_2", gp.tileSize, gp.tileSize);
		left1 = setup("/npc/merchant_down_1", gp.tileSize, gp.tileSize);
		left2 = setup("/npc/merchant_down_2", gp.tileSize, gp.tileSize);
		right1 = setup("/npc/merchant_down_1", gp.tileSize, gp.tileSize);
		right2 = setup("/npc/merchant_down_2", gp.tileSize, gp.tileSize);
	}
	//Dialog
	public void setDialogue(){
		dialogues[0] = "Então você me encontrou!\nEu tenho algumas mercadorias.\nVocê quer negociar?";
		
	}
    
    public void setItem(){
        inventory.add(new OBJ_Potion_Red(gp));
        inventory.add(new OBJ_Key(gp));
        inventory.add(new OBJ_Sword_Normal(gp));
        inventory.add(new OBJ_Axe(gp));
        inventory.add(new OBJ_Shield_Wood(gp));
        inventory.add(new OBJ_Shield_Blue(gp));
    }

    public void speak(){
		super.speak();
        gp.gameState = gp.tradeState;
        gp.ui.npc = this;
	}
}
