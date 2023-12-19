package principal;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean up,down, left, right, enterPressed;
    boolean checkDrawTime = false;
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

        //PLAY STATE
        if(gp.gameState == gp.playState){

            if(code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
            up = true;
            }
            if(code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
                down = true;
            }
            if(code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) {
            left = true;
            }
            if(code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
                right = true;
            }
            if(code == KeyEvent.VK_P){
                gp.gameState = gp.pauseState;     
            }
            if(code == KeyEvent.VK_ENTER){
                enterPressed = true;
            }
            
            //debug
		    if(code == KeyEvent.VK_T) {
                if(checkDrawTime == false){
                    checkDrawTime = true;
                }
                else if(checkDrawTime == true){
                checkDrawTime = false;
                }
            } 
        } 

        //PAUSE STATE
        else if(gp.gameState == gp.pauseState){
            if(code == KeyEvent.VK_P){
                gp.gameState = gp.playState; 
            }
        }
        //DIALOG STATE
         else if(gp.gameState == gp.dialogState){
                if(code == KeyEvent.VK_ENTER){
                    gp.gameState = gp.playState;
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
