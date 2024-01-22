package principal;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean up,down, left, right, enterPressed, shotKeyPressed;
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
        if(gp.gameState == gp.titleState){
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

        //OPTION STATE
        else if(gp.gameState == gp.optionState){
            optionState(code);
        }
        
        //GAME OVER STATE
        else if(gp.gameState == gp.gameOverState){
           gameOverState(code);
        }
    } 

    public void gameOverState(int code){
        if(code == KeyEvent.VK_W){
            gp.ui.commandNum--;
            gp.playSE(9);
            if(gp.ui.commandNum < 0){
                gp.ui.commandNum = 1;
            }
        }
        if(code == KeyEvent.VK_S){
            gp.ui.commandNum++;
            gp.playSE(9);
            if(gp.ui.commandNum > 1){
                gp.ui.commandNum = 0;
            }
        }
        if(code == KeyEvent.VK_ENTER){
            if(gp.ui.commandNum == 0){
                gp.gameState = gp.playState;
                gp.retry();
                gp.playMusic(12);
            }
            else if(gp.ui.commandNum == 1){
                gp.gameState = gp.titleState;
                gp.ui.titleScreenState = 0;
                gp.restart();

            }
        }
    }
    public void tileState(int code){
        
        if(gp.ui.titleScreenState == 0){
           // gp.playMusic(12);
           if(code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                gp.playSE(9);
                if(gp.ui.commandNum < 0){
                    gp.ui.commandNum = 2;
                }
            }
            if(code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                gp.playSE(9);
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
                    gp.playSE(9);
                    if(gp.ui.commandNum < 0){
                        gp.ui.commandNum = 1;
                    }
                }
                if(code == KeyEvent.VK_S) {
                    gp.ui.commandNum++;
                    gp.playSE(9);
                    if(gp.ui.commandNum > 1){
                        gp.ui.commandNum = 0;
                    }
                }
                if(code == KeyEvent.VK_ENTER){
                    if(gp.ui.commandNum == 0){
                       gp.ui.titleScreenState = 1;
                       gp.gameState = gp.playState;
                      // gp.stopMusic(); 
                       gp.playMusic(12);
                       
                    }
                    else if(gp.ui.commandNum == 1){
                      gp.ui.titleScreenState = 0;
                }
            } 
        } 
    }

    public void playState(int code){
        //gp.stopMusic();
        if(code == KeyEvent.VK_W) {up = true;}   
           
        if(code == KeyEvent.VK_S) {down = true;}
              
        if( code == KeyEvent.VK_A) {left = true;}
            
        if(code == KeyEvent.VK_D) {right = true;}

        if(code == KeyEvent.VK_F){shotKeyPressed = true;}

        if(code == KeyEvent.VK_ENTER){enterPressed = true;}

        //GAME STATE
        //characther state
        if(code == KeyEvent.VK_E){gp.gameState = gp.characterState;}

        //option state
        if(code == KeyEvent.VK_ESCAPE){gp.gameState = gp.optionState;}

        //pause state
        if(code == KeyEvent.VK_P){gp.gameState = gp.pauseState;}  
        

        
              
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
            switch(gp.currentMap){
                case 0: gp.tileM.loadMap("/maps/mapV2.txt",0); break;
                case 1: gp.tileM.loadMap("/maps/interior01.txt",1); break;
                case 2: gp.tileM.loadMap("/maps/dangeon1.txt",2); break;
            }
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
        if(code == KeyEvent.VK_ENTER){
            gp.player.selectItem();
        }
           
    }

    public void optionState(int code){
        if(code == KeyEvent.VK_ESCAPE){gp.gameState = gp.playState;}
        if(code == KeyEvent.VK_ENTER){ enterPressed = true;}

        int maxCommandNum = 0;
        switch (gp.ui.subState) {
            case 0: maxCommandNum = 5; break;
            case 3: maxCommandNum = 1; break;
        }

        if(code == KeyEvent.VK_W){
            gp.ui.commandNum--;
            gp.playSE(9);
            if(gp.ui.commandNum < 0){
                gp.ui.commandNum = maxCommandNum;
            }
        }
        if(code == KeyEvent.VK_S){
            gp.ui.commandNum++;
            gp.playSE(9);
            if(gp.ui.commandNum > maxCommandNum){
                gp.ui.commandNum = 0;
            }
        }
        if(code == KeyEvent.VK_A){
            if(gp.ui.subState == 0){
                if(gp.ui.commandNum == 1 && gp.music.volumeScale > 0){
                    gp.music.volumeScale--;
                    gp.music.checkVolume();
                    gp.playSE(9);
                }
                if(gp.ui.commandNum == 2 && gp.music.volumeScale > 0){
                    gp.se.volumeScale--;
                    gp.playSE(9);
                }
            }
        }
        if(code == KeyEvent.VK_D){
            if(gp.ui.subState == 0){
                if(gp.ui.commandNum == 1 && gp.music.volumeScale < 5){
                    gp.music.volumeScale++;
                    gp.music.checkVolume();
                    gp.playSE(9);
                }
                if(gp.ui.commandNum == 2 && gp.se.volumeScale < 5){
                    gp.se.volumeScale++;
                    gp.playSE(9);
                }
            }
        }
    }


	@Override
	public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_UP || code == KeyEvent.VK_W) { up = false; }
          
        if(code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {down = false;  }
    
        if(code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) { left = false; }
           
        if(code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) { right = false;}

        if(code == KeyEvent.VK_F){ shotKeyPressed = false; }

		
	}
    
}
