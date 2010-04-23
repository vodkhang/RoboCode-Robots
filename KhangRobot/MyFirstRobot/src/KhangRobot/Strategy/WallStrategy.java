/*******************************************************************************
 * Copyright (c) 2001, 2010 Mathew A. Nelson and Robocode contributors
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://robocode.sourceforge.net/license/epl-v10.html
 *
 * Contributors:
 *     Mathew A. Nelson
 *     - Initial implementation
 *     Flemming N. Larsen
 *     - Maintainance
 *******************************************************************************/
package KhangRobot.Strategy;

import KhangRobot.Helper;
import robocode.HitByBulletEvent;
import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.Robot;
import robocode.ScannedRobotEvent;

import java.awt.*;
import robocode.AdvancedRobot;

/**
 * WallStrategy - a sample robot by Mathew Nelson, and maintained by Flemming N. Larsen
 * <p/>
 * Moves around the outer edge with the gun facing in.
 */
public class WallStrategy implements RobotStrategy {

    boolean peek; // Don't turn if there's a robot there
    double moveAmount; // How much to move
    private AdvancedRobot mainRobot;

    public WallStrategy(AdvancedRobot mainRobo) {
        this.mainRobot = mainRobo;
        System.out.println("mainRobo = " + mainRobo);
        mainRobot.setBodyColor(Color.black);
        mainRobot.setGunColor(Color.black);
        mainRobot.setRadarColor(Color.RED);
        mainRobot.setBulletColor(Color.cyan);
        mainRobot.setScanColor(Color.cyan);

        // mainRobot.set colors
        

        // Initialize moveAmount to the maximum possible for this battlefield.
        moveAmount = Math.max(mainRobot.getBattleFieldWidth(), mainRobot.getBattleFieldHeight());
        // Initialize peek to false
        peek = false;

        // turnLeft to face a wall.
        // getHeading() % 90 means the remainder of
        // getHeading() divided by 90.
//        double currentX = mainRobot.getX();
//        double currentY = mainRobot.getY();
//        double distanceX = currentX - mainRobot.getBattleFieldWidth() / 2;
//        double distanceY
        

        mainRobot.turnLeft(mainRobot.getHeading() % 90);
        mainRobot.ahead(moveAmount);
        // Turn the gun to turn right 90 degrees.
        peek = true;
        mainRobot.turnGunRight(90);
        mainRobot.turnRight(90);
    }

    /**
     * run: Move around the walls
     */
    public void normalRun() {
        // Look before we turn when ahead() completes.
        peek = true;
        // Move up the wall
        mainRobot.scan();
        mainRobot.ahead(moveAmount);
        // Don't look now
        //peek = false;
        // Turn to the next wall
        mainRobot.scan();
        mainRobot.turnRight(90);
    }

    /**
     * onHitRobot:  Move away a bit.
     */
    public void onHitRobot(HitRobotEvent e) {
        // If he's in front of us, mainRobot.set back up a bit.
        if (e.getBearing() > -90 && e.getBearing() < 90) {
            mainRobot.back(100);
        } // else he's in back of us, so mainRobot.set ahead a bit.
        else {
            mainRobot.ahead(100);
        }
    }

    /**
     * onScannedRobot:  Fire!
     */
    public void onScannedRobot(ScannedRobotEvent e) {
        Helper.shootOnScannedRobot(e, mainRobot);
//        mainRobot.fire(1);
//        mainRobot.fire(1);
//        mainRobot.fire(1);
        // Note that scan is called automatically when the robot is moving.
        // By calling it manually here, we make sure we generate another scan event if there's a robot on the next
        // wall, so that we do not start moving up it until it's gone.
        //if (peek) {
            mainRobot.scan();
        //}
    }

    public void onHitByBullet(HitByBulletEvent e) {
    }

    public void onHitWall(HitWallEvent event) {
    }
}
