package net.sf.robocode.battle.peer.bonus;

import net.sf.robocode.battle.peer.RobotPeer;

public abstract class StrengthPowerBonusPeer  extends BonusPeer{
	private static final int STRENGTH_AFFECT_TIME_LIFE = 100;
	public static final int AFFECTED_RADIUS = 50;
	public static final double BONUS_STRONG = 3;
	public static final double WEAK_STRONG = 0.4;
	private static final double NORMAL_STRONG = 1;		
	private double strongPowerFactor; 
	protected int affectedRadius = BonusPeer.STANDARD_RADIUS;		
	
	public StrengthPowerBonusPeer(double x, double y, String name, String imageFileName, 
			int timeLife, double powerFactor) {
		super(x, y, name, imageFileName, timeLife, STRENGTH_AFFECT_TIME_LIFE);
		strongPowerFactor = powerFactor;
	}

	public void affect() {
		if (isAffecting()) {			
			getAffectedRobot().setBulletPowerFactor(strongPowerFactor);
			getAffectedRobot().setAdditionalImageName(additionalImageName);
		}		
	}

	public boolean applyBonusToRobot(RobotPeer robot) {
		if (isRobotInRange(robot, affectedRadius)) {
			setAffectedRobot(robot);
			getAffectedRobot().setBulletPowerFactor(strongPowerFactor);			
			activeTimeLife = -1;
			return true;
		}		
		return false;
	}
	
	public void inaffect() {
		// TODO Auto-generated method stub
		getAffectedRobot().setBulletPowerFactor(NORMAL_STRONG);
		super.inaffect();				
	}
}
