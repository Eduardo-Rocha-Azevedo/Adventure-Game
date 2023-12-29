package principal;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import tile.TileManeger;
import principal.Sound;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class GamePanel extends JPanel implements Runnable{

	//screen setings 
	final int originalTilesSize = 16;// 16px X 16px
	final int scale = 3;
	
	public final int tileSize = originalTilesSize * scale;
    public final int maxScreenCols = 16;
    public final int maxScreenRows = 12;
    public final int screenWith = tileSize * maxScreenCols;//768px
    public final int screenHeight = tileSize * maxScreenRows;//576px

    //World setings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;


    //FPS
    int FPS = 60;

    //SYSTEM
    TileManeger tileM = new TileManeger(this); 
    public KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHantler eHandler = new EventHantler(this);
    Thread gameThread; 


    //ENTITY AND OBJECTS
    public Player player = new Player(this,keyH);
    public Entity obj[] = new Entity[10];
    public Entity npc[] = new Entity[10]; 
    public Entity monster[] = new Entity[20];
    ArrayList<Entity> entityList = new ArrayList<>();

    //GAME STATES
    public int gameState;
    public final int tileState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogState = 3;


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
        //playMusic(0);
        //stopMusic();
        gameState = tileState;
    }

    public void startGameThread(){
        if(gameThread == null){
            gameThread = new Thread(this);
            gameThread.start();
        }
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
                repaint();
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

    public void update(){
      if(gameState == playState){
       //player
        player.update();

       //NPC
       for(int i = 0; i < npc.length; i++){
        if(npc[i] != null){
            npc[i].update();
         }
       } 
       //MONSTER
         for(int i = 0; i < monster.length; i++){
          if(monster[i] != null){
            if(monster[i].alive == true && monster[i].dyain == false){
                monster[i].update();
             }
            if(monster[i].alive == false){
                monster[i] = null;
              }    
            }
         }
      }
      if(gameState == pauseState) {
       //nothing yet
      }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        //debug
        long drawStart = 0;
       
        if(keyH.checkDrawTime == true){
             drawStart = System.nanoTime(); 
        }

        //TITLE SCREEN
        if(gameState == tileState){
            ui.draw(g2);
        }   

        //OTHERS
        else{
            //TILE
            tileM.draw(g2);

            // Add entities to the list
            entityList.add(player);

            //npc
            for(int i = 0; i < npc.length; i++){
                if(npc[i] != null){
                    entityList.add(npc[i]);
                }
            }
            //obj
            for(int i = 0; i < obj.length; i++){
                if(obj[i] != null){
                    entityList.add(obj[i]);
                }
            }
            //monster
            for(int i = 0; i < monster.length; i++){
                if(monster[i] != null){
                    entityList.add(monster[i]);
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
            //UI
            ui.draw(g2);
        }
       

        //debug
        if(keyH.checkDrawTime == true){
           long drawEnd = System.nanoTime();
        long passed  = drawEnd - drawStart;

   
        g2.setColor(Color.WHITE);
        g2.drawString("Draw Time: " + passed,10, 400);
        System.out.println("Draw Time: " + passed);

        }
        
         g2.dispose();
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
}