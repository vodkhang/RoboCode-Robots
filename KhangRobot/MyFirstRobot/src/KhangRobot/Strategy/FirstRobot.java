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

public class FirstRobot implements RobotStrategy {
    // Counting variable for the crazy movement

    private int count = 0;
    private AdvancedRobot mainRobot;

    public FirstRobot(AdvancedRobot mainRobot) {
        this.mainRobot = mainRobot;
        mainRobot.setAllColors(Color.yellow);
        mainRobot.setBulletColor(Color.yellow);

        // Make robot, gun, and radar turn independently of each other
        if (mainRobot instanceof KhangRobot) {
            ((KhangRobot) mainRobot).setAllAdjustTrue();
        }
        // Turn the radar around as fast as possible (the limit is 45 degree/tick)
        mainRobot.setTurnRadarRightRadians(Double.POSITIVE_INFINITY);
    }

    public void normalRun() {
        // Our color
        // Bizarre movement
        count ++; 
        if (count % 50 < 8) {
            mainRobot.setAhead(30);
        } else {
            mainRobot.setAhead(10);
        }

        if (count % 50 < 8) {
            mainRobot.setTurnLeft(10);
        } else {
            mainRobot.setTurnRight(10);
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
        mainRobot.setTurnLeft(60);
        mainRobot.ahead(100);
        mainRobot.execute();
    }

    public void onBulletHit(BulletHitEvent e) {
    }
}

