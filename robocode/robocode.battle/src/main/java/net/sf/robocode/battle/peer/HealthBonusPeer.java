package net.sf.robocode.battle.peer;

public class HealthBonusPeer extends BonusPeer{
	private static final int STANDARD_TIME_LIFE = 100;
	private static final int STANDARD_RADIUS = 50;
	private static final int ADDITION_ENERGY = 200;
	public HealthBonusPeer(double x, double y) {
		super(x, y);
		name = "Health";
		imageFileName = "health.png";
		timeLife = STANDARD_TIME_LIFE;
	}
	
	public boolean applyBonusToRobot(RobotPeer robot) {
//		System.out.println("x :" + x + " y: " + y + " robotX: " + robot.getX() + " robotY: " + robot.getY());
		if (isRobotInRange(robot, STANDARD_RADIUS)) {
			robot.addEnergy(ADDITION_ENERGY);
			timeLife = -1;
			return true;		
		}
		return false;
	}

}
