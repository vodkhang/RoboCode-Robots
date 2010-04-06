/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package KhangRobot.Strategy;

/**
 * Feel free to copy the code, but give me credit for the aiming code
 * Author: Nguyen Tuan Anh (ubolonton)
 * ubolonton.blotspot.com
 */
import KhangRobot.Helper;
import KhangRobot.KhangRobot;
import robocode.*;
import robocode.util.Utils;

import java.awt.Color;

public class Indian implements RobotStrategy {
    private int count = 0; // counting variable for the crazy movement
    private double lastEnemyEnergy = 0;
    private double dir = 1; // 1 means running forward, -1 backward
    private AdvancedRobot mainRobot;

    public Indian(AdvancedRobot mainRobot) {
        this.mainRobot = mainRobot;
        mainRobot.setAllColors(Color.red);
        mainRobot.setBulletColor(Color.red);

        // Make robot, gun, and radar turn independently of each other
        if (mainRobot instanceof KhangRobot) {
            ((KhangRobot) mainRobot).setAllAdjustTrue();
        }
        // Turn the radar around as fast as possible (the limit is 45 degree/tick)
        mainRobot.setTurnRadarRightRadians(Double.POSITIVE_INFINITY);
    }

    public void normalRun() {
        // Bizarre movement
        System.out.println(count++);
        if (mainRobot.getDistanceRemaining() == 0 && mainRobot.getTurnRemaining() == 0) {
            if (count % 50 < 8)
                mainRobot.setAhead(30*dir);
            else
                 mainRobot.setAhead(10*dir);

            if (count % 50 < 8) {
                mainRobot.setTurnLeft(10);
            } else {
                mainRobot.setTurnRight(10);
            }
        }

        // onScannedRobot is not sent if the enemy stands still
        // So we need to explicitly call scan()
        mainRobot.scan();

        // If somehow the radar stops, we must turn it again
        if (mainRobot.getRadarTurnRemaining() == 0) {
            mainRobot.setTurnRadarLeft(Double.POSITIVE_INFINITY);
        }
    }
    
    /**
     * onScannedRobot: What to do when you see another robot
     */
    public void onScannedRobot(ScannedRobotEvent e) {
        Helper.shootOnScannedRobot(e, mainRobot);

        // Keep track of enemy's energy assuming 1-on-1
        double enemyEnergyLost = lastEnemyEnergy - e.getEnergy();
        lastEnemyEnergy = e.getEnergy();

        if (mainRobot.getDistanceRemaining() > 5 || mainRobot.getTurnRemaining() > 0) {
            // If we are in a motion already, simple continue with it
            // 5 is for a smooth movement, we don't want to completely stop before moving again
            return;
        } else if (enemyEnergyLost < 3 && enemyEnergyLost > 1) {
            // Assume they fired if energy lost is 1-3
            // Quickly go aside, but not exactly perpendicular
            double tankTurn = e.getBearing() + 60;
            System.out.println("They fired!");
            mainRobot.setTurnRight(Utils.normalRelativeAngleDegrees(tankTurn));
            // NOTE: changing direction like this invalidated the above comment if dir < 0
            mainRobot.setAhead(100*dir);
        } else if (e.getDistance() > 100) {
            // Too far from them
            // Go toward, but not straight-forward, slightly to the side
            double tankTurn = e.getBearing() + 25;
            mainRobot.setTurnRight(Utils.normalRelativeAngleDegrees(tankTurn));
            // NOTE: changing direction like this invalidated the above comment if dir < 0
            mainRobot.setAhead(30*dir);
        } else {
            // Not too far, not being fired at
            // Circle them (90 means perpendicular to their direction)
            double tankTurn = e.getBearing() + 90;
            mainRobot.setTurnRight(Utils.normalRelativeAngleDegrees(tankTurn));
            mainRobot.setAhead(30*dir);
        }
        
        // Do all the above then scan
        mainRobot.execute();
        mainRobot.scan();
    }

    /**
     * onHitByBullet: What to do when you're hit by a bullet
     */
    public void onHitByBullet(HitByBulletEvent e) {
        // Nothing here yet
        //turnLeft(90 - e.getBearing());
    }

    public void onHitRobot(HitRobotEvent e) {
    }

    public void onHitWall(HitWallEvent event) {
        dir *= -1;
    }
}

