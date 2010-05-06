package net.sf.robocode.battle.snapshot;

import java.awt.Shape;

import net.sf.robocode.battle.peer.BonusPeer;

import robocode.control.snapshot.IBonusSnapShot;
// vodkhang@gmail.com
public class BonusSnapshot implements IBonusSnapShot{
	// image name file
	private String imageName;
	
	// name of the bonus
	private String name;
	
	// shape of the bonus
	private Shape shape;
	
	// coordinate of the bonus
	private double x;	
	private double y;
	
	public BonusSnapshot (BonusPeer bonusPeer) {
		x = bonusPeer.getX();
		y = bonusPeer.getY();
		name = bonusPeer.getName();
		imageName = bonusPeer.getImageName();
		shape = bonusPeer.getShape();
	}
	/**
	 * {@inheritDoc}
	 */
	public String getImageName() {
		// TODO Auto-generated method stub
		return imageName;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	public Shape getShape() {
		// TODO Auto-generated method stub
		return shape;
	}

	public double getX() {
		// TODO Auto-generated method stub
		return x;
	}

	public double getY() {
		// TODO Auto-generated method stub
		return y;
	}
	
}
//FINISH