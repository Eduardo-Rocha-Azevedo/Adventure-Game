package objects;

import java.io.IOException;
import javax.imageio.ImageIO;
import principal.GamePanel;
import principal.UtiliyTool;

public class OBJ_Boots extends SuperObject {

    GamePanel gp;
    UtiliyTool uTool = new UtiliyTool();
    
     public OBJ_Boots(GamePanel gp){
        name = "Boots";
        this.gp = gp;
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        }catch(IOException e){
            System.out.println("Erro ao carregar imagem da chave");
        }

    }
}
