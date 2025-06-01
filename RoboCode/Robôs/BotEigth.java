package Robot;
import robocode.*;
import java.awt.Color;

public class BotEight extends Robot {
    
    public void run() {
        setBodyColor(Color.blue);
        setGunColor(Color.yellow);
        setRadarColor(Color.green);
        
        setGunColor(Color.black);
        
        while(true) {
            ahead(200);
            turnRight(90);
            ahead(200);
            turnRight(90);
            ahead(200);
            turnRight(90);
            ahead(200);
            
            ahead(200);
            turnLeft(90);
            ahead(200);
            turnLeft(90);
            ahead(200);
            turnLeft(90);
            ahead(200);
        }
    }
}