/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package KhangRobot.Strategy;

import KhangRobot.KhangRobot;
import java.awt.Color;
import robocode.AdvancedRobot;
import robocode.HitByBulletEvent;
import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;
import robocode.util.Utils;

/**
 *
 * @author Khang Vo
 */
public class RamStrategy implements RobotStrategy {

    private boolean isRobotHit = false;
    private int turnDirection = 1; // Clockwise or counterclockwise
    private boolean isRobotScanned = false;
    private AdvancedRobot mainRobot;

    public RamStrategy(AdvancedRobot mainRobot) {
        this.mainRobot = mainRobot;
        // Make robot, gun, and radar turn independently of each other
        if (mainRobot instanceof KhangRobot) {
            ((KhangRobot) mainRobot).setAllAdjustFalse();
        }
        mainRobot.turnGunRight(mainRobot.getHeading()-mainRobot.getGunHeading());
        
        mainRobot.setAllColors(Color.BLUE);
    }

    /**
     * Default behaviour
     */
    public void normalRun() {
        isRobotHit = false;
        // if we did not find the robot, we go ahead and turn,
        // if already found, just turn
        if (!isRobotScanned) {
            mainRobot.setAhead(10);
        }
        isRobotScanned = false;
        mainRobot.setTurnRight(5 * turnDirection);
        mainRobot.execute();
    }

    /**
     * Found it, go ahead, ram him and rape him
     */
    public void onScannedRobot(ScannedRobotEvent e) {
        if (isRobotScanned) {
            double absoluteBearing = mainRobot.getHeading() + e.getBearing();
            double bearingFromGun = Utils.normalRelativeAngleDegrees(absoluteBearing
                    - mainRobot.getGunHeading());
            mainRobot.setTurnRight(bearingFromGun);
            // We check gun heat here, because calling fire()
            // uses a turn, which could cause us to lose track
            // of the other robot.
            if (mainRobot.getGunHeat() == 0) {
                mainRobot.setFire(Math.min(3 - Math.abs(bearingFromGun), mainRobot.getEnergy() - .1));
                mainRobot.execute();
            }
            return;
        }
        // if found it, turn to it and shoot
        if (e.getBearing() >= 0) {
            turnDirection = 1;
        } else {
            turnDirection = -1;
        }

        mainRobot.setTurnRight(e.getBearing());
        mainRobot.setAhead(e.getDistance() + 5);
        //setMaxVelocity(50);
        // if the distance is small enough, kill him        
        if (e.getDistance() <= 200) {
            mainRobot.setFire(1.5);
        } else {
            mainRobot.setFire(1);
        }
        mainRobot.execute();
        isRobotScanned = true;
        // find him again
        mainRobot.scan(); // Might want to move ahead again!

    }

    /**
     * We will not do anything yet when hit by a bullet
     */
    public void onHitByBullet(HitByBulletEvent e) {
    }

    public void onHitRobot(HitRobotEvent e) {
        // turn into that robot
        if (e.getBearing() >= 0) {
            turnDirection = 1;
        } else {
            turnDirection = -1;
        }
        mainRobot.turnRight(e.getBearing());
        double robotEnergy = mainRobot.getEnergy();
        double opponentEnergy = e.getEnergy();
        boolean isHeavyRam = false;
        if (robotEnergy > opponentEnergy + 50) {
            isHeavyRam = true;
        }
        // When we already hit one time, we will continue hit, so shoot
        // strong to kill
        if (!isHeavyRam) {
            if (isRobotHit) {
                mainRobot.setFire(3);
            } else {
                mainRobot.setFire(2);
            }
        } else {
            //mainRobot.setFire(0.5);
        }
//        System.out.println("isHitRobot = " + isHitRobot);
        // Ram him again!
        mainRobot.setAhead(40); // Ram him again!
        isRobotHit = true;
        mainRobot.execute();
    }

    /**
     * We will never hit a wall with our current strategy
     * @param event
     */
    public void onHitWall(HitWallEvent event) {
    }

    @Override
    public String toString() {
        return "Ram Strategy";
    }
}
