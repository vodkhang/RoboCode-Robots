package net.sf.robocode.battle.peer;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.Random;

import robocode.BattleRules;


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
	private static final int ADDITION_ENERGY = 200;
	private static final int RANDOM_RATE = 60;
	private static final int STANDARD_TIME_LIFE = 100;
	private static final double STANDARD_RADIUS = 50;
	private String name;
	private String imageName;
//	private Shape shape;
	private BonusPeerType type;
	private int timeLife;
	public BonusPeer(double x, double y, BonusPeerType type) {
		this.x = x;
		this.y = y;
		this.type = type;
		generateProperties(type);		
//		System.out.println("BonusPeer - constructor: " + this);
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
		return timeLife > 0;
	}
	
	public double getX() {
		return x;
	}

	public BonusPeerType getType() {
		return type;
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

//	public Shape getShape() {
//		return shape;
//	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "x: " + x + " y: " + y + " name: " + name + " timeLife " + timeLife;
	}
	private void generateProperties(BonusPeerType type) {
		switch (type) {
			case HealthIncrease :
				name = "Health";
				imageName = "health_increase.jpg";				
				timeLife = STANDARD_TIME_LIFE;
				break;
			default:
				System.out.println("Not support yet");
		}
	}
	public static BonusPeer createNewBonusPeer(BattleRules battleRules) {
		int random = new Random().nextInt(RANDOM_RATE);
		// we will only create a bonus peer on one-quarter probability
		if (random != 0) {
			return null;
		}
		//int random = new Random().nextInt(BonusPeerType.values().length);		
		double x = new Random().nextDouble() * battleRules.getBattlefieldWidth();
		double y = new Random().nextDouble() * battleRules.getBattlefieldHeight();
		return new BonusPeer(x, y, BonusPeerType.HealthIncrease);
	}
	public boolean applyBonusToRobot(RobotPeer robot) {		
		boolean isInXArea = (x - STANDARD_RADIUS < robot.getX()) && (x + STANDARD_RADIUS > robot.getX());
		boolean isInYArea = (y - STANDARD_RADIUS < robot.getY()) && (y + STANDARD_RADIUS > robot.getY());
		if (!isInXArea || !isInYArea) {
			return false;
		}
		switch (type) {
			case HealthIncrease:
				robot.addEnergy(ADDITION_ENERGY);
				timeLife = -1;
				return true;
			default:
				System.out.println("We did not implement other types");
				return true;
		}
	}
}
