package principal;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

import entity.PlayerDummy;
import objects.OBJ_BlueHeart;
import objects.OBJ_Door_Iron;
import principal.monster.MOM_Skeleton;

public class CutsceneManager {
    GamePanel gp;
    Graphics2D g2;
    public int sceneNum;
    public int scenePhase;

    // Scene Number
    public final int NA = 0;
    public final int skeletonLord = 1;
    public final int ending = 2;
    int counter = 0;
    float alpha = 0;
    int y = 0;
    String endCredit;

    public CutsceneManager(GamePanel gp){
        this.gp = gp;
        endCredit = "Program/Efeitos Sonoros/Arte\n"+
                    "Eduardo Azevedo\n"+
                    "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"+
                    "Agradecimento Especial\n"+
                    "Aos meus amigos e familiares:\n\n\n"+
                    "Macaco bola azul\n"+
                    "Susana\n"+
                    "Bruna Azevedo\n"+
                    "Carlos Estradioto\n"+
                    "\n\n\n\n\n"+
                    "Obrigado por jogar :)\n";

    }

    public void draw(Graphics2D g2){
        this.g2 = g2;
        
        switch(sceneNum){
            case skeletonLord: scene_skeletonLord();break;
            case ending: scene_ending();break;
        }
    }

    public void scene_skeletonLord(){
        if(scenePhase == 0){
            gp.bossBattleOn = true;

            // Shut the iron door
            for(int i = 0; i < gp.obj[1].length; i++){
                if(gp.obj[gp.currentMap][i] != null){
                    gp.obj[gp.currentMap][i] = new OBJ_Door_Iron(gp);
                    gp.obj[gp.currentMap][i].worldX = gp.tileSize*25; 
                    gp.obj[gp.currentMap][i].worldY = gp.tileSize*28;
                    gp.obj[gp.currentMap][i].temp = true;

                    i++;
                    gp.obj[gp.currentMap][i] = new OBJ_Door_Iron(gp);
                    gp.obj[gp.currentMap][i].worldX = gp.tileSize*25; 
                    gp.obj[gp.currentMap][i].worldY = gp.tileSize*15;
                    gp.obj[gp.currentMap][i].temp = true;

                    gp.playSE(21);
                    break;
                }
            }

            // Search a vacancy slot for the dummy
            for(int i = 0; i < gp.npc[1].length; i++){

                if(gp.npc[gp.currentMap][i] == null){
                    gp.npc[gp.currentMap][i] = new PlayerDummy(gp);
                    gp.npc[gp.currentMap][i].worldX = gp.player.worldX; 
                    gp.npc[gp.currentMap][i].worldY = gp.player.worldY;
                    gp.npc[gp.currentMap][i].direction = gp.player.direction;
                    break;
                }
            }

            gp.player.drawing = false;
            scenePhase++;
        }

        if(scenePhase == 1){
            gp.player.worldY -= 2;
            if(gp.player.worldY < gp.tileSize*16){
                scenePhase++;
            }
        }

        if(scenePhase == 2){
            // Search the boss
            for(int i = 0; i < gp.monster[1].length; i++){
                if(gp.monster[gp.currentMap][i] != null && gp.monster[gp.currentMap][i].name == MOM_Skeleton.monName){
                
                    gp.monster[gp.currentMap][i].sleep = false; 
                    gp.ui.npc = gp.monster[gp.currentMap][i];
                    scenePhase++; 
                    break;  
                }
            }
        }
        if(scenePhase == 3){
            // The boos speaks
            gp.ui.drawDialogScreen();

        }

        if(scenePhase == 4){
            // Return to player

            // Search the dummy
            for(int i = 0; i < gp.npc[1].length; i++){
                if(gp.npc[gp.currentMap][i] != null && gp.npc[gp.currentMap][i].name.equals(PlayerDummy.npcName)){
                    //Restore the player position
                    gp.player.worldX = gp.npc[gp.currentMap][i].worldX;
                    gp.player.worldY = gp.npc[gp.currentMap][i].worldY;

                    //Delet the dummy
                    gp.npc[gp.currentMap][i] = null;
                    break;
                }
            }

            //Start drawing the player
            gp.player.drawing = true;

            //Reset
            sceneNum = NA;
            scenePhase = 0;
            gp.gameState = gp.playState;

            // Change the music
            gp.stopMusic();
            gp.playMusic(22);

        }
    }

    public void scene_ending(){
        if(scenePhase == 0){
            gp.stopMusic();
            gp.ui.npc = new OBJ_BlueHeart(gp);
            scenePhase++;
        }
        if(scenePhase == 1){
            // Display dialogues
            gp.ui.drawDialogScreen();
            
        }
        if(scenePhase == 2){
            // Play the fanfare
            gp.playSE(4);
            scenePhase++;
        }
        if(scenePhase == 3){
            // Wait until the sound effect ends
            if(counterReached(300) == true){
                scenePhase++;
            }
        }
        if(scenePhase == 4){
            // The sceen gets darker
            alpha += 0.005f;
            if(alpha > 1f){
                alpha = 1f;
            }
            drawBlackBackground(alpha);

            if(alpha == 1f){
                alpha = 0;
                scenePhase++;
            }
        }
        if(scenePhase == 5){
            drawBlackBackground(1f);
            alpha += 0.005f;
            if(alpha > 1f){
                alpha = 1f;
            }

            String text = "Após a feroz batalha com o Skeleton Lord,\n"+
                "Henry finalmente encontrou o legendário tessouro\n"+
                "Porém, este não é o fim desta jornada.\n"+
                "A Aventura apenas começou";
            drawString(alpha, 38f, 200, text, 70);   

            if(counterReached(700) == true){
                gp.playMusic(12);
                scenePhase++;
            }
        }
        if(scenePhase == 6){
            drawBlackBackground(1f);
            drawString(1f, 120f, gp.screenHeight/2, "Ancient Ruins", 40);

            if(counterReached(480) == true){
                scenePhase++;
            }
        }
        if(scenePhase == 7){
            y = gp.screenHeight/2;
            drawBlackBackground(1f);
            drawString(1f, 38f, y, endCredit,40);

            if(counterReached(480) == true){
                scenePhase++;
            }
        }

        if(scenePhase == 8){
            drawBlackBackground(1f);

            // Scroll the credit
            y--;
            drawString(1f, 38f, y, endCredit,40);
            if(counterReached(1600) == true){
                scenePhase++;
            }
        }

        if(scenePhase == 9){
            // Return to the title screen
            gp.gameState = gp.titleState;
            gp.stopMusic();
            gp.ui.titleScreenState = 0;
            sceneNum = NA;
            scenePhase = 0;
        }
    }

    public boolean counterReached(int target){
        boolean counterReached = false;

        counter++;
        if(counter > target){
            counterReached = true;
            counter = 0;
        }
        return counterReached;
    }

    public void drawBlackBackground(float alpha){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(Color.black);
        g2.fillRect(0,0, gp.screenWith, gp.screenHeight);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }

    public void drawString(float alpha, float fontSize, int y, String text, int lineHeight){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(fontSize));

        for(String line: text.split("\n")){
            int x = gp.ui.getXForCenterText(line);
            g2.drawString(line, x, y);
            y += lineHeight;
        }
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}
