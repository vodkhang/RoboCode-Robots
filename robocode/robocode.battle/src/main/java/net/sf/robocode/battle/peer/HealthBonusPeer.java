package net.sf.robocode.battle.peer;

public class HealthBonusPeer extends BonusPeer{
	private static final int STANDARD_TIME_LIFE = 100;
	private static final double STANDARD_RADIUS = 50;
	private static final int ADDITION_ENERGY = 200;
	public HealthBonusPeer(double x, double y) {
		super(x, y);
		name = "Poision";
		imageFileName = "health.png";
		timeLife = STANDARD_TIME_LIFE;
	}
	
	public boolean applyBonusToRobot(RobotPeer robot) {
//		System.out.println("x :" + x + " y: " + y + " robotX: " + robot.getX() + " robotY: " + robot.getY());
		boolean isInXArea = (x - STANDARD_RADIUS < robot.getX()) && (x + STANDARD_RADIUS > robot.getX());
		boolean isInYArea = (y - STANDARD_RADIUS < robot.getY()) && (y + STANDARD_RADIUS > robot.getY());
		if (!isInXArea || !isInYArea) {
			return false;
		}
		System.out.println("HealthBonusPeer, applyBonusToRobot, can apply");
		robot.addEnergy(ADDITION_ENERGY);
		timeLife = -1;
		return true;		
	}

}
