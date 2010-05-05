/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package KhangRobot.Strategy;

import KhangRobot.KhangRobot;
import java.awt.Color;
import robocode.AdvancedRobot;
import robocode.BulletHitEvent;
import robocode.HitByBulletEvent;
import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;
import robocode.util.Utils;

/**
 *
 * @author lonton
 */
public class SuperWall implements RobotStrategy {
	private static int HGShots;     //Number of shots with Head-On Targeting
	private static int LGShots;     //Number of shots with Linear Targeting
	private static int HGHits;      //Number of hits with Head-On Targeting
	private static int LGHits;      //Number of hits with Linear Targeting
	private boolean gunIdent;       //Used to tell which gun we are using
	private int dir = 1;
	private double energy;
	private static int enemyFireCount = 0;
    private int count = 0;
    private AdvancedRobot robot;

    public SuperWall(AdvancedRobot robot) {
        this.robot = robot;
        robot.setAllColors(Color.black);
        robot.setBulletColor(Color.black);
		robot.setBodyColor(Color.black);
		robot.setGunColor(Color.black);
		robot.setRadarColor(Color.orange);
		robot.setBulletColor(Color.cyan);
		robot.setScanColor(Color.cyan);

        // Make robot, gun, and radar turn independently of each other
        if (robot instanceof KhangRobot) {
            ((KhangRobot) robot).setAllAdjustTrue();
        }
        // Turn the radar around as fast as possible (the limit is 45 degree/tick)
        robot.setTurnRadarRightRadians(Double.POSITIVE_INFINITY);
    }

    public void normalRun() {

		robot.setTurnRadarRight(Double.POSITIVE_INFINITY);
		// turnLeft to face a wall.
		// getHeading() % 90 means the remainder of
		// getHeading() divided by 90.
		robot.turnLeft(robot.getHeading() % 90);

		while (true) {
			//if (getDistanceRemaining() == 0) {
			//	turnRight(90 * dir);
				if (Utils.isNear(robot.getHeadingRadians(), 0D) || Utils.isNear(robot.getHeadingRadians(), Math.PI)) {
					robot.ahead((Math.max(robot.getBattleFieldHeight() - robot.getY(), robot.getY()) - 28) * dir);
				} else {
					robot.ahead((Math.max(robot.getBattleFieldWidth() - robot.getX(), robot.getX()) - 28) * dir);
				}
			robot.turnRight(90 * dir);
			//}
		}
    }

    public void onHitByBullet(HitByBulletEvent e) {
    }

    public void onHitRobot(HitRobotEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void onHitWall(HitWallEvent event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void onScannedRobot(ScannedRobotEvent e) {
		double absBearing = e.getBearingRadians() + robot.getHeadingRadians();                // The enemies location relative to us
		double latVel = e.getVelocity() * Math.sin(e.getHeadingRadians() - absBearing); // The enemies lateral velocity
		double radarTurn = absBearing - robot.getRadarHeadingRadians();                       // The amount to turn our radar

		double HGRating = (double) HGHits / HGShots;
		double LGRating = (double) LGHits / LGShots;

		if (energy > (energy = e.getEnergy())) {
			enemyFireCount++;
			if (enemyFireCount % 5 == 0) {
				dir = -dir;
				if (Utils.isNear(robot.getHeadingRadians(), 0D) || Utils.isNear(robot.getHeadingRadians(), Math.PI)) {
					robot.setAhead((Math.max(robot.getBattleFieldHeight() - robot.getY(), robot.getY()) - 28) * dir);
				} else {
					robot.setAhead((Math.max(robot.getBattleFieldWidth() - robot.getX(), robot.getX()) - 28) * dir);
				}
			}
		}

		robot.setMaxVelocity(Math.random() * 12);

		if ((robot.getRoundNum() == 0 || LGRating > HGRating) && robot.getRoundNum() != 1){ // In the first round or when linear gun got more hitting percentage use linear targeting
			double bulletPower = Math.min(3, e.getEnergy() / 4);
			robot.setTurnGunRightRadians(Utils.normalRelativeAngle(absBearing - robot.getGunHeadingRadians() + Math.asin(latVel / (20 - 3 * bulletPower))));
			LGShots++;
			gunIdent = true;
			robot.setFire(bulletPower); // Fire the minimum amount of energy needed to finish off the other robot
		} else { // in second round or when the head-on gun got more hitting percentage, use head-on gun.
			robot.setTurnGunRightRadians(Utils.normalRelativeAngle(absBearing - robot.getGunHeadingRadians()));
			HGShots++;
			gunIdent = false;
			robot.setFire(e.getEnergy() / 4); // Fire the minimum amount of energy needed to finish off the other robot
		}
		robot.setTurnRadarRightRadians(Utils.normalRelativeAngle(radarTurn) * 2); // Make the radar lock on
    }

	public void onBulletHit(BulletHitEvent e) {
		if(gunIdent) {
			LGHits = LGHits+1;
		} else {
			HGHits = HGHits+1;
		}
	}
}
