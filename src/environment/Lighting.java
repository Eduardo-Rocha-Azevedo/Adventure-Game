package environment;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.image.BufferedImage;

import principal.GamePanel;

public class Lighting {
    GamePanel gp;
    BufferedImage darknessFilter;
   
    //DAY STATE
    public int dayCounter = 0;
    public float filterAlpha = 0f;
    public final int day= 0;
    public final int dusk = 1;
    public final int night = 2;
    public final int dawn = 3;
    public int dayState = day;


    public Lighting(GamePanel gp){
        this.gp = gp;
        setLightSource();
    }

    public void setLightSource(){
        //Create a buffered image
        darknessFilter = new BufferedImage(gp.screenWith, gp.screenHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D)darknessFilter.getGraphics();

        if(gp.player.currentLight == null){
            g2.setColor(new Color(0,0,0.1f,0.96f));
        }
        else{
            //Get the center x and y of light circle
            int centerX = gp.player.screenX + (gp.tileSize) / 2;
            int centerY = gp.player.screenY + (gp.tileSize) / 2;
 
            //Create a gradient effect
            Color color[] = new Color[12];
            float fraction[] = new float[12];
 
            color[0] = new Color(0,0,0.1f,0.1f);
            color[1] = new Color(0,0,0.1f,0.42f);
            color[2] = new Color(0,0,0.1f,0.52f);
            color[3] = new Color(0,0,0.1f,0.61f);
            color[4] = new Color(0,0,0.1f,0.69f);
            color[5] = new Color(0,0,0.1f,0.76f);
            color[6] = new Color(0,0,0.1f,0.82f);
            color[7] = new Color(0,0,0.1f,0.87f);
            color[8] = new Color(0,0,0.1f,0.91f);
            color[9] = new Color(0,0,0.1f,0.94f);
            color[10] = new Color(0,0,.1f,0.95f);
            color[11] = new Color(0,0,.1f,0.96f);
 
            fraction[0] = 0f;
            fraction[1] = 0.4f;
            fraction[2] = 0.5f;
            fraction[3] = 0.6f;
            fraction[4] = 0.65f;
            fraction[5] = 0.7f;
            fraction[6] = 0.75f;
            fraction[7] = 0.8f;
            fraction[8] = 0.85f;
            fraction[9] = 0.9f;
            fraction[10] = 0.95f;
            fraction[11] = 1f;
 
            //Create a gradiention paint settings for the light circle
            RadialGradientPaint gPaint = new RadialGradientPaint(centerX, centerY, gp.player.currentLight.lightRadius, fraction, color);

            // Set the gradient data on g2
             g2.setPaint(gPaint);
        }
 
        g2.fillRect(0, 0, gp.screenWith, gp.screenHeight);
        g2.dispose();
    }

    public void update(){
        if(gp.player.lightUpdated == true){
            setLightSource();
            gp.player.lightUpdated = false;
        }
        // Check the day state
        if(dayState == day){
            dayCounter++;
            if(dayCounter > 2400){ // muda o tempo para  cada state 
                dayState = dusk;
                dayCounter = 0;
            }
        }
        if(dayState == dusk){
            filterAlpha += 0.001f;

            if(filterAlpha > 1f){
                filterAlpha = 1f;
                dayState = night;
                gp.aSetter.setMonster();
            }    
        }
        if(dayState == night){
            dayCounter++;
            
            if(dayCounter > 2400){
                dayState = dawn;
                dayCounter = 0;
                
            }
        }
        if(dayState == dawn){
            filterAlpha -= 0.0001f;

            if(filterAlpha < 0){
                filterAlpha = 0;
                dayState = day;
            }
        }
    }

    public void draw(Graphics2D g2){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, filterAlpha));
        g2.drawImage(darknessFilter, 0, 0, null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        //DEBUG
        String situation = "";

        switch(dayState){
            case day: situation = "Dia"; break;
            case dusk: situation = "Pôr do sol"; break;
            case night: situation = "Noite"; break;
            case dawn: situation = "Amanhecer"; break;
        }
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(30f));
        g2.drawString(situation, 800, 500);
    }
}