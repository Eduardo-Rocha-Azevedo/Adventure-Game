package tile;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import principal.GamePanel;
import principal.UtiliyTool;

public class TileManeger {
    GamePanel gp;
    UtiliyTool uTool = new UtiliyTool();
    public Tile[] tile;
    public int mapTileNum[][][];
    boolean drawPath = true;

    public TileManeger(GamePanel gp){
        this.gp = gp; 
        tile = new Tile[180];
        mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/maps/mapV2.txt",0);
        loadMap("/maps/interior01.txt", 1);
        loadMap("/maps/dangeon1.txt", 2);
    }

    public void getTileImage(){
        
        setup(0, "000", true);
        setup(1, "001", false);
        setup(2, "002", false);
        setup(3, "003", false);
        setup(4, "004", false);
        setup(5, "005", false);
        setup(6, "006", false);
        setup(7, "007", false);
        setup(8, "008", false);
        setup(9, "009", false);
        setup(10, "010", false);
        setup(11, "011", false);
        setup(12, "012", false);
        setup(13, "013", false);
        setup(14, "014", false);
        setup(15, "015", false);
        setup(16, "016", true);
        setup(17, "017", false);
        setup(18, "018", false);
        setup(19, "019", false);
        setup(20, "020", true);
        setup(21, "021", true);
        setup(22, "022", true);
        setup(23, "023", true);
        setup(24, "024", true);
        setup(25, "025", true);
        setup(26, "026", true);
        setup(27, "027", true);
        setup(28, "028", true);
        setup(29, "029", true);
        setup(30, "030", true);
        setup(31, "031", true);
        setup(32, "032", true);
        setup(33, "033", false);
        setup(34, "034", false);
        setup(35, "035", true);
        setup(36, "036", false);
        setup(37, "037", false);
        setup(38, "038", false);
        setup(39, "039", false);
        setup(40, "040", true);
        setup(41, "041", true);
        setup(42, "042", true);
        setup(43, "043", true);
        setup(44, "044", true);
        setup(45, "045", true);
        setup(46, "046", false);
        setup(47, "047", true);
        setup(48, "048", true);
        setup(49, "049", true);
        setup(50, "050", true);
        setup(51, "051", true);
        setup(52, "052", true);
        setup(53, "053", true);
        setup(54, "054", false);
        setup(55, "055", true);
        setup(56, "056", true);
        setup(57, "057", true);
        setup(58, "058", true);
        setup(59, "059", true);
        setup(60, "060", true);
        setup(61, "061", true);
        setup(62, "062", true);
        setup(63, "063", true);
        setup(64, "064", true);
        setup(65, "065", true);
        setup(66, "066", true);
        setup(67, "067", true);
        setup(68, "068", true);
        setup(69, "069", true);
        setup(70, "070", true);
        setup(71, "071", true);
        setup(72, "072", true);
        setup(73, "073", true);
        setup(74, "074", true);
        setup(75, "075", false);
        setup(76, "076", false);
        setup(77, "077", false);
        setup(78, "078", false);
        setup(79, "079", false);
        setup(80, "080", false);
        setup(81, "081", false);
        setup(82, "082", false);
        setup(83, "083", false);
        setup(84, "084", false);
        setup(85, "085", false);
        setup(86, "086", false);
        setup(87, "087", false);
        setup(88, "088", false);
        setup(89, "089", false);
        setup(90, "090", false);
        setup(91, "091", false);
        setup(92, "092", true);
        setup(93, "093", false);
        setup(94, "094", true);
        setup(95, "095", true);
        setup(96, "096", true);
        setup(97, "097", true);
        setup(98, "098", true);
        setup(99, "099", true);
        setup(100, "100", false);
        setup(101, "101", false);
        setup(102, "102", true);
        setup(103, "103", true);
        setup(104, "104", true);
        setup(105, "105", true);
        setup(106, "106", true);
        setup(107, "107", true);
        setup(108, "108", true);
        setup(109, "109", true);
        setup(110, "110", true);
        setup(111, "111", false);
        setup(112, "112", false);
        setup(113, "113", false);
        setup(114, "114", false);
        setup(115, "115", false);
        setup(116, "116", false);
        setup(117, "117", false);
        setup(118, "118", false);
        setup(119, "119", false);
        setup(120, "120", true);
        setup(121, "121", true);
        setup(122, "122", true);
        setup(123, "123", true);
        setup(124, "124", true);
        setup(125, "125", true);
        setup(126, "126", true);
        setup(127, "127", true);
        setup(128, "128", true);
        setup(129, "129", true);
        setup(130, "130", true);
        setup(131, "131", true);
        setup(132, "132", true);
        setup(133, "133", true);
        setup(134, "134", true);
        setup(135, "135", true);
        setup(136, "136", true);
        setup(137, "137", false);
    }

    public void setup(int index, String imageName, boolean collision ){
        UtiliyTool uTool = new UtiliyTool();
        try{
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+ imageName +".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath, int map){
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow){
              String line = br.readLine();

              while (col < gp.maxWorldCol) {
                String numbers[] = line.split(" ");

                int num = Integer.parseInt(numbers[col]);

                mapTileNum[map][col][row] = num;
                col++;
              }
              if(col == gp.maxWorldCol){
                col = 0;
                row++;
              }
            }
         br.close();

        } catch (Exception e) {
          
        }
    }

    public void draw(Graphics2D g2){
       int worldCol = 0;
       int worldRow = 0;
      
       
       while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){

            int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow]; 
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
               worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
               worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
               worldY - gp.tileSize < gp.player.worldY + gp.player.screenY
            ){
             g2.drawImage(tile[tileNum].image, screenX, screenY, null);

            }
            worldCol++;
        
            if(worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }
       }
       if(drawPath){
        g2.setColor(new Color(255,0,0,70));
        for(int i = 0; i < gp.pFinder.pathList.size(); i++){
            int worldX = gp.pFinder.pathList.get(i).col *gp.tileSize;
            int worldY = gp.pFinder.pathList.get(i).row *gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            //g2.fillRect(screenX, screenY, gp.tileSize, gp.tileSize);
        }
       }
    }
}
