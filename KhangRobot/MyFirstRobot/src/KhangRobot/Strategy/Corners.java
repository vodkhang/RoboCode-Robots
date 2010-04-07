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

import robocode.DeathEvent;
import robocode.HitByBulletEvent;
import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.Robot;
import robocode.ScannedRobotEvent;
import static robocode.util.Utils.normalRelativeAngleDegrees;

import java.awt.*;
import robocode.AdvancedRobot;

/**
 * Corners - a sample robot by Mathew Nelson, and maintained by Flemming N. Larsen
 * <p/>
 * This robot moves to a corner, then swings the gun back and forth.
 * If it dies, it tries a new corner in the next round.
 */
public class Corners implements RobotStrategy {

    int others; // Number of other robots in the game
    static int corner = 0; // Which corner we are currently using
    // static so that it keeps it between rounds.
    boolean stopWhenSeeRobot = false; // See goCorner()
    private AdvancedRobot mainRobot;

    public Corners(AdvancedRobot mainRobot) {
        this.mainRobot = mainRobot;
        // Set colors
        mainRobot.setBodyColor(Color.red);
        mainRobot.setGunColor(Color.black);
        mainRobot.setRadarColor(Color.yellow);
        mainRobot.setBulletColor(Color.green);
        mainRobot.setScanColor(Color.green);

        // Save # of other bots
        others = mainRobot.getOthers();
        // Move to a corner
        goCorner();
        // Initialize gun turn speed to 3

    }

    /**
     * run:  Corners' main run function.
     */
    public void normalRun() {
        // Tell the game that when we take move,
        // we'll also want to turn right... a lot.
        mainRobot.setTurnRight(200);
        // Limit our speed to 5
        mainRobot.setMaxVelocity(15);
        // Start moving (and turning)
        mainRobot.setAhead(200);       
        
        // Spin gun back and forth
        int gunIncrement = 3;
        for (int i = 0; i < 30; i++) {
            mainRobot.setTurnGunLeft(gunIncrement);
        }
        gunIncrement *= -1;
        mainRobot.execute();
    }
    private double getAheadAmountToAvoidWall() {
        // Move to the corner
        
        double currentX = mainRobot.getX();
        double currentY = mainRobot.getY();
        System.out.println("currentX = " + currentX);
        System.out.println("currentY = " + currentY);

        double battleWidth = mainRobot.getBattleFieldWidth();
        double battleHeight = mainRobot.getBattleFieldHeight();

        System.out.println("battleWidth = " + battleWidth);
        System.out.println("battleHeight = " + battleHeight);

        boolean isHitVerticalWall = currentX < 50 || currentX > (battleWidth - 50);
        boolean isHitHorizontalWall  = currentY < 50 || currentY > (battleHeight - 50);

        System.out.println("isHitHorizontalWall = " + isHitHorizontalWall);
        System.out.println("isHitVerticalWall = " + isHitVerticalWall);

        double aheadAmount = 0;
        // you are on the vertical wall
        if(isHitVerticalWall) {
            double heading = mainRobot.getHeading();
            System.out.println("heading = " + heading);
            if(heading == 0) {
                aheadAmount = battleWidth - currentY - 100;
            } else if (heading == 180) {
                aheadAmount = currentY - 100;
            }
        } else if (isHitHorizontalWall) {
            double heading = mainRobot.getHeading();
            System.out.println("heading = " + heading);
            if(heading == 90) {
                aheadAmount = battleHeight - currentX - 100;
            } else if (heading == 270) {
                aheadAmount = currentX - 100;
            }
        }
        System.out.println("aheadAmount = " + aheadAmount);
        return aheadAmount;
    }
    /**
     * goCorner:  A very inefficient way to get to a corner.  Can you do better?
     */
    public void goCorner() {
        // We don't want to stop when we're just turning...
        stopWhenSeeRobot = false;
        // turn to face the wall to the "right" of our desired corner.
        mainRobot.turnRight(normalRelativeAngleDegrees(corner - mainRobot.getHeading()));
        // Ok, now we don't want to crash into any robot in our way...
        stopWhenSeeRobot = true;
        // Move to that wall
        mainRobot.ahead(300);
        
        // Turn to face the corner
        mainRobot.turnLeft(90);
        mainRobot.ahead(getAheadAmountToAvoidWall());
        mainRobot.turnLeft(20);
        mainRobot.back(100);
        // Turn gun to starting point
        mainRobot.turnGunLeft(90);
    }

    /**
     * onScannedRobot:  Stop and fire!
     */
    public void onScannedRobot(ScannedRobotEvent e) {
        // Should we stop, or just fire?
        if (stopWhenSeeRobot) {
            // Stop everything!  You can safely call stop multiple times.
            //mainRobot.stop();
            // Call our custom firing method
            smartFire(e.getDistance());
            // Look for another robot.
            // NOTE:  If you call scan() inside onScannedRobot, and it sees a robot,
            // the game will interrupt the event handler and start it over
            mainRobot.scan();
            // We won't get here if we saw another robot.
            // Okay, we didn't see another robot... start moving or turning again.
            //mainRobot.resume();
        } else {
            smartFire(e.getDistance());
        }
    }

    /**
     * smartFire:  Custom fire method that determines firepower based on distance.
     *
     * @param robotDistance the distance to the robot to fire at
     */
    public void smartFire(double robotDistance) {
        if (robotDistance > 200 || mainRobot.getEnergy() < 15) {
            mainRobot.fire(1);
        } else if (robotDistance > 50) {
            mainRobot.fire(2);
        } else {
            mainRobot.fire(3);
        }
    }

    /**
     * onDeath:  We died.  Decide whether to try a different corner next game.
     */
    public void onDeath(DeathEvent e) {
        // Well, others should never be 0, but better safe than sorry.
        if (others == 0) {
            return;
        }

        // If 75% of the robots are still alive when we die, we'll switch corners.
        if ((others - mainRobot.getOthers()) / (double) others < .75) {
            corner += 90;
            if (corner == 270) {
                corner = -90;
            }
            System.out.println("I died and did poorly... switching corner to " + corner);
        } else {
            System.out.println("I died but did well.  I will still use corner " + corner);
        }
    }

    public void onHitByBullet(HitByBulletEvent e) {
    }

    public void onHitRobot(HitRobotEvent e) {
    }

    public void onHitWall(HitWallEvent event) {
        System.out.println("Corner hit the wall");
        // Move to the corner
        double currentX = mainRobot.getX();
        double currentY = mainRobot.getY();
        System.out.println("currentX = " + currentX);
        System.out.println("currentY = " + currentY);        
    }
}
