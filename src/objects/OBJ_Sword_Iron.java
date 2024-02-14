package objects;

import entity.Entity;
import principal.GamePanel;

public class OBJ_Sword_Iron extends Entity{
     GamePanel gp;
    public OBJ_Sword_Iron(GamePanel gp){
        super(gp);
        type = type_sword_iron;
        name = "Espada de Ferro";
        down1 = setup("/objects/sword_iron", gp.tileSize, gp.tileSize);
        attackValue = 4;
        attackArea.width = 36;
        attackArea.height = 36;
        description  ="[Espada de Ferro]\nUma espada poderosa\norigem desconhecida."; 
        price = 500; 
        knockBackPower = 6;
        motion1_duration = 5;
        motion2_duration = 20;
    }
}
