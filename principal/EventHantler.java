package principal;

public class EventHantler {
    GamePanel gp;
    EventRect eventRect[][];

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;

    public EventHantler(GamePanel gp){
        this.gp = gp;
        eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];

        int col = 0;
        int row = 0;
        while(col < gp.maxWorldCol && row < gp.maxWorldRow){
            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = 23;
            eventRect[col][row].y = 23;
            eventRect[col][row].width = 2;
            eventRect[col][row].height= 2;
            eventRect[col][row].eventRectDefultX = eventRect[col][row].x;
            eventRect[col][row].eventRectDefultY = eventRect[col][row].y;

            col++;
            if(col == gp.maxWorldCol){
                col = 0;
                row++;
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
            if(hit(27,16,"right") == true) {damagePit(27,16,gp.dialogState);}
            //if(hit(23,19,"any") == true) {damagePit(23,19,gp.dialogState);}
            if(hit(23,12,"up") == true) {healingPool(23,12,gp.dialogState);}
        }
       
    }

    public boolean hit(int col, int row, String reqDirection){
        boolean hit = false;
        gp.player.solidArea.x  = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y  = gp.player.worldY + gp.player.solidArea.y;
        eventRect[col][row].x = col * gp.tileSize + eventRect[col][row].x;
        eventRect[col][row].y = row * gp.tileSize + eventRect[col][row].y;

        if(gp.player.solidArea.intersects(eventRect[col][row]) && eventRect[col][row].eventDone == false){
            if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")){
                hit = true;

                previousEventX = gp.player.worldX;
                previousEventY = gp.player.worldY;
            }
        }

        gp.player.solidArea.x  = gp.player.solidAreaDefultX;
        gp.player.solidArea.y  = gp.player.solidAreaDefultY;
        eventRect[col][row].x =  eventRect[col][row].eventRectDefultX;
        eventRect[col][row].y =  eventRect[col][row].eventRectDefultY;

        return hit;
    }

    public void damagePit(int col,int row, int gameState ){
     
        gp.gameState = gameState;
        gp.playSE(6);
        gp.ui.currentDialog = "you fall into a hole";
        gp.player.life--;
        //eventRect[col][row].eventDone = true;
        canTouchEvent = false;
    }

    public void healingPool(int col, int row, int gameState){
        if(gp.keyH.enterPressed == true){
            gp.gameState = gameState;
            gp.player.attackCanceled = true;
            gp.playSE(2);
            gp.ui.currentDialog = "You drink the water.\nYour life is restored.";
            gp.player.life = gp.player.maxLife;
        }
    }

    public void teleport(int col, int row, int gameState){
        gp.gameState = gameState;
        gp.ui.currentDialog = "You are teleported";
        gp.player.worldX = 37 * gp.tileSize;
        gp.player.worldY = 10 * gp.tileSize;
    }
}
