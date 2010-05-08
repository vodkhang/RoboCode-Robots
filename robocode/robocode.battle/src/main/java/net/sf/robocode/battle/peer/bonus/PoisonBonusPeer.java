package net.sf.robocode.battle.peer.bonus;

import net.sf.robocode.battle.peer.RobotPeer;

public class PoisonBonusPeer extends BonusPeer{
	private static final int TIME_LIFE_LIMIT = 200;
	private static final int AFFECTED_RADIUS = 80;
	private static final int SUBTRACT_HEALTH = -50;
	public PoisonBonusPeer(double x, double y) {		
		super(x, y, "Poison", "poison", TIME_LIFE_LIMIT);		
		numberOfImages = 4;
	}
	
	public boolean applyBonusToRobot(RobotPeer robot) {
		// TODO Auto-generated method stub
		if (isRobotInRange(robot, AFFECTED_RADIUS)) {
			if (robot.getEnergy() > 50) {
				robot.updateEnergy(SUBTRACT_HEALTH);
			} else {
				robot.updateEnergy(robot.getEnergy()/2 - robot.getEnergy());
			}
			timeLife = -1;
			return true;
		}
		return false;
	}
	/**
	 * This bonus is one time affect so always return false
	 */
	public boolean isAffecting() {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * Because it is one time affect so it does not do anything
	 */
	public void affect() {
		// TODO Auto-generated method stub		
	}

	public void inaffect() {
		// TODO Auto-generated method stub
		
	}

}
