package net.sf.robocode.battle.peer.bonus;

import java.util.Random;

import net.sf.robocode.battle.peer.RobotPeer;

import robocode.BattleRules;


// TODO: we can add a bonusID so that we can easily perform searching or sorting here
public abstract class BonusPeer implements IBonusPeer {
	enum BonusPeerType {
		BonusHealth,
		BonusPoison,
		BonusStrong,
		BonusWeak
	}
	protected double x;
	protected double y;
	protected int numberOfImages = 1;
	private static final int RANDOM_RATE = 30;
	public static final int STANDARD_TIME_LIFE = 100;
	public static final int STANDARD_RADIUS = 40;
	protected String name;
	protected String imageFileName;
	protected int activeTimeLife;
	protected int affectTimeLife;
	protected final int initialTimeLife; 
	private int imageIndex = 0;
	private RobotPeer affectedRobot;
	protected String additionalImageName = null;
	public BonusPeer(double x, double y, String name, String imageFileName, int timeLife, int affectTimeLife) {
		this.x = x;
		this.y = y;		
		this.name = name;
		this.imageFileName = imageFileName;
		this.activeTimeLife = timeLife;
		initialTimeLife = affectTimeLife;
		this.affectTimeLife = initialTimeLife;
	}
	
	public void decrementTimeLife() {
		activeTimeLife --;
	}
	
	public void decrementAffectTimeLife() {
		affectTimeLife --;
	}
	
	public int getImageIndex() {
		// TODO Auto-generated method stub
		return imageIndex ++ % numberOfImages;
	}	
	
	protected void setAffectedRobot(RobotPeer affectedRobot) {
		this.affectedRobot = affectedRobot;
	}
	
	public RobotPeer getAffectedRobot() {
		return affectedRobot;
	}

	/**
	 * Check if this bonus is still active or not
	 * If it is not active, we can remove it out
	 * @return is Active or not
	 */
	public boolean isActive() {
		return activeTimeLife >= 0;
	}
	
	public boolean isAffecting() {
		// TODO Auto-generated method stub
		return (affectTimeLife >= 0 && affectedRobot != null);
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
		return "x: " + x + " y: " + y + " name: " + name + " timeLife " + activeTimeLife;
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
	public void inaffect() {
		affectedRobot.setAdditionalImageName(null);
		affectedRobot = null;
		affectTimeLife = -1;
	}
	/**
	 * The factory to create new bonus peer every turn
	 * @param battleRules
	 * @return
	 */
	public static IBonusPeer createNewBonusPeer(BattleRules battleRules) {
		int random = new Random().nextInt(RANDOM_RATE);
		// we will only create a bonus peer on one-quarter probability
		if (random != 0) {
			return null;
		}
		int randomBonus = new Random().nextInt(BonusPeerType.values().length);
	
		double x = new Random().nextDouble() * battleRules.getBattlefieldWidth();
		double y = new Random().nextDouble() * battleRules.getBattlefieldHeight();
		BonusPeerType type = BonusPeerType.values()[randomBonus];
		switch (type) {
			case BonusHealth : 
				return new HealthBonusPeer(x, y);
			case BonusPoison :
				return new PoisonBonusPeer(x, y);
			case BonusStrong :
				return new StrongBonusPeer(x, y);
			case BonusWeak :
				return new WeakBonusPeer(x, y);
			default:
				System.out.println("The random type is invalid");
				return null;
		}
	}

}
