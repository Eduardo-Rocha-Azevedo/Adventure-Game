package entity;

import java.awt.Rectangle;
import java.util.ArrayList;

import objects.OBJ_Door_Iron;
import principal.GamePanel;
import tile_interactive.IT_MetalPlate;
import tile_interactive.InteractiveTile;

public class NPC_BigRock extends Entity{
    public static final String npcName = "Big Rock";
    GamePanel gp;
    public NPC_BigRock(GamePanel gp){
        super(gp);
        this.gp = gp;
        name = npcName;
        direction = "down";
        speed = 4;

        solidArea = new Rectangle();
        solidArea.x = 2;
        solidArea.y = 6;
        solidAreaDefultX = solidArea.x;
        solidAreaDefultY = solidArea.y;
        solidArea.width = 30;
        solidArea.height = 30;

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
    public void setDialog(){
		dialogues[0][0] = "Uma grande pedra";
	}

    public void setAction(){}
    public void update() {}
    public void speak() {
        // Do this character specific stuff
        getFacePlayer();
        startDialogue(this, dialogueSet);

        dialogueSet++;
        if (dialogues[dialogueSet][0] == null) {
            //dialogueSet = 0;  reset to 0
            dialogueSet--; // don't reset
			
        }       
    }

    public void move(String d) {
        this.direction = d;

        checkCollision();
        if(collisioOn == false){
            switch(direction){
                case "up": worldY -= speed;break;
                case "down": worldY += speed;break;
                case "left": worldX -= speed;break;
                case "right": worldX += speed;break;
            }
        }

        detectPlate();
    }

    
    public void detectPlate() {
        ArrayList<InteractiveTile> plateList = new ArrayList<>();
        ArrayList<Entity> rockList = new ArrayList<>();

        // Create a plate list
        for (int i = 0; i < gp.iTile[1].length; i++) {
            if (gp.iTile[gp.currentMap][i] != null &&
                    gp.iTile[gp.currentMap][i].name != null &&
                    gp.iTile[gp.currentMap][i].name.equals(IT_MetalPlate.itName)) {
                plateList.add(gp.iTile[gp.currentMap][i]);
            }
        }

        // Create a rock list
        for (int i = 0; i < gp.npc[1].length; i++) {
            if (gp.npc[gp.currentMap][i] != null && gp.npc[gp.currentMap][i].name.equals(NPC_BigRock.npcName)) {
                rockList.add(gp.npc[gp.currentMap][i]);
            }
        }

        int count = 0;

        // Scan the plate list
        for (InteractiveTile interactiveTile : plateList) {
            int xDistance = Math.abs(worldX - interactiveTile.worldX);
            int yDistance = Math.abs(worldY - interactiveTile.worldY);
            int distance = Math.max(xDistance, yDistance);

            if (distance < 15) {
                if (linkedEntity == null) {
                    linkedEntity = interactiveTile;
                    gp.playSE(3);
                }
            } else {
                if (linkedEntity == interactiveTile) {
                    linkedEntity = null;
                }
            }
        }

        // Scan the rock list
        for (Entity entity : rockList) {
            // Count the rock on the plate
            if (entity.linkedEntity != null) {
                count++;
            }
        }

        // If all the rocks are on the plates , the iron door opens
        if (count == rockList.size()) {
            for (int i = 0; i < gp.obj[1].length; i++) {
                if (gp.obj[gp.currentMap][i] != null && gp.obj[gp.currentMap][i].name.equals(OBJ_Door_Iron.objName)) {
                    gp.obj[gp.currentMap][i] = null;
                    gp.playSE(21);
                }
            }
        }
    }
}
