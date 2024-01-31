package principal;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
//import java.text.DecimalFormat;
import java.util.ArrayList;

import entity.Entity;
import objects.OBJ_Coin_gold;
import objects.OBJ_CosmoCrystal;
import objects.OBJ_Heart;


public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font maruMonica;
    //player images ui
    BufferedImage heart_full, heart_half, heart_blank, cosmo_full, cosmo_blank, coin;
    public boolean gameFinished = false;

    //inventory
    public int playerSlotCol = 0;
    public int playerSlotRow = 0;
    public int npcSlotCol = 0;
    public int npcSlotRow = 0;

    //mensage info
    public boolean messageOn = false;
    public String currentDialog = "";
    public int commandNum = 0;
    public int titleScreenState = 0;
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();

    //OPTION STATE
    int subState = 0;

    //counters
    int counter = 0;
    public Entity npc;
   
    public UI(GamePanel gp){
        this.gp = gp;

        InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
        try {
			maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}

        // CREATE HUD OBJECTS   
        Entity heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;
        Entity cosmo = new OBJ_CosmoCrystal(gp);
        cosmo_full = cosmo.image;
        cosmo_blank = cosmo.image2;
        Entity goldCoin = new OBJ_Coin_gold(gp);
        coin = goldCoin.down1;
    }

    public void addMessage(String text){
        message.add(text);
        messageCounter.add(0);
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;

        g2.setFont(maruMonica);  
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.white);
        
        //TITLE SCREEN
        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }
        //PLAY STATE
        if(gp.gameState == gp.playState){
            //HEARTS
            drawPlayerLife();
            drawMessage();
        }
        //PAUSE STATE
        else if(gp.gameState == gp.pauseState){
             drawPlayerLife();
             drawPauseScreen();
           
        }
        //DIALOG STATE
        else if(gp.gameState == gp.dialogState){
            drawPlayerLife(); 
            drawDialogScreen();
        }

        //CHARACTER STATE
        else if(gp.gameState == gp.characterState){
            drawCharacterScreen();
            drawInventory(gp.player, true);

        }

        //OPTION STATE
        else if(gp.gameState == gp.optionState){
            drawOptionScreen();
        }

        //GAME OVER STATE
        else if(gp.gameState == gp.gameOverState){
            drawGameOverScreen();
        }

        //TRANSITION STATE
        else if(gp.gameState == gp.transitionState){
           drawTransition();
        }
        //TRADE STATE
        else if(gp.gameState == gp.tradeState){
            drawTradeScreen();
        }
        
    }
    
    public void drawPlayerLife(){
        
        int x = gp.tileSize/2;
        int y = gp.tileSize/2;

        int i = 0;

        //draw max hearts
        while(i < gp.player.maxLife/2){
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tileSize;
        }
        //reset
        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;

        //draw current life
        while(i < gp.player.life){
            g2.drawImage(heart_half, x, y, null);
            i++;
            if(i < gp.player.life){
                g2.drawImage(heart_full, x, y,null);
            }
            i++;
            x += gp.tileSize;
        }
        //draw max cosmo crystal
        x = (gp.tileSize/2) -5;
        y = (int)(gp.tileSize*1.5);
        i = 0;
        while(i < gp.player.maxCosmo){
            g2.drawImage(cosmo_blank, x, y, null);
            i++;
            x += 35; 
        }

        //draw cosmo
        x = (gp.tileSize/2) -5;
        y = (int)(gp.tileSize*1.5);
        i = 0;
        while(i < gp.player.cosmo){
            g2.drawImage(cosmo_full, x, y, null);
            i++;
            x += 35; 
        }
    }
    public void drawMessage(){
        int messageX = gp.tileSize;
        int messageY = gp.tileSize*4;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 25f));

        for(int i = 0; i < message.size(); i++){
            if(message.get(i) != null){
                g2.setColor(Color.gray);
                g2.drawString(message.get(i), messageX + 2, messageY +2);

                g2.setColor(Color.white);
                g2.drawString(message.get(i), messageX, messageY);

                int counter = messageCounter.get(i) + 1; // messgeCounter++
                messageCounter.set(i, counter);//define o contator para o array
                messageY +=30;
                if(messageCounter.get(i) >180){
                    message.remove(i);
                    messageCounter.remove(i);  
                }
            }
        }
    }
    public void drawTitleScreen() {
		
		if(titleScreenState == 0) {
			//TITLE NAME
           // gp.playMusic(12);
			g2.setColor(new Color(0,0,0));
			g2.fillRect(0, 0, gp.screenWith, gp.screenHeight);
			
			g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
			String text = "Ancient Ruin";
			int x = getXForCenterText(text);
			int y = gp.tileSize*3;
			
			//SHADOW
			g2.setColor(Color.gray);
			g2.drawString(text, x+5, y+5);
			
			//MAIN COLOR
			g2.setColor(Color.white);
			g2.drawString(text, x, y);
			
			//CHARACTER IMAGE
			x = gp.screenWith/2 - gp.tileSize/2;
			y += gp.tileSize*2 - 30;
			g2.drawImage(gp.player.down1, x, y, gp.tileSize, gp.tileSize, null);
			
			//MENU
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30F));
			
			text = "NEW GAME";
			x = getXForCenterText(text);
			y += gp.tileSize*2;
			g2.drawString(text, x, y);
			if(commandNum == 0) {
				g2.drawString(">", x - (gp.tileSize/2), y);
                
			}
			
			text = "LOAD GAME";
			x = getXForCenterText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if(commandNum == 1) {
				g2.drawString(">", x - (gp.tileSize/2), y);
			}
			
			text = "QUIT";
			x = getXForCenterText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if(commandNum == 2) {
				g2.drawString(">", x - (gp.tileSize/2), y);
			}
		}
        //History Screen
		else if (titleScreenState == 1) {
			drawHistoryScreen();
           
		}
	}
    public void drawHistoryScreen() {
       // gp.playMusic(12);
    	g2.setColor(new Color(0,0,0));
			g2.fillRect(0, 0, gp.screenWith, gp.screenHeight);
			
			// SELECTION SCREEN
			g2.setColor(Color.WHITE);
			g2.setFont(g2.getFont().deriveFont(30F));
			
			String text = "Add history later!";
			int x = getXForCenterText(text);
			int y = gp.tileSize*3;
			g2.drawString(text, x, y);
			
			text = "Start Game";
			x = getXForCenterText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if(commandNum == 0) {
				g2.drawString(">", x - gp.tileSize, y);
                //gp.stopMusic();
            }
			text = "Go to Back";
			x = getXForCenterText(text);
			y += gp.tileSize*2;
			g2.drawString(text, x, y);
			if(commandNum == 1) {
				g2.drawString(">", x - gp.tileSize, y);
                
			}
    }
    public void drawPauseScreen(){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80f));
        String text = "PAUSED";
        int x = getXForCenterText(text);
        int y = gp.screenHeight/2;

        g2.drawString(text, x, y);
    }
    public void drawDialogScreen(){
        //WINDOW
        int x = gp.tileSize*3;
        int y = gp.tileSize/2;
        int width = gp.screenWith - (gp.tileSize*6);
        int height = gp.tileSize*4;

        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32f));
        x += gp.tileSize;
        y += gp.tileSize;
        
        for(String line:currentDialog.split("\n")) {
        	g2.drawString(line, x, y);
        	y += 40;
        } 
    }
    public void drawCharacterScreen(){

        //Craete a frame
        final int  frameX = gp.tileSize*2;
        final int  frameY = gp.tileSize;
        final int  frameWidth = gp.tileSize*5;
        final int  frameHeight = gp.tileSize*10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        //TEXT
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32f));

        int textX = frameX + 20;
        int textY = frameY + gp.tileSize; 
        final int lineHeight = 35;

        //NAME
        g2.drawString("Level ",textX, textY); textY += lineHeight;
        g2.drawString("Life",textX, textY); textY += lineHeight;
        g2.drawString("Cosmo", textX, textY); textY += lineHeight;
        g2.drawString("Força",textX, textY);textY += lineHeight; 
        g2.drawString("Destreza", textX, textY); textY += lineHeight;
        g2.drawString("Ataque", textX, textY); textY += lineHeight;
        g2.drawString("Defesa: ", textX, textY);  textY += lineHeight;
        g2.drawString("Exp: ", textX, textY); textY += lineHeight;
        g2.drawString("Next Level: ", textX, textY); textY += lineHeight;
        g2.drawString("Coin: ", textX, textY); textY += lineHeight + 10;
        g2.drawString("Arma ", textX, textY); textY += lineHeight + 15;
        g2.drawString("Escudo", textX, textY); textY += lineHeight;
       

        //VALUE
        int tailX = (frameX + frameWidth) - 30;
        //reset textY
        textY = frameY + gp.tileSize;
        String value;

        value = String.valueOf(gp.player.level);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.life +" / " + gp.player.maxLife);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

         value = String.valueOf(gp.player.cosmo +" / " + gp.player.maxCosmo);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.strength);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.dexterity);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.attack);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.defense);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.exp);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.nextLevelExp);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.coin);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize, textY - 24, null);
        textY += gp.tileSize;

        g2.drawImage(gp.player.currentShield.down1, tailX - gp.tileSize, textY - 24, null);
       

    }
    public void drawInventory(Entity entity, boolean cursor){
        int frameX = 0;
        int frameY = 0;
        int frameWidth = 0;
        int frameHeight = 0;
        int slotCol = 0;
        int slotRow = 0;
       
       if(entity == npc) {
       
            //FRAME
            frameX = gp.tileSize * 2;
            frameY = gp.tileSize;
            frameWidth = gp.tileSize * 6;
            frameHeight = gp.tileSize * 5;
            slotCol = npcSlotCol;
            slotRow = npcSlotRow;
        }

        //Create a frame
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        drawPlayerInventory();

        //Slot 
        final int slotXStart = frameX + 20;
        final int slotYStart = frameY + 20;
        int slotX = slotXStart;
        int slotY = slotYStart;
        int slotSize = gp.tileSize +3;


        //Draw player's items
        //SLOT
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        slotX = slotXstart;
        slotY = slotYstart;
        slotSize = gp.tileSize + 3;
 
 
         //DRAW PLAYER'S ITEMS
         for(int i = 0; i < entity.inventory.size(); i++)
         {
 
             //EQUIP CURSOR
             if(entity.inventory.get(i) == entity.currentWeapon ||
                     entity.inventory.get(i) == entity.currentShield)
             {
                 g2.setColor(new Color(240,190,90));
                 g2.fillRoundRect(slotX,slotY, gp.tileSize, gp.tileSize,10,10 );
             }
 
             g2.drawImage(entity.inventory.get(i).down1, slotX,slotY,null);  //draw item
 
             slotX += slotSize;

            if (i == 4 || i == 9 || i == 14) {
                slotX = slotXStart;
                slotY += slotSize;
            }
        
        }
        //Draw cursor
        if(cursor == true){

            //Item selector cursor
            int cursorX = slotXStart + (slotSize * slotCol);
            int cursorY = slotYStart + (slotSize * slotRow);
            int cursorWidth = gp.tileSize;
            int cursorHeight = gp.tileSize;
            g2.setColor(Color.white);
            g2.setStroke(new BasicStroke(3));
            g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

            //Description frame
            int dFrameX = frameX;
            int dFrameY = frameY + frameHeight;
            int dFrameWidth = frameWidth;
            int dFrameHeight = gp.tileSize*3;
            

            //Draw description
            int textX = dFrameX + 20;
            int textY = dFrameY + gp.tileSize;
            g2.setFont(g2.getFont().deriveFont(28f));
            int itemIndex = getItemIndexOnSlot(slotCol, slotRow);

            if(itemIndex < entity.inventory.size()){
                drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
                for(String line:entity.inventory.get(itemIndex).description.split("\n")) {
                    g2.drawString(line, textX, textY);
                    textY += 30;
                }
            }
        }
    }
    public void drawPlayerInventory(){

        int frameX = gp.tileSize*12;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize*6;
        int frameHeight = gp.tileSize*5;
        int slotCol = playerSlotCol;
        int slotRow = playerSlotRow;
       
       
        //Create a frame
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        

        //Slot 
        final int slotXStart = frameX + 20;
        final int slotYStart = frameY + 20;
        int slotX = slotXStart;
        int slotY = slotYStart;
        int slotSize = gp.tileSize +3;


        //Draw player's items
        for (int i = 0; i < gp.player.inventory.size(); i++) {
            // EQUIP CURSOR
            if (gp.player.inventory.get(i) == gp.player.currentWeapon ||
            gp.player.inventory.get(i) == gp.player.currentShield) {
                g2.setColor(new Color(240, 190, 90));
                g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
            }
        

            g2.drawImage(gp.player.inventory.get(i).down1, slotX, slotY, null);
            slotX += slotSize;

            if (i == 4 || i == 9 || i == 14) {
                slotX = slotXStart;
                slotY += slotSize;
            }
        
        }
        //Draw cursor
    
         //Item selector cursor
            int cursorX = slotXStart + (slotSize * slotCol);
            int cursorY = slotYStart + (slotSize * slotRow);
            int cursorWidth = gp.tileSize;
            int cursorHeight = gp.tileSize;
            g2.setColor(Color.white);
            g2.setStroke(new BasicStroke(3));
            g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

             /*   //Description frame
            int dFrameX = frameX;
            int dFrameY = frameY + frameHeight;
            int dFrameWidth = frameWidth;
            int dFrameHeight = gp.tileSize*3;
            

            //Draw description
         int textX = dFrameX + 20;
            int textY = dFrameY + gp.tileSize;
            g2.setFont(g2.getFont().deriveFont(28f));
            int itemIndex = getItemIndexOnSlot(slotCol, slotRow);

            if(itemIndex < gp.player.inventory.size()){
                drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
                for(String line:gp.player.inventory.get(itemIndex).description.split("\n")) {
                    g2.drawString(line, textX, textY);
                    textY += 30;
                }
            } */ 
    }
    
    public void drawGameOverScreen(){
       
        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0, 0, gp.screenWith, gp.screenHeight);

        int x;
        int y;
        String text;

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));
        text = "GAME OVER";
        //SHADOW
        g2.setColor(Color.gray);
        x = getXForCenterText(text);
        y = gp.tileSize*4;
        g2.drawString(text, x, y);

        //MAIN COLOR
        g2.setColor(Color.white);
        g2.drawString(text, x-5, y-5);

        //Retry
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40f));
        text = "Reiniciar";
        x = getXForCenterText(text);
        y += gp.tileSize*4;
        g2.drawString(text, x, y);

        if(commandNum == 0){
            g2.drawString(">", x - 40, y);
          
        }

        //Back to title screen
        text = "Menu";
        x = getXForCenterText(text);
        y += 55;
        g2.drawString(text, x, y);

        if(commandNum == 1){
            g2.drawString(">", x - 40, y);
        }
    }
    public void drawOptionScreen(){
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32f));

        //SUB WINDOW
        int frameX = gp.tileSize*6;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize*8;
        int frameHeight = gp.tileSize*10;

        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        switch(subState){
            case 0: optionsTop(frameX, frameY);break;
            case 1: options_fullScreenNotification(frameX, frameY);break;
            case 2: options_control(frameX, frameY); break;
            case 3: options_endGameConfirmation(frameX, frameY); break;
        }
        gp.keyH.enterPressed = false;
    }
    public void optionsTop(int frameX, int frameY){
        int textX;
        int textY;

        //TITLE
        String text = "Opções";
        textX = getXForCenterText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        //FULL SCREEN ON/OFF
      
        textX = frameX + gp.tileSize;
        textY += gp.tileSize*2;
        g2.drawString("Tela Cheia", textX, textY);
        if(commandNum == 0){
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed == true){
                if(gp.fullScreen == false){
                    gp.fullScreen = true;
                }
                else if(gp.fullScreen == true){
                    gp.fullScreen = false;
                }
                 subState = 1;
            }
        }

        //Music
        textY += gp.tileSize;
        g2.drawString("Musica", textX, textY);
        if(commandNum == 1){
            g2.drawString(">", textX - 25, textY);
        }

        //SE
        textY += gp.tileSize;
        g2.drawString("SE", textX, textY);
        if(commandNum == 2){
            g2.drawString(">", textX - 25, textY);
        }

        //Control
        textY += gp.tileSize;
        g2.drawString("Controle", textX, textY);
        if(commandNum == 3){
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed == true){
                subState = 2;
                commandNum = 0;
            }
        }


        //End Game
        textY += gp.tileSize;
        g2.drawString("Sair do jogo", textX, textY);
        if(commandNum == 4){
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed == true){
                subState = 3;
                commandNum = 0;
            }
        }

        //Back
        textY += gp.tileSize*2;
        g2.drawString("Voltar", textX, textY);
        if(commandNum == 5){
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed == true){
                gp.gameState = gp.playState;
                gp.keyH.enterPressed = false;
            }
        }

        //full screen check box
        textX = frameX + (int)(gp.tileSize*4.5);
        textY = frameY + gp.tileSize*2+24;
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(textX, textY, 24, 24);
        if(gp.fullScreen == true){
            g2.fillRect(textX, textY, 24,24);
        }

        //music volume
        textY += gp.tileSize;
        g2.drawRect(textX, textY, 120, 24);
        int volumeWidth = 24 * gp.music.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 24);

        //SE volume
        textY += gp.tileSize;
        g2.drawRect(textX, textY, 120, 24);
        volumeWidth = 24 * gp.se.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 24);

        gp.config.saveConfig();
    }
    public void options_fullScreenNotification(int frameX, int frameY){
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize*3;

        currentDialog = "Para aplicar este efeito,\n reinicie o jogo";
        for (String line : currentDialog.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 30;
        }
        //back
        textY = frameY + gp.tileSize*9;
        g2.drawString("Voltar", textX, textY);
        if(commandNum == 0){
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed == true) {
                subState = 0;
                
            }
        }
    }
    public void options_control(int frameX, int frameY){
        int textX;
        int textY;

        //TITLE
        String text = "Controles";
        textX = getXForCenterText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        textX = frameX + gp.tileSize;
        textY += gp.tileSize;
        g2.drawString("Mover", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Confimar/Atacar", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Atirar", textX, textY);   
        textY += gp.tileSize;
        g2.drawString("Inventário", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Pausar", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Opções", textX, textY);
        textY += gp.tileSize;

        textX = frameX + gp.tileSize*6;
        textY = frameY + gp.tileSize*2;

        g2.drawString("WASD", textX, textY);
        textY += gp.tileSize;
        g2.drawString("ENTER", textX, textY);
        textY += gp.tileSize;
        g2.drawString("F", textX, textY);
        textY += gp.tileSize;
        g2.drawString("E", textX, textY);
        textY += gp.tileSize;
        g2.drawString("P", textX, textY);
        textY += gp.tileSize;
        g2.drawString("ESC", textX, textY);

        //back
        textX = frameX + gp.tileSize;
        textY = frameY + gp.tileSize*9;
        g2.drawString("Voltar", textX, textY);
        if(commandNum == 0){
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed == true) {
                subState = 0;
                commandNum = 3;
            }
        }
    }
    public void options_endGameConfirmation(int frameX, int frameY){
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize*3;
        
        currentDialog = "Sair do jogo e \nvoltar para a tela de título?";
        for (String line : currentDialog.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 30;
        }

        //YES
        String text = "Sim";
        textX = getXForCenterText(text);
        textY += gp.tileSize*3;
        g2.drawString(text, textX, textY);
        if(commandNum == 0){
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed == true) {
                subState = 0;
                gp.stopMusic();
                gp.gameState = gp.titleState;
                gp.ui.titleScreenState = 0;
            }
        }

        //NO
        text = "Não";
        textX = getXForCenterText(text);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if(commandNum == 1){
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed == true) {
                subState = 0;
                commandNum = 4;
            }
        }

    }
    
    public void drawTransition(){
       counter++;
       g2.setColor(new Color(0,0,0,counter*5));
         g2.fillRect(0, 0, gp.screenWith, gp.screenHeight);
            if(counter == 50){
                counter = 0;
                gp.gameState = gp.playState;
                gp.currentMap =gp.eHandler.tempMap;
                gp.player.worldX =   gp.tileSize * gp.eHandler.tempCol;
                gp.player.worldY =   gp.tileSize * gp.eHandler.tempRow;
                gp.eHandler.previousEventX = gp.player.worldX;
                gp.eHandler.previousEventY = gp.player.worldY;

            }
    } 
    public void drawTradeScreen(){
        switch(subState){
            case 0: trade_select(); break;
            case 1: trade_buy(); break;
            case 2: trade_sell(); break;
        }
        gp.keyH.enterPressed = false;
    }

    public void trade_select(){
        drawDialogScreen();

        //draw window
        int x = gp.tileSize*15;
        int y = gp.tileSize*4;
        int width = gp.tileSize*4;
        int height = (int)(gp.tileSize*3.5);
        drawSubWindow(x, y, width, height);

        //draw text
        x += gp.tileSize; 
        y += gp.tileSize;
      
        g2.drawString("Comprar", x, y);
        if(commandNum == 0){
            g2.drawString(">", x - 24, y);
            if(gp.keyH.enterPressed == true){
                subState = 1;
            }
        } 
        y+= gp.tileSize;

        g2.drawString("Vender", x, y);
        if(commandNum == 1){
            g2.drawString(">", x - 24, y);
            if(gp.keyH.enterPressed == true){
                subState = 2;
            }
        }
        y+= gp.tileSize;
        g2.drawString("Voltar", x, y); 
        if(commandNum == 2){
            g2.drawString(">", x - 24, y);
            if(gp.keyH.enterPressed == true){
                gp.gameState = gp.dialogState;
                currentDialog = "Venha de novo, hehe!";
            
            }
        }
    } 
    public void trade_buy(){
        //*  DRAW PLAYER INVENTORY
        drawInventory(gp.player, false);
        //*  DRAW NPC INVENTORY
        drawInventory(npc, true);

        //Draw hint window
        int x = gp.tileSize*2;
        int y = gp.tileSize*9;
        int width = gp.tileSize*6;
        int height = gp.tileSize*2;
        drawSubWindow(x, y, width, height);
        g2.drawString("[ESC] Voltar", x+24, y +60);

        //Draw player coin window
        x = gp.tileSize*12;
        y = gp.tileSize*9;
        width = gp.tileSize*6;
        height = gp.tileSize*2;
        drawSubWindow(x, y, width, height);
        g2.drawString("Moeda: " + gp.player.coin, x+24, y +60);

        //Draw price window
        int itemIndex = getItemIndexOnSlot(npcSlotCol, npcSlotRow);
        if(itemIndex < npc.inventory.size()){
            x = (int)(gp.tileSize*5.5);
            y = (int)(gp.tileSize*5.5);
            width = (int)(gp.tileSize*2.5);
            height = gp.tileSize;
            drawSubWindow(x, y, width, height);
            g2.drawImage(coin, x+10, y+8,32,32,null);

            int price = npc.inventory.get(itemIndex).price;
            String text = ""+price;
            x = getXForAlignToRightText(text, gp.tileSize*8-20);
            g2.drawString(text, x, y+ 34);

            if(gp.keyH.enterPressed == true){
                if(npc.inventory.get(itemIndex).price > gp.player.coin){
                    subState = 0;
                    gp.gameState = gp.dialogState;
                    currentDialog = "Moedas insufientes";
                    drawDialogScreen();
                }
                else if(gp.player.inventory.size() == gp.player.maxInventorySize){
                    subState = 0;
                    gp.gameState = gp.dialogState;
                    currentDialog = "Inventário cheio";
                    drawDialogScreen();
                }
                else{
                    gp.player.coin -= npc.inventory.get(itemIndex).price;
                    gp.player.inventory.add(npc.inventory.get(itemIndex));
                }
            }

        }

    }
    public void trade_sell(){
        //draw player inventory
        drawInventory(gp.player, true);
        int x,y,width,height;

        x = gp.tileSize*2;
        y = gp.tileSize*9;
        width = gp.tileSize*6;
        height = gp.tileSize*2;
        drawSubWindow(x, y, width, height);
        g2.drawString("[ESC] Voltar", x+24, y +60);

        //Draw player coin window
        x = gp.tileSize*12;
        y = gp.tileSize*9;
        width = gp.tileSize*6;
        height = gp.tileSize*2;
        drawSubWindow(x, y, width, height);
        g2.drawString("Moeda: " + gp.player.coin, x+24, y +60);

        //Draw price window
        int itemIndex = getItemIndexOnSlot(playerSlotCol, playerSlotRow);
        if(itemIndex < gp.player.inventory.size()){
            x = (int)(gp.tileSize*15.5);
            y = (int)(gp.tileSize*5.5);
            width = (int)(gp.tileSize*2.5);
            height = gp.tileSize;
            drawSubWindow(x, y, width, height);
            g2.drawImage(coin, x+10, y+8,32,32,null);

            int price = gp.player.inventory.get(itemIndex).price/2;
            String text = ""+price;
            x = getXForAlignToRightText(text, gp.tileSize*18-20);
            g2.drawString(text, x, y+ 34);

            //sell an item

            if(gp.keyH.enterPressed == true){
                if(gp.player.inventory.get(itemIndex) == gp.player.currentWeapon ||
                     gp.player.inventory.get(itemIndex) == gp.player.currentShield){
                        commandNum= 0;
                        subState = 0;
                        gp.gameState = gp.dialogState;
                        currentDialog = "Você não pode vender um item equipado";
                        drawDialogScreen();
                }
                else {
                    gp.player.inventory.remove(itemIndex);
                    gp.player.coin += price;
                }
            }
                
        }
    }
    public int getItemIndexOnSlot(int slotCol, int slotRow){
        int itemIndex = slotCol + (slotRow*5);
        return itemIndex;
    }
    public void drawSubWindow(int x, int y, int width, int height){
        Color c = new Color(0,0,0,210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);
        
        c = new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25,25);
    }
    public int getXForCenterText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWith/2 - length/2; 
        return x;
    }
    public int getXForAlignToRightText(String text, int tailX){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = tailX - length; 
        return x;
    }
}