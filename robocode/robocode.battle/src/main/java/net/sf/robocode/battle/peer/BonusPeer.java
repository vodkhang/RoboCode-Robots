package net.sf.robocode.battle.peer;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.Random;

import robocode.BattleRules;

import net.sf.robocode.battle.Battle;
// TODO: we can add a bonusID so that we can easily perform searching or sorting here
public class BonusPeer {
	enum BonusPeerType {
		HealthIncrease,
		HealthDecrease,
		PowerIncrease,
		PowerDecrease
	}
	private double x;
	private double y;
	private static final int ADDITION_ENERGY = 50;
	private static final int STANDARD_TIME_LIFE = 30;
	private static final double STANDARD_RADIUS = 3;
	private String name;
	private String imageName;
	private Shape shape;
	private BonusPeerType type;
	private int timeLife;
	public BonusPeer(double x, double y, BonusPeerType type) {
		this.x = x;
		this.y = y;
		this.type = type;
		generateProperties(type);		
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
		return timeLife == 0;
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



	public String getImageName() {
		return imageName;
	}

	public Shape getShape() {
		return shape;
	}

	private void generateProperties(BonusPeerType type) {
		switch (type) {
			case HealthIncrease :
				name = "Health Bonus";
				imageName = "health_increase.jpg";
				shape = new Ellipse2D.Double(x, y, STANDARD_RADIUS, STANDARD_RADIUS);
				timeLife = STANDARD_TIME_LIFE;
			default:
				System.out.println("Not support yet");
		}
	}
	public static BonusPeer createNewBonusPeer(BattleRules battleRules) {
		int random = new Random().nextInt(10);
		// we will only create a bonus peer on one-quarter probability
		if (random != 0) {
			return null;
		}
		//int random = new Random().nextInt(BonusPeerType.values().length);		
		double x = new Random().nextDouble() * battleRules.getBattlefieldWidth();
		double y = new Random().nextDouble() * battleRules.getBattlefieldHeight();
		return new BonusPeer(x, y, BonusPeerType.HealthIncrease);
	}
	public void applyBonusToRobot(RobotPeer robot) {
		boolean isInXArea = x - STANDARD_RADIUS < robot.getX() && x + STANDARD_RADIUS > robot.getX();
		boolean isInYArea = y - STANDARD_RADIUS < robot.getY() && y + STANDARD_RADIUS > robot.getY();
		if (!isInXArea || !isInYArea) {
			return;
		}
		switch (type) {
			case HealthIncrease:
				robot.addEnergy(ADDITION_ENERGY);
			default:
				System.out.println("We did not implement other types");
		}
	}
}
