package atirador;
import robocode.*;

public class Atirador extends Robot {
    public void run() {
        double centerX = getBattleFieldWidth() / 2;
        double centerY = getBattleFieldHeight() / 2;

        double dx = centerX - getX();
        double dy = centerY - getY();
        double angleToCenter = Math.toDegrees(Math.atan2(dx, dy));

        turnRight(normalizeBearing(angleToCenter - getHeading()));
        ahead(Math.hypot(dx, dy));

        while (true) {
            turnRadarRight(360);
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        double enemyBearing = e.getBearing();
        double enemyDistance = e.getDistance();
        double enemyVelocity = e.getVelocity();
        double enemyHeading = e.getHeading();

        double absoluteBearing = getHeading() + enemyBearing;

        double bulletPower = 3;
        double bulletSpeed = 20 - 3 * bulletPower;

        double time = enemyDistance / bulletSpeed;

        double predictedX = getX() + enemyDistance * Math.sin(Math.toRadians(absoluteBearing)) + enemyVelocity * time * Math.sin(Math.toRadians(enemyHeading));
        double predictedY = getY() + enemyDistance * Math.cos(Math.toRadians(absoluteBearing)) + enemyVelocity * time * Math.cos(Math.toRadians(enemyHeading));

        double dx = predictedX - getX();
        double dy = predictedY - getY();
        double predictedBearing = Math.toDegrees(Math.atan2(dx, dy));

        double gunTurn = normalizeBearing(predictedBearing - getGunHeading());
        turnGunRight(gunTurn);

        if (getGunHeat() == 0 && Math.abs(gunTurn) < 10) {
            fire(bulletPower);
        }

        double radarTurn = normalizeBearing(absoluteBearing - getRadarHeading());
        turnRadarRight(radarTurn);
    }

    private double normalizeBearing(double angle) {
        while (angle > 180) angle -= 360;
        while (angle < -180) angle += 360;
        return angle;
    }
}
