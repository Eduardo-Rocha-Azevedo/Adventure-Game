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
        
        setup(0, "000", false);
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
        setup(52, "089", true);
        setup(53, "090", true);
        setup(54, "091", false);
        setup(55, "092", true);
        setup(56, "093", true);
        setup(57, "094", true);
        setup(58, "095", true);
        setup(59, "096", true);
        setup(60, "097", true);
        setup(61, "098", true);
        setup(62, "099", true);
        setup(63, "100", true);
        setup(64, "101", true);
        setup(65, "102", true);
        setup(66, "103", true);
        setup(67, "104", true);
        setup(68, "105", true);
        setup(69, "106", true);
        setup(70, "107", true);
        setup(71, "108", true);
        setup(72, "109", true);
        setup(73, "110", true);
        setup(74, "111", true);
        setup(75, "112", false);
        setup(76, "113", false);
        setup(77, "114", false);
        setup(78, "115", false);
        setup(79, "116", false);
        setup(80, "117", false);
        setup(81, "118", false);
        setup(82, "119", false);
        setup(83, "120", false);
        setup(84, "121", false);
        setup(85, "122", false);
        setup(86, "123", false);
        setup(87, "124", false);
        setup(88, "125", false);
        setup(89, "126", false);
        setup(90, "127", false);
        setup(91, "128", false);
        setup(92, "129", true);
        setup(93, "130", false);
        setup(94, "131", true);
        setup(95, "132", true);
        setup(96, "133", true);
        setup(97, "134", true);
        setup(98, "135", true);
        setup(99, "136", true);
        setup(100, "137", false);
        setup(101, "138", true);
        setup(102, "139", true);
        setup(103, "140", true);
        setup(104, "141", true);
        setup(105, "142", true);
        setup(106, "143", true);
        setup(107, "144", true);
        setup(108, "145", true);
        setup(109, "146", true);
        setup(110, "147", true);
        setup(111, "148", false);
        setup(112, "149", false);
        setup(113, "150", false);
        setup(114, "151", false);
        setup(115, "152", false);
        setup(116, "153", false);
        setup(117, "154", false);
        setup(118, "155", false);
        setup(119, "156", false);
        setup(120, "157", true);
        setup(121, "158", true);
        setup(122, "159", true);
        setup(123, "160", true);
        setup(124, "161", true);
        setup(125, "162", true);
        setup(126, "163", true);
        setup(127, "164", true);
        setup(128, "165", true);
        setup(129, "166", true);
        setup(130, "167", true);
        setup(131, "168", true);
        setup(132, "169", true);
        setup(133, "170", true);
        setup(134, "171", true);
        setup(135, "172", true);
        setup(136, "173", true);
        

        

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
