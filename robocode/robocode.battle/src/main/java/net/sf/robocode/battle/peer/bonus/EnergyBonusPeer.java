package net.sf.robocode.battle.peer.bonus;

import net.sf.robocode.battle.peer.RobotPeer;

public abstract class EnergyBonusPeer extends BonusPeer{
	private static final int ENERGY_AFFECT_TIME_LIFE = 4;
	protected int affectRadius = BonusPeer.STANDARD_RADIUS;
	protected int additionalEnergy = 0;
	protected int maxNumberAdditionalImage;
	
	public EnergyBonusPeer(double x, double y, String name, String imageName, int timeLife, int numberOfImages) {
		super(x, y, name, imageName, timeLife, ENERGY_AFFECT_TIME_LIFE);
		this.numberOfImages = numberOfImages;
	}
	public boolean applyBonusToRobot(RobotPeer robot) {
		if (isRobotInRange(robot, affectRadius)) {
			setAffectedRobot(robot);
			activeTimeLife = -1;
			return true;		
		}
		return false;
	}
	/**
	 * Because it is a one time affect so do not do anything
	 */
	public void affect() {
		// TODO Auto-generated method stub
		if (isAffecting()) {
			RobotPeer robot = getAffectedRobot();			
			robot.updateEnergy(additionalEnergy/initialTimeLife);
			robot.setAdditionalImageName(additionalImageName);			
		}
	}
}
