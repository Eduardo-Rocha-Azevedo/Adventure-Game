package principal;

import entity.Entity;
import java.lang.Math;
public class EventHantler {
    GamePanel gp;
    EventRect eventRect[][][];

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;
    int tempMap, tempCol, tempRow;

    public EventHantler(GamePanel gp){
        this.gp = gp;
        eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        int map = 0;
        int col = 0;
        int row = 0;
        while(map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow){
            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x = 23;
            eventRect[map][col][row].y = 23;
            eventRect[map][col][row].width = 2;
            eventRect[map][col][row].height= 2;
            eventRect[map][col][row].eventRectDefultX = eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefultY = eventRect[map][col][row].y;

            col++;
            if(col == gp.maxWorldCol){
                col = 0;
                row++;

                if(row == gp.maxWorldRow){
                    row = 0;
                    map++;
                }
            }
        }
    }

    public void checkEvent(){
        //Check if the player is more than 1 tile away from the previous event
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance , yDistance);

        if(distance > gp.tileSize){
            canTouchEvent = true;
        }

        if(canTouchEvent == true){
            if(hit(0,27,16,"right") == true) {damagePit(gp.dialogState);}
            //else if(hit(0,23,12,"up") == true) {healingPool(gp.dialogState);}

            //Merchant MAP
            else if(hit(0, 10, 39,"any")== true) {teleport(1, 10,40);}
            else if(hit(1, 10, 40,"any")== true) {teleport(0, 10,39);}
            else if(hit(1,10,36,"any") == true){speak(gp.npc[1][0]);}

            //Doungeon MAP
            else if(hit(0, 10, 8, "any")== true) {teleport(2, 8, 41);}
            else if(hit(2,7,41,"any") == true){teleport(0, 10, 8);}
           
        }
       
    }

    public boolean hit(int map,int col, int row, String reqDirection){
        boolean hit = false;
        if(map == gp.currentMap){
             gp.player.solidArea.x  = gp.player.worldX + gp.player.solidArea.x;
             gp.player.solidArea.y  = gp.player.worldY + gp.player.solidArea.y;
             eventRect[map][col][row].x = col * gp.tileSize + eventRect[map][col][row].x;
             eventRect[map][col][row].y = row * gp.tileSize + eventRect[map][col][row].y;

             if(gp.player.solidArea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone == false){
                 if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")){
                     hit = true;

                     previousEventX = gp.player.worldX;
                     previousEventY = gp.player.worldY;
                }
            }

        gp.player.solidArea.x  = gp.player.solidAreaDefultX;
        gp.player.solidArea.y  = gp.player.solidAreaDefultY;
        eventRect[map][col][row].x =  eventRect[map][col][row].eventRectDefultX;
        eventRect[map][col][row].y =  eventRect[map][col][row].eventRectDefultY;
        }
       

        return hit;
    }

    public void damagePit(int gameState ){
     
        gp.gameState = gameState;
        gp.playSE(6);
        gp.ui.currentDialog = "você caiu em um buraco";
        gp.player.life--;
        //eventRect[col][row].eventDone = true;
        canTouchEvent = false;
    }

    public void healingPool(int gameState){
        if(gp.keyH.enterPressed == true){
            gp.gameState = gameState;
            
            gp.playSE(2);
            gp.ui.currentDialog = "Você bebeu água.\nSua vida foi restaurada e seu cosmos.";
            gp.player.life = gp.player.maxLife;
            gp.player.cosmo = gp.player.maxCosmo;
            gp.aSetter.setMonster();
            gp.player.attackCanceled = true;
        }
    }

    public void teleport(int map, int col, int row){
        gp.gameState = gp.transitionState;
        gp.currentMap = map;
        tempMap = map;
        tempCol = col;
        tempRow = row;
   
        canTouchEvent = false;
        gp.playSE(14);
        gp.aSetter.setMonster();
    }

    public void speak(Entity e){
       if(gp.keyH.enterPressed == true){
           gp.gameState = gp.dialogState;
           gp.player.attackCanceled = true;
           e.speak();
       }
    }

}
