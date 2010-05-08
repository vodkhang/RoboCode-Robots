package net.sf.robocode.battle.peer.bonus;

import net.sf.robocode.battle.peer.RobotPeer;

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
	
	boolean isAffecting();
	
	String getName();
	
	String getImageFileName();
	int getImageIndex();
	boolean applyBonusToRobot(RobotPeer robot);
	
	void affect(); // used for long time affect on robot
	void inaffect(); // used for stop affecting
}
