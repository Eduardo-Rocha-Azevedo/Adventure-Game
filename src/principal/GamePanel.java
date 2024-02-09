package principal;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ai.PathFinder;
import entity.Entity;
import entity.Player;
import environment.EnvironmentManeger;
import tile.TileManeger;
import tile_interactive.InteractiveTile;
//import principal.Sound;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
//import java.awt.GraphicsDevice;
//import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class GamePanel extends JPanel implements Runnable{

	//SCREEN SETINGS
	final int originalTilesSize = 16;// 16px X 16px
	final int scale = 3;
	
	public final int tileSize = originalTilesSize * scale;
    public final int maxScreenCols = 20;
    public final int maxScreenRows = 12;
    public final int screenWith = tileSize * maxScreenCols;//768px
    public final int screenHeight = tileSize * maxScreenRows;//576px

    //FOR FULL SCREEN
    int screenWith2 = screenWith;
    int screenHeight2 = screenHeight;
    BufferedImage tempScreen;
    Graphics2D g2;
    public boolean fullScreen = false;

    //World setings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int maxMap = 10;
    public int currentMap = 0;

    //FPS
    int FPS = 60;

    //SYSTEM
    public TileManeger tileM = new TileManeger(this); 
    public KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHantler eHandler = new EventHantler(this);
    Config config = new Config(this);
    public PathFinder pFinder = new PathFinder(this);
    EnvironmentManeger eManeger = new EnvironmentManeger(this);
    Thread gameThread; 


    //ENTITY AND OBJECTS
    public Player player = new Player(this,keyH);
    public Entity obj[][] = new Entity[maxMap][20];
    public Entity npc[][] = new Entity[maxMap][10]; 
    public Entity monster[][] = new Entity[maxMap][20];
    public InteractiveTile iTile[][] = new InteractiveTile[maxMap][50];//caso precise de mais tiles interativos aumetar
    public Entity projectile[][] = new Entity[maxMap][20];
    //public ArrayList<Entity> projectileList = new ArrayList<>();
    public ArrayList<Entity> particleList = new ArrayList<>();
    ArrayList<Entity> entityList = new ArrayList<>();

    //GAME STATES
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogState = 3;
    public final int characterState = 4;
    public final int optionState = 5;
    public final int gameOverState = 6;
    public final int transitionState = 7;
    public final int tradeState = 8;

    //CONSTRUCTOR======================
    public GamePanel() {
     this.setPreferredSize(new Dimension(screenWith, screenHeight));
     this.setBackground(new Color(0,0,0));
     this.setDoubleBuffered(true);
     this.addKeyListener(keyH);
     this.setFocusable(true);
   
    }


    //METHODS==========================
    public void setupGame(){
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
        aSetter.setInteractiveTile();
        eManeger.setup();
       // stopMusic();
        //playMusic(12);
        
        gameState = titleState;
        tempScreen = new BufferedImage(screenWith, screenHeight, BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D)tempScreen.getGraphics();
    
        if(fullScreen == true){
            setFullScreen();
        }    
    }
    public void retry(){
        
        player.setDefultPositions();
        player.retoreLifeAndCosmo();
        aSetter.setMonster();
        aSetter.setNPC();
       
    }
    public void restart(){
        player.setDefultValues();
        player.setDefultPositions();
        player.setItems();
        aSetter.setMonster();
        aSetter.setNPC();
        aSetter.setInteractiveTile();
        stopMusic();
    }
    public void setFullScreen() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		Main.window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		screenWith2 = (int) width;
		screenHeight2 = (int) height;
        
		//fullScreenOffsetFactor = (float) screenWith / (float) screenWith2;
	}

    public void startGameThread(){
        if(gameThread == null){
            gameThread = new Thread(this);
            gameThread.start();
        }
    }

   
    public void update(){
      if(gameState == playState){
       //player
        player.update();

       //NPC
       for(int i = 0; i < npc[1].length; i++){
        if(npc[currentMap][i] != null){
            npc[currentMap][i].update();
         }
       } 
       //MONSTER
         for(int i = 0; i < monster[1].length; i++){
          if(monster[currentMap][i] != null){
            if(monster[currentMap][i].alive == true && monster[currentMap][i].dyain == false){
                monster[currentMap][i].update();
             }
            if(monster[currentMap][i].alive == false){
                monster[currentMap][i].checkDrop();
                monster[currentMap][i] = null;
              }    
            }
         }

        //PROJECTILE
        for(int i = 0; i < projectile[1].length; i++){
           if(projectile[currentMap][i] != null){
            if(projectile[currentMap][i].alive == true){
                projectile[currentMap][i].update();
            }

            if(projectile[currentMap][i].alive == false){
                projectile[currentMap][i] = null;
            }
          }
        }

        //PARTICLE
        for(int i = 0; i < particleList.size(); i++){
           if(particleList.get(i) != null){
            if(particleList.get(i).alive == true){
                particleList.get(i).update();
            }

            if(particleList.get(i).alive == false){
                particleList.remove(i);
            }
          }
        }


        for(int i = 0; i < iTile[1].length; i++){
            if(iTile[currentMap][i] != null){
                iTile[currentMap][i].update();
            }
        }
        eManeger.update();
      }
      if(gameState == pauseState) {
       //nothing yet
      }
    }


    public void drawToTempScreen(){
         //debug
         long drawStart = 0;
       
         if(keyH.showDebugText == true){
              drawStart = System.nanoTime(); 
         }
 
         //TITLE SCREEN
         if(gameState == titleState){
             ui.draw(g2);
         }   
 
         //OTHERS
         else{
            //TILE
            tileM.draw(g2);
             
            //INTERACTIVE TILE
            for(int i = 0; i < iTile[1].length; i++){
                if(iTile[currentMap][i] != null){
                    iTile[currentMap][i].draw(g2);
                }
            }   
 
            // Add entities to the list
            entityList.add(player);
 
            //npc
            for(int i = 0; i < npc[1].length; i++){
                if(npc[currentMap][i] != null){
                    entityList.add(npc[currentMap][i]);
                }
            }
            //obj
            for(int i = 0; i < obj[1].length; i++){
                if(obj[currentMap][i] != null){
                    entityList.add(obj[currentMap][i]);
                }
            }
            //monster
            for(int i = 0; i < monster[1].length; i++){
                if(monster[currentMap][i] != null){
                    entityList.add(monster[currentMap][i]);
                }
            }
 
            //projectile
            for(int i = 0; i < projectile[1].length; i++){
                if(projectile[currentMap][i] != null){
                    entityList.add(projectile[currentMap][i]);
                }
            }
 
            //particle
            for(int i = 0; i < particleList.size(); i++){
                if(particleList.get(i) != null){
                    entityList.add(particleList.get(i));
                }
            }
 
            //sort
            Collections.sort(entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {
                    int result = Integer.compare(e1.worldX, e2.worldY);
                    return  result;
                }
            });
 
            //draw entities
            for(int i = 0; i < entityList.size(); i++){
                entityList.get(i).draw(g2);
            }
 
            //empty list
            entityList.clear();

            //Environment
            eManeger.draw(g2);

            //UI
            ui.draw(g2);
         }
        
 
         //debug
         if(keyH.showDebugText == true){
             long drawEnd = System.nanoTime();
             long passed  = drawEnd - drawStart;
 
             g2.setFont(new Font("arial", Font.PLAIN, 15));
             g2.setColor(Color.WHITE);
 
             int x = 10;
             int y = 400;
             int lineHeight = 20;
 
             g2.drawString("WorldX: "+ player.worldX, x, y); y += lineHeight;
             g2.drawString("WorldY: " + player.worldY , x, y); y += lineHeight;
             g2.drawString("Col: "+ (player.worldX + player.solidArea.x)/tileSize, x, y); y += lineHeight;
             g2.drawString("Row: " + (player.worldY + player.solidArea.y)/tileSize, x, y); y += lineHeight;
             g2.drawString("Draw Time: " + passed, x, y);
            
        }
    }

    public void drawToScreen(){
        Graphics g = getGraphics();
        g.drawImage(tempScreen, 0, 0, screenWith2, screenHeight2, null);    
        g.dispose();
   }

    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic(){
        music.stop();
    }

    public void playSE(int i){
        se.setFile(i);
        se.play();
    }
    
    @Override
    public void run(){  
        double drawInterval = 1000000000/FPS;
        double delta = 0; 
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;


        while(gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1){
                update();
                drawToTempScreen(); // draw everything to the buffered image
                drawToScreen(); // draw the buffered image to the screen
                delta--;  
                drawCount++;
            }   

            if(timer >= 1000000000){
                //System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

}