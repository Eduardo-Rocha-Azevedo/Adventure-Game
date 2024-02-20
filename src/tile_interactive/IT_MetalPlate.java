package tile_interactive;

import principal.GamePanel;

public class IT_MetalPlate  extends InteractiveTile{
    public static final String itName = "Metal Plate";
    public IT_MetalPlate(GamePanel gp, int col, int row){
        super(gp, col, row);
        this.gp = gp;
        this.worldX = gp.tileSize*col;
        this.worldY = gp.tileSize*row;
        name = itName;
        life = 3;
        down1 = setup("/tiles_interatives/metalplate",gp.tileSize,gp.tileSize);
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 0;
        solidArea.height = 0;
        solidAreaDefultX = solidArea.x;
        solidAreaDefultY = solidArea.y;
        destructible = true;  
         
    }
}
