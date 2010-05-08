package net.sf.robocode.battle.peer;

/**
 * An interface over all kinds of Bonuses like Health Bonus, Poision Bonus, Strong Bonus, Weak Bonus
 * @author Khang Vo
 *
 */
public interface IBonusPeer {
	double getX();
	
	double getY();
	
	void decrementTimeLife();
	
	boolean isActive();
	
	String getName();
	
	String getImageFileName();
	
	public boolean applyBonusToRobot(RobotPeer robot);
}
