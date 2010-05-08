package net.sf.robocode.battle.peer.bonus;

import net.sf.robocode.battle.peer.RobotPeer;

public abstract class StrengthPowerBonusPeer  extends BonusPeer{
	private static final int AFFECT_TIME_LIFE = 100;
	private static final int AFFECTED_RADIUS = 50;
	public static final double BONUS_STRONG = 5;
	public static final double WEAK_STRONG = 0.2;
	private static final double NORMAL_STRONG = 1;
	private RobotPeer affectedRobot;
	private int affectTimeLife = -1;
	private double strongPowerFactor; 
	public StrengthPowerBonusPeer(double x, double y, String name, String imageFileName, 
			int timeLife, double powerFactor) {
		super(x, y, name, imageFileName, timeLife);
		strongPowerFactor = powerFactor;
	}
	@Override
	public void decrementTimeLife() {
		// TODO Auto-generated method stub
		super.decrementTimeLife();
		affectTimeLife --;
	}
	public void affect() {
		if (isAffecting() && affectedRobot != null) {
			affectedRobot.setBulletPowerFactor(strongPowerFactor);
		}		
	}

	public boolean applyBonusToRobot(RobotPeer robot) {
		if (isRobotInRange(robot, AFFECTED_RADIUS)) {
			affectedRobot = robot;
			affectedRobot.setBulletPowerFactor(strongPowerFactor);
			affectTimeLife = AFFECT_TIME_LIFE;
			timeLife = -1;
			return true;
		}		
		return false;
	}

	public boolean isAffecting() {
		// TODO Auto-generated method stub		
		return affectTimeLife >= 0;
	}
	public void inaffect() {
		// TODO Auto-generated method stub
		affectTimeLife = -1;
		affectedRobot.setBulletPowerFactor(NORMAL_STRONG);
		affectedRobot = null;		
	}
}
