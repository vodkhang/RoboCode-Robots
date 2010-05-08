package net.sf.robocode.battle.peer;

import java.util.Random;

import robocode.BattleRules;


// TODO: we can add a bonusID so that we can easily perform searching or sorting here
public abstract class BonusPeer implements IBonusPeer {
	enum BonusPeerType {
		BonusHealth,
		BonusPoison,
		PowerIncrease,
		PowerDecrease
	}
	protected double x;
	protected double y;
	private static final int RANDOM_RATE = 100;
	protected String name;
	protected String imageFileName;
	protected int timeLife;
	public BonusPeer(double x, double y) {
		this.x = x;
		this.y = y;		
	}
	
	public void decrementTimeLife() {
		timeLife --;
	}
	
	/**
	 * Check if this bonus is still active or not
	 * If it is not active, we can remove it out
	 * @return is Active or not
	 */
	public boolean isActive() {
		return timeLife >= 0;
	}
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "x: " + x + " y: " + y + " name: " + name + " timeLife " + timeLife;
	}
	
	public static IBonusPeer createNewBonusPeer(BattleRules battleRules) {
		int random = new Random().nextInt(RANDOM_RATE);
		// we will only create a bonus peer on one-quarter probability
		if (random != 0) {
			return null;
		}
		int randomBonus = new Random().nextInt(2);

		//int random = new Random().nextInt(BonusPeerType.values().length);		
		double x = new Random().nextDouble() * battleRules.getBattlefieldWidth();
		double y = new Random().nextDouble() * battleRules.getBattlefieldHeight();
		BonusPeerType type = BonusPeerType.values()[randomBonus];
		switch (type) {
			case BonusHealth : 
				return new HealthBonusPeer(x, y);
			case BonusPoison :
				return new PoisonBonusPeer(x, y);
			default:
				System.out.println("The random type is invalid");
				return null;
		}
	}
	protected boolean isRobotInRange(RobotPeer robot, int affectedRadius) {
		boolean isInXArea = (x - affectedRadius < robot.getX()) && (x + affectedRadius > robot.getX());
		boolean isInYArea = (y - affectedRadius < robot.getY()) && (y + affectedRadius > robot.getY());
		if (!isInXArea || !isInYArea) {
			return false;
		}
		return true;
	}
	public String getImageFileName() {
		// TODO Auto-generated method stub
		return imageFileName;
	}
}
