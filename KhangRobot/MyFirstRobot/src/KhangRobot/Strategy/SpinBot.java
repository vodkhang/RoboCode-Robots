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

import KhangRobot.Strategy.RobotStrategy;
import robocode.AdvancedRobot;
import robocode.HitByBulletEvent;
import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;

import java.awt.*;
import robocode.BulletHitEvent;

/**
 * SpinBot - a sample robot by Mathew Nelson, and maintained by Flemming N. Larsen
 * <p/>
 * Moves in a circle, firing hard when an enemy is detected
 */
public class SpinBot implements RobotStrategy {

    private AdvancedRobot mainRobot;

    public SpinBot(AdvancedRobot mainRobot) {
        this.mainRobot = mainRobot;
        mainRobot.setAllColors(Color.green);
        mainRobot.setTurnRight(30);
        mainRobot.back(300);
        mainRobot.execute();
    }

    /**
     * SpinBot's run method - Circle
     */
    public void normalRun() {
        // Tell the game that when we take move,
        // we'll also want to turn right... a lot.        
        mainRobot.setTurnRight(200);
        // Limit our speed to 5
        mainRobot.setMaxVelocity(15);
        // Start moving (and turning)
        mainRobot.setAhead(200);
        mainRobot.execute();
    }
    /**
     * onScannedRobot: Fire hard!
     */
    public void onScannedRobot(ScannedRobotEvent e) {
        // we don't fire because this strategy is used for low energy
    }

    /**
     * onHitRobot:  If it's our fault, we'll stop turning and moving,
     * so we need to turn again to keep spinning.
     */
    public void onHitRobot(HitRobotEvent e) {
        mainRobot.setTurnRight(10);
        mainRobot.setBack(100);
        mainRobot.execute();
    }

    public void onHitByBullet(HitByBulletEvent e) {
    }

    public void onHitWall(HitWallEvent event) {
        mainRobot.setTurnRight(10);
        mainRobot.setAhead(200);
    }
    public void onBulletHit(BulletHitEvent e) {
    }
}
