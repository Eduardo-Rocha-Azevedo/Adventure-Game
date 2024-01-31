package tile_interactive;

import principal.GamePanel;

public class IT_Trunk  extends InteractiveTile {
    public IT_Trunk(GamePanel gp, int col, int row){
        super(gp,col, row);
        this.worldX = gp.tileSize*col;
        this.worldY = gp.tileSize*row;

        down1 = setup("/tiles_interatives/trunk",gp.tileSize,gp.tileSize);
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 0;
        solidArea.height = 0;
        solidAreaDefultX = solidArea.x;
        solidAreaDefultY = solidArea.y;
          
    }
}
