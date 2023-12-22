package objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import principal.GamePanel;
import principal.UtiliyTool;


public class SuperObject {

    public BufferedImage image, image2, image3;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefultX = 0;
    public int solidAreaDefultY = 0;
    UtiliyTool uTool = new UtiliyTool();

    public void draw(Graphics2D g2, GamePanel gp){

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
           worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
           worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
           worldY - gp.tileSize < gp.player.worldY + gp.player.screenY
        ){
         g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

        }
    }
}
