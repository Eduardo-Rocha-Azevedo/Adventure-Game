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
import objects.OBJ_Heart;


public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font maruMonica;
    BufferedImage heart_full, heart_half, heart_blank;
  
    public boolean gameFinished = false;

    //inventory
    public int slotCol = 0;
    public int slotRow = 0;


    //mensage info
    public boolean messageOn = false;
    public String currentDialog = "";
    public int commandNum = 0;
    public int titleScreenState = 0;
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
   
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
    }

    public void addMassage(String text){
        message.add(text);
        messageCounter.add(0);
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;

        g2.setFont(maruMonica);  
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.white);
        
        //TITLE SCREEN
        if(gp.gameState == gp.tileState){
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
            drawInventory();

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

    public void drawTitleScreen(){
        if(titleScreenState == 0){
             //title name
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96f));
            String text = "Ancient Ruin ";
            int x = getXForCenterText(text);
            int y = gp.tileSize*3;

            //shadow
            g2.setColor(new Color(128,128,128,220));
            g2.drawString(text, x+5, y+5);

            //main color
            g2.setColor(Color.white);
            g2.drawString(text, x, y);
            
            //player img
            x = gp.screenWith/2 - (gp.tileSize*2)/2;
            y += gp.tileSize*2;
            g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);

            //Menu
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48f));
            text = "New Game";
            x = getXForCenterText(text);
            y += gp.tileSize*3.5;
            g2.drawString(text, x, y);

            if(commandNum == 0){
                g2.drawString(">", x-gp.tileSize, y);
            }

            text = "Load Game";
            x = getXForCenterText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);

            if(commandNum == 1){
                g2.drawString(">", x-gp.tileSize, y);
            }

            text = "Quit";
            x = getXForCenterText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);

            if(commandNum == 2){
                g2.drawString(">", x-gp.tileSize, y);
            }

        }
        else if (titleScreenState == 1){
            // class selection screen
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48f));

            String text = "Add history later";
            int x = getXForCenterText(text);
            int y = gp.tileSize*3;
            g2.drawString(text, x, y);
           


            text = "Start";
            x = getXForCenterText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x-gp.tileSize, y);
                
            }

        
            text = "Back";
            x = getXForCenterText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 1){
                g2.drawString(">", x-gp.tileSize, y);
            }

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
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int width = gp.screenWith - (gp.tileSize*4);
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
        g2.drawString("Level ",textX, textY);
        textY += lineHeight;
        g2.drawString("Life",textX, textY);
        textY += lineHeight;
        g2.drawString("Força",textX, textY);
        textY += lineHeight;
        g2.drawString("Destreza", textX, textY);
        textY += lineHeight;
        g2.drawString("Ataque", textX, textY);
        textY += lineHeight;
        g2.drawString("Defesa: ", textX, textY);
        textY += lineHeight;
        g2.drawString("Exp: ", textX, textY);
        textY += lineHeight;
        g2.drawString("Next Level: ", textX, textY);
        textY += lineHeight;
        g2.drawString("Coin: ", textX, textY);
        textY += lineHeight + 20;
        g2.drawString("Arma ", textX, textY);
        textY += lineHeight + 15;
        g2.drawString("Escudo", textX, textY);
        textY += lineHeight;

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

        g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize, textY - 14, null);
        textY += gp.tileSize;

        g2.drawImage(gp.player.currentShield.down1, tailX - gp.tileSize, textY - 14, null);
       

    }

    public void drawInventory(){
        //Create a frame
        int frameX = gp.tileSize*9;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize*6;
        int frameHeight = gp.tileSize*5;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        //Slot 
        final int slotXStart = frameX + 20;
        final int slotYStart = frameY + 20;
        int slotX = slotXStart;
        int slotY = slotYStart;
        int slotSize = gp.tileSize +3;


        //Item selector cursor
        int cursorX = slotXStart + (slotSize * slotCol);
        int cursorY = slotYStart + (slotSize * slotRow);
        int cursorWidth = gp.tileSize;
        int cursorHeight = gp.tileSize;

        //Draw player's items
        for(int i = 0; i < gp.player.inventory.size(); i++){
            g2.drawImage(gp.player.inventory.get(i).down1, slotX, slotY, null);
            slotX += slotSize;
            if(i == 4 || i == 9|| i == 14){
                slotX = slotXStart;
                slotY += slotSize;
            }
        }

        //Draw cursor
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

        //Description frame
        int dFrameX = frameX;
        int dFrameY = frameY + frameHeight;
        int dFrameWidth = frameWidth;
        int dFrameHeight = gp.tileSize*3;
        drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);

        //Draw description
        int textX = dFrameX + 20;
        int textY = dFrameY + gp.tileSize;
        g2.setFont(g2.getFont().deriveFont(28f));
        int itemIndex = getItemIndexOnSlot();

        if(itemIndex < gp.player.inventory.size()){
            for(String line:gp.player.inventory.get(itemIndex).description.split("\n")) {
            	g2.drawString(line, textX, textY);
            	textY += 30;
            }
           
        }
    
    
    }

    public int getItemIndexOnSlot(){
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























