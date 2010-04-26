/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package KhangRobot;


import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;
import robocode.util.Utils;

/**
 *
 * @author Khang Vo
 */
public class Helper {

    public static void shootOnScannedRobot(ScannedRobotEvent e, AdvancedRobot mainRobot) {
        // Click buttons with robot names on the right side to see println messages
        // System.out.println("radar\t" + getRadarHeading());
        // System.out.println("enemy\t" + (getHeading() + e.getBearing()));
        // System.out.println("rem  \t" + getRadarTurnRemainingRadians());

        // Decide how strong the bullet is depending on enemy's distance
        double power;
        double d = e.getDistance();
        power = 3.0 * Math.exp(-0.002 * (d - 200));
        if (power > 3) {
            power = 3;
        } else if (power < 0.1) {
            power = 0.1;
        }

        // Calculate bullet's speed
        double v = 20 - (power * 3);

        // Radar tracking by turning the radar to enemy's last direction
        double radarTurn = mainRobot.getHeadingRadians() + e.getBearingRadians()
                - mainRobot.getRadarHeadingRadians();
        // Chop angle to [-pi..pi] range
        mainRobot.setTurnRadarRightRadians(1.9 * Utils.normalRelativeAngle(radarTurn));

        // Approximate linear aim-ahead assuming velocity vector is constant
        // beta = arcsin(V/v * sin(alpha))
        // beta: angle to turn
        // V: enemy's velocity
        // v: bullet's velocity
        // alpha: angle between enemy's direction from us and enemy's heading
        double gunTurn = mainRobot.getHeadingRadians() + e.getBearingRadians()
                - mainRobot.getGunHeadingRadians();
        double alpha = e.getHeadingRadians() - (mainRobot.getHeadingRadians() + e.getBearingRadians());
        gunTurn += Math.asin(e.getVelocity() * Math.sin(alpha) / v);
        // Chop angle to [-pi..pi] range
        mainRobot.setTurnGunRightRadians(Utils.normalRelativeAngle(gunTurn));

        // Fire after turning the gun
        mainRobot.setFire(power);
    }

    
}
