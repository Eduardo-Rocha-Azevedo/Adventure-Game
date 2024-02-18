package entity;

import java.util.Random;

import principal.GamePanel;

public class NPC_BigRock extends Entity{
    public static final String objName = "BigRock";
    public NPC_BigRock(GamePanel gp){
        super(gp);
        this.gp = gp;
        name = objName;
        type = type_npc;
        direction = "down";
        speed = 4;

        solidArea.x = 2;
        solidArea.y = 6;
        solidArea.width = 44;
        solidArea.height = 40;
        solidAreaDefultX = solidArea.x;
        solidAreaDefultY = solidArea.y;

        dialogueSet = -1;

        getImage();
        setDialog();
    }

    	public void getImage(){
	
		up1 = setup("/npc/bigrock",gp.tileSize, gp.tileSize);
		up2 = setup("/npc/bigrock", gp.tileSize, gp.tileSize);
		down1 = setup("/npc/bigrock", gp.tileSize, gp.tileSize);
		down2 = setup("/npc/bigrock", gp.tileSize, gp.tileSize);
		left1 = setup("/npc/bigrock", gp.tileSize, gp.tileSize);
		left2 = setup("/npc/bigrock", gp.tileSize, gp.tileSize);
		right1 = setup("/npc/bigrock", gp.tileSize, gp.tileSize);
		right2 = setup("/npc/bigrock", gp.tileSize, gp.tileSize);
	}
	//Dialog
	public void setDialog(){
		dialogues[0][0] = "Uma grande pedra...";
	
	}



	public void speak() {
        startDialogue(this, dialogueSet);

        dialogueSet++;
        if (dialogues[dialogueSet][0] == null) {
            //dialogueSet = 0;  reset to 0
            dialogueSet--; // don't reset
			
        }
    }
}
