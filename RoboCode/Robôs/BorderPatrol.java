package myrobots;
import robocode.*;

public class BorderPatrol extends Robot {
    public void run() {
        goToCorner();
        turnLeft(getHeading() % 90);
        while (true) {
            moveAlongWall();
        }
    }

    private void goToCorner() {
        turnLeft(getHeading());
        ahead(getY());
        turnRight(90);
        ahead(getX());
        turnLeft(90);
    }

    private void moveAlongWall() {
        ahead(100000);
        turnRight(90);
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        fire(1);
    }

    public void onHitWall(HitWallEvent e) {
        back(20);
        turnRight(90);
    }
}
