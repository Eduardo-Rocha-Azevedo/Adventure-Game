package principal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config {
    GamePanel gp;

    public Config(GamePanel gp){
        this.gp = gp;
    }

    public void saveConfig(){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("config.txt"));
            //Full Screen
            if(gp.fullScreen == true){
                bw.write("On");
            } 
            if(gp.fullScreen == false){
                bw.write("Off");
            }
            bw.newLine();

            //Music volume
            bw.write(String.valueOf(gp.music.volumeScale));
            bw.newLine();

            //SE volume
            bw.write(String.valueOf(gp.se.volumeScale));
            bw.newLine();

            bw.close();

        } catch(IOException e){

        }
       
    }

    public void loadConfig(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("config.txt"));
            String s = br.readLine();

            //Full Screen
            if(s.equals("On")){
                gp.fullScreen = true;
            }
            if(s.equals("Off")){
                gp.fullScreen = false;
            }

            //Music volume
            s = br.readLine();
            gp.music.volumeScale = Integer.parseInt(s);

            //SE volume
            s = br.readLine();
            gp.se.volumeScale = Integer.parseInt(s);

            br.close();
        } catch (Exception e) {
            
        }
    }
}
