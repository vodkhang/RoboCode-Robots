package net.sf.robocode.battle.peer;

public class PoisonBonusPeer extends BonusPeer{
	private static final int TIME_LIFE_LIMIT = 200;
	private static final int AFFECTED_RADIUS = 80;
	private static final int SUBTRACT_HEALTH = -50;
	public PoisonBonusPeer(double x, double y) {		
		super(x, y);
		name = "Poison";
		imageFileName = "poison.png";
		timeLife = TIME_LIFE_LIMIT;
	}
	
	public boolean applyBonusToRobot(RobotPeer robot) {
		// TODO Auto-generated method stub
		if (isRobotInRange(robot, AFFECTED_RADIUS)) {
			if (robot.getEnergy() > 50) {
				robot.addEnergy(SUBTRACT_HEALTH);
			} else {
				robot.addEnergy(robot.getEnergy()/2 - robot.getEnergy());
			}
			timeLife = -1;
			return true;
		}
		return false;
	}

}
