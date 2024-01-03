package principal;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean up,down, left, right, enterPressed, spacePressed;
    boolean showDebugText = false;
    public GamePanel gp;

    //CONSTRUCTOR =======================
    public KeyHandler(GamePanel gp){
        this.gp = gp; 
    }

    //METHODS ===============================
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();

        //TITLE STATE
        if(gp.gameState == gp.tileState){
            tileState(code);
        }
       
        //PLAY STATE
       else if(gp.gameState == gp.playState){
            playState(code);     
        } 

        //PAUSE STATE
        else if(gp.gameState == gp.pauseState){
           pauseState(code);
        }
        //DIALOG STATE
         else if(gp.gameState == gp.dialogState){
            dialogState(code);
        }

        //CHARACTER STATE
        else if(gp.gameState == gp.characterState){
            characterState(code);
        }
        	
    } 

    public void tileState(int code){

       if(gp.ui.titleScreenState == 0){
           if(code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0){
                    gp.ui.commandNum = 2;
                }
            }
            if(code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 2){
                     gp.ui.commandNum = 0;
                }
            }
            if(code == KeyEvent.VK_ENTER){
                if(gp.ui.commandNum == 0){
                    gp.ui.titleScreenState = 1;
                }
                else if(gp.ui.commandNum == 1){
                        //add later
                }
                else if(gp.ui.commandNum == 2){
                        System.exit(0);
                }
            }   
        } 
         //history
         else if(gp.ui.titleScreenState == 1){
                if(code == KeyEvent.VK_W) {
                    gp.ui.commandNum--;
                    if(gp.ui.commandNum < 0){
                        gp.ui.commandNum = 1;
                    }
                }
                if(code == KeyEvent.VK_S) {
                    gp.ui.commandNum++;
                    if(gp.ui.commandNum > 1){
                        gp.ui.commandNum = 0;
                    }
                }
                if(code == KeyEvent.VK_ENTER){
                    if(gp.ui.commandNum == 0){
                       gp.ui.titleScreenState = 1;
                       gp.gameState = gp.playState;
                       gp.playMusic(0);
                    }
                    else if(gp.ui.commandNum == 1){
                      gp.ui.titleScreenState = 0;
                }
            } 
        }
    }

    public void playState(int code){
        if(code == KeyEvent.VK_W) {up = true;}   
           
        if(code == KeyEvent.VK_S) {down = true;}
              
        if( code == KeyEvent.VK_A) {left = true;}
            
        if(code == KeyEvent.VK_D) {right = true;}
                
        if(code == KeyEvent.VK_P){gp.gameState = gp.pauseState;}   
                 
        if(code == KeyEvent.VK_E){gp.gameState = gp.characterState;}
              
        if(code == KeyEvent.VK_ENTER){enterPressed = true;}
              
        //debug
		if(code == KeyEvent.VK_T) {
           if(showDebugText == false){
               showDebugText = true;
           }
           else if(showDebugText == true){
               showDebugText = false;
           }
        } 

        if(code == KeyEvent.VK_R){
          gp.tileM.loadMap("/maps/mapV2.txt");
        }
    }

    public void pauseState(int code){
        if(code == KeyEvent.VK_P){gp.gameState = gp.playState;}
    }

    public void dialogState(int code){
        if(code == KeyEvent.VK_ENTER){ gp.gameState = gp.playState;}   
    }

    public void characterState(int code){
        if(code == KeyEvent.VK_E){
            gp.gameState = gp.playState; 
        }
        if(code == KeyEvent.VK_W){
            if(gp.ui.slotRow != 0){
                gp.ui.slotRow--;
                gp.playSE(9);
            }
           
        }
        if(code == KeyEvent.VK_A){
           if(gp.ui.slotCol != 0){
                gp.ui.slotCol--;
                gp.playSE(9);
           }
            
        }
        if(code == KeyEvent.VK_S){
            if(gp.ui.slotRow != 3) {
                gp.ui.slotRow++;
                gp.playSE(9);
            }
           
        }
        if(code == KeyEvent.VK_D){
            if(gp.ui.slotCol != 4){
                gp.ui.slotCol++;
                gp.playSE(9);
            }
        }
           
    }

	@Override
	public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
           up = false;
        }
        if(code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
            down = false;
        }
        if(code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) {
            left = false;
        }
        if(code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
            right = false;
        }
		
	}
    
}
