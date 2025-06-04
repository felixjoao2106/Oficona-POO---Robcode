package myrobots;
import robocode.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class Cshapa extends AdvancedRobot {
    private double moveDirection = 1;

    public void run() {
        setBodyColor(Color.BLACK);
        setGunColor(new Color(128, 0, 128));
        setRadarColor(new Color(102, 0, 153));
        setBulletColor(new Color(204, 0, 255));
        setScanColor(Color.MAGENTA);

        setAdjustGunForRobotTurn(true);
        setAdjustRadarForGunTurn(true);

        while (true) {
            setTurnRadarRight(360);
            execute();
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        double bulletPower = 3.0;
        double enemyAbsoluteBearing = getHeadingRadians() + e.getBearingRadians();

        double enemyX = getX() + e.getDistance() * Math.sin(enemyAbsoluteBearing);
        double enemyY = getY() + e.getDistance() * Math.cos(enemyAbsoluteBearing);

        double enemyHeading = e.getHeadingRadians();
        double enemyVelocity = e.getVelocity();

        double deltaTime = 0;
        double predictedX = enemyX;
        double predictedY = enemyY;

        while ((++deltaTime) * (20 - 3 * bulletPower) < Point2D.distance(getX(), getY(), predictedX, predictedY)) {
            predictedX += Math.sin(enemyHeading) * enemyVelocity;
            predictedY += Math.cos(enemyHeading) * enemyVelocity;

            if (predictedX < 18.0 || predictedY < 18.0 ||
                predictedX > getBattleFieldWidth() - 18.0 ||
                predictedY > getBattleFieldHeight() - 18.0) {
                predictedX = Math.min(Math.max(18.0, predictedX), getBattleFieldWidth() - 18.0);
                predictedY = Math.min(Math.max(18.0, predictedY), getBattleFieldHeight() - 18.0);
                break;
            }
        }

        double theta = Math.atan2(predictedX - getX(), predictedY - getY());
        double gunTurn = robocode.util.Utils.normalRelativeAngle(theta - getGunHeadingRadians());
        setTurnGunRightRadians(gunTurn);

        setTurnRadarRightRadians(robocode.util.Utils.normalRelativeAngle(enemyAbsoluteBearing - getRadarHeadingRadians()) * 2);

        if (getGunHeat() == 0.0 && Math.abs(getGunTurnRemaining()) < 10) {
            setFire(bulletPower);
        }

        double moveAngle = robocode.util.Utils.normalRelativeAngle(e.getBearingRadians() + Math.PI / 2);
        double moveX = Math.sin(getHeadingRadians() + moveAngle) * 100 * moveDirection;
        double moveY = Math.cos(getHeadingRadians() + moveAngle) * 100 * moveDirection;

        if (willHitWall(getX() + moveX, getY() + moveY)) {
            moveDirection *= -1;
        }

        setTurnRightRadians(moveAngle);
        setAhead(140 * moveDirection);
    }

    public void onHitByBullet(HitByBulletEvent e) {
        moveDirection *= -1;
        setAhead(120 * moveDirection);
    }

    public void onHitWall(HitWallEvent e) {
        moveDirection *= -1;
        setBack(100);
    }

    private boolean willHitWall(double x, double y) {
        double margin = 50;
        return x < margin || x > getBattleFieldWidth() - margin || y < margin || y > getBattleFieldHeight() - margin;
    }
}
