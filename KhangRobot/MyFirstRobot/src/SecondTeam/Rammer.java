/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SecondTeam;

import robocode.*;
import robocode.util.Utils;
import java.awt.Color;

/**
 *
 * @author lonton
 */
public class Rammer extends AdvancedRobot {
    public static final String INDIAN_NAME = "SecondTeam.Indian";
    private boolean isRobotHit = false;
    private int turnDirection = 1; // Clockwise or counterclockwise
    private boolean isRobotScanned = false;

    public void run() {
        setAllColors(Color.red);
        turnGunRight(getHeading() - getGunHeading());

        setAllColors(Color.BLUE);

        // The movement loop
        while (true) {
            isRobotHit = false;
            // if we did not find the robot, we go ahead and turn,
            // if already found, just turn
            if (!isRobotScanned) {
                setAhead(10);
            }
            isRobotScanned = false;
            setTurnRight(5 * turnDirection);
            execute();
        }
    }

    /**
     * onScannedRobot: What to do when we see another robot
     */
    @Override
    public void onScannedRobot(ScannedRobotEvent e) {
        System.out.println("e.getName() = " + e.getName());
        if (getOthers() > 1) {
            if (e.getName().equals(INDIAN_NAME)) {
                isRobotScanned = false;
                setTurnLeft(180);
                return;
            }
        }
        // NOTE: Robocode's bug?        

        if (isRobotScanned) {
            double absoluteBearing = getHeading() + e.getBearing();
            double bearingFromGun = Utils.normalRelativeAngleDegrees(absoluteBearing
                    - getGunHeading());
            setTurnRight(bearingFromGun);
            // We check gun heat here, because calling fire()
            // uses a turn, which could cause us to lose track
            // of the other robot.
            if (getGunHeat() == 0) {
                setFire(Math.min(3 - Math.abs(bearingFromGun), getEnergy() - .1));
                execute();
            }
            return;
        }
        // if found it, turn to it and shoot
        if (e.getBearing() >= 0) {
            turnDirection = 1;
        } else {
            turnDirection = -1;
        }

        setTurnRight(e.getBearing());
        setAhead(e.getDistance() + 5);
        //setMaxVelocity(50);
        // if the distance is small enough, kill him
        if (e.getDistance() <= 200) {
            setFire(1.5);
        } else {
            setFire(1);
        }
        execute();
        isRobotScanned = true;
        // find him again
        scan(); // Might want to move ahead again!

    }

    @Override
    public void onHitRobot(HitRobotEvent e) {
        // NOTE: Robocode's bug?
        if (e.getName().equals(INDIAN_NAME)) {
            // Oops, hit teammate, sorry go back
            isRobotHit = false;
            setBack(100);
            return;
        }

        // turn into that robot
        if (e.getBearing() >= 0) {
            turnDirection = 1;
        } else {
            turnDirection = -1;
        }
        turnRight(e.getBearing());
        double robotEnergy = getEnergy();
        double opponentEnergy = e.getEnergy();
        boolean isHeavyRam = false;
        if (robotEnergy > opponentEnergy + 50) {
            isHeavyRam = true;
        }
        // When we already hit one time, we will continue hit, so shoot
        // strong to kill
        if (!isHeavyRam) {
            if (isRobotHit) {
                setFire(3);
            } else {
                setFire(2);
            }
        } else {
            //setFire(0.5);
        }
//        System.out.println("isHitRobot = " + isHitRobot);
        // Ram him again!
        setAhead(40); // Ram him again!
        isRobotHit = true;
        execute();
    }
}
