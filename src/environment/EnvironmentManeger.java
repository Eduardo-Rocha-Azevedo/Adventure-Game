package environment;

import java.awt.Graphics2D;

import principal.GamePanel;

public class EnvironmentManeger {
    GamePanel gp;
    Lighting lighting;

    public EnvironmentManeger(GamePanel gp){
        this.gp = gp;
    }

    public void setup(){
        lighting = new Lighting(gp);
    }

    public void update(){
        lighting.update();
    }

    public void draw(Graphics2D g2){
        lighting.draw(g2);
    }
}
