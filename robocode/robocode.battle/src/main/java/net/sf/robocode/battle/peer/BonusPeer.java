package net.sf.robocode.battle.peer;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.Random;

import robocode.BattleRules;


// TODO: we can add a bonusID so that we can easily perform searching or sorting here
public abstract class BonusPeer implements IBonusPeer {
	enum BonusPeerType {
		HealthIncrease,
		HealthDecrease,
		PowerIncrease,
		PowerDecrease
	}
	protected double x;
	protected double y;
	private static final int RANDOM_RATE = 60;
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
		//int random = new Random().nextInt(BonusPeerType.values().length);		
		double x = new Random().nextDouble() * battleRules.getBattlefieldWidth();
		double y = new Random().nextDouble() * battleRules.getBattlefieldHeight();
		return new HealthBonusPeer(x, y);
	}

	public String getImageFileName() {
		// TODO Auto-generated method stub
		return imageFileName;
	}
}
