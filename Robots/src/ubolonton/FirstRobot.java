/**
 * Feel free to copy the code, but give me credit for the aiming code
 * Author: Nguyen Tuan Anh (ubolonton)
 * ubolonton.blogspot.com
 */

package ubolonton;

import java.awt.Color;

import robocode.AdvancedRobot;
import robocode.HitByBulletEvent;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;
import robocode.util.Utils;

public class FirstRobot extends AdvancedRobot {
    private int count = 0; // counting variable for the crazy movement
    private double lastEnemyEnergy = 0;
    private double dir = 1; // 1 means running forward, -1 backward

    public void run() {
        // Our color
        setColors(Color.red, Color.blue, Color.cyan);
        setBulletColor(Color.yellow);

        // Make robot, gun, and radar turn independently of each other
        setAdjustGunForRobotTurn(true);
        setAdjustRadarForGunTurn(true);
        setAdjustRadarForRobotTurn(true);

        // Turn the radar around as fast as possible (the limit is 45
        // degree/tick)
        setTurnRadarRightRadians(Double.POSITIVE_INFINITY);

        // The movement loop
        while (true) {
            // FIXME: this seems to be run only after the battle ends, why???
            // Bizarre movement
            //System.out.println(count++);
            if (getDistanceRemaining() == 0 && getTurnRemaining() == 0) {
                if (count % 50 < 8)
                    setAhead(30*dir);
                else
                    setAhead(10*dir);

                if (count % 50 < 8) {
                    setTurnLeft(10);
                } else {
                    setTurnRight(10);
                }
            }

            // onScannedRobot is not sent if the enemy stands still
            // So we need to explicitly call scan()
            execute();
            scan();

            // If somehow the radar stops, we must turn it again
            if (getRadarTurnRemaining() == 0) {
                setTurnRadarLeft(Double.POSITIVE_INFINITY);
            }
        }
    }

    /**
     * onScannedRobot: What to do when we see another robot
     */
    public void onScannedRobot(ScannedRobotEvent e) {

        // Click buttons with robot names on the right side to see println
        // messages
        // System.out.println("radar\t" + getRadarHeading());
        // System.out.println("enemy\t" + (getHeading() + e.getBearing()));
        // System.out.println("rem  \t" + getRadarTurnRemainingRadians());

        // Decide how strong the bullet is depending on enemy's distance
        double power;
        double d = e.getDistance();
        power = 3.0 * Math.exp(-0.002 * (d - 50));
        if (power > 3)
            power = 3;
        else if (power < 0.1)
            power = 0.1;

        // Calculate bullet's speed
        double v = 20 - (power * 3);

        // Radar tracking by turning the radar to enemy's last direction
        double radarTurn = getHeadingRadians() + e.getBearingRadians() - getRadarHeadingRadians();
        // Chop angle to [-pi..pi] range
        setTurnRadarRightRadians(1.9 * Utils.normalRelativeAngle(radarTurn));

        // Approximate linear aim-ahead assuming velocity vector is constant
        // beta = arcsin(V/v * sin(alpha))
        // beta: angle to turn
        // V: enemy's velocity
        // v: bullet's velocity
        // alpha: angle between enemy's direction from us and enemy's heading
        double gunTurn = getHeadingRadians() + e.getBearingRadians() - getGunHeadingRadians();
        double alpha = e.getHeadingRadians() - (getHeadingRadians() + e.getBearingRadians());
        gunTurn += Math.asin(e.getVelocity() * Math.sin(alpha) / v);
        // Chop angle to [-pi..pi] range
        setTurnGunRightRadians(Utils.normalRelativeAngle(gunTurn));

        // Fire after turning the gun
        // If power is less than 2, there's a chance we don't shoot
        // TODO: this should be based directly on distance
        if (Math.random() < power/2) {
//            if (getGunHeat() == 0.0) {
//                try {
//                	AudioInputStream inputStream = AudioSystem.getAudioInputStream(this.getClass().getResource("splat.wav"));
//                	Clip clip = AudioSystem.getClip();
//                	clip.open(inputStream);
//                	clip.loop(0);
//                } catch (Exception ex) {
//                	ex.printStackTrace();
//                }
//            }
            setFire(power);
      }

        // Keep track of enemy's energy assuming 1-on-1
        double enemyEnergyLost = lastEnemyEnergy - e.getEnergy();
        lastEnemyEnergy = e.getEnergy();

        if (getDistanceRemaining() > 5 || getTurnRemaining() > 0) {
            // If we are in a motion already, simple continue with it
            // 5 is for a smooth movement, we don't want to completely stop before moving again
            return;
        } else if (enemyEnergyLost < 3 && enemyEnergyLost > 1) {
            // Assume they fired if energy lost is 1-3
            // Quickly go aside, but not exactly perpendicular
            double tankTurn = e.getBearing() + 60;
            System.out.println("They fired!");
            setTurnRight(Utils.normalRelativeAngleDegrees(tankTurn));
            // NOTE: changing direction like this invalidated the above comment if dir < 0
            setAhead(100*dir);
        } else if (d > 100) {
            // Too far from them
            // Go toward, but not straight-forward, slightly to the side
            double tankTurn = e.getBearing() + 25;
            setTurnRight(Utils.normalRelativeAngleDegrees(tankTurn));
            // NOTE: changing direction like this invalidated the above comment if dir < 0
            setAhead(30*dir);
        } else {
            // Not too far, not being fired at
            // Circle them (90 means perpendicular to their direction)
            double tankTurn = e.getBearing() + 90;
            setTurnRight(Utils.normalRelativeAngleDegrees(tankTurn));
            setAhead(30*dir);           
        }
        
        // Do all the above then scan
        execute();
        scan();
    }

    /**
     * onHitByBullet: What to do when we're hit by a bullet
     */
    public void onHitByBullet(HitByBulletEvent e) {
        // Nothing here yet
        // turnLeft(90 - e.getBearing());
        //dir *= -1;
    }

    /**
     * onHitWall: What to do when we hit the wall
     */
    public void onHitWall(HitWallEvent e) {
        // Go back if we hit the wall
        dir *= -1;
    }
}
