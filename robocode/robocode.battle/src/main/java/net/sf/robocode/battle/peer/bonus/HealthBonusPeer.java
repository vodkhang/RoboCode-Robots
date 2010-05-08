package net.sf.robocode.battle.peer.bonus;

import net.sf.robocode.battle.peer.RobotPeer;

public class HealthBonusPeer extends BonusPeer{
	private static final int STANDARD_TIME_LIFE = 100;
	private static final int STANDARD_RADIUS = 50;
	private static final int ADDITION_ENERGY = 200;
	public HealthBonusPeer(double x, double y) {
		super(x, y, "Health", "health", STANDARD_TIME_LIFE);		
		numberOfImages = 4;
	}
	
	public boolean applyBonusToRobot(RobotPeer robot) {
		if (isRobotInRange(robot, STANDARD_RADIUS)) {
			robot.updateEnergy(ADDITION_ENERGY);
			timeLife = -1;
			return true;		
		}
		return false;
	}

	/**
	 * This bonus is one time affect so it always return false
	 */
	public boolean isAffecting() {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * Because it is a one time affect so do not do anything
	 */
	public void affect() {
		// TODO Auto-generated method stub		
	}

	public void inaffect() {
		// TODO Auto-generated method stub
		
	}

}
