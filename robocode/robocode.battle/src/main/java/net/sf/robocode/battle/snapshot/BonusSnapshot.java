package net.sf.robocode.battle.snapshot;

import net.sf.robocode.battle.peer.bonus.IBonusPeer;

import robocode.control.snapshot.IBonusSnapShot;
// vodkhang@gmail.com
public class BonusSnapshot implements IBonusSnapShot{
	// image name file
	private String imageName;
	
	// name of the bonus
	private String name;
	
	// shape of the bonus
	//private Shape shape;	
	
	// coordinate of the bonus
	private double x;	
	private double y;
	
	private int imageIndex;
	public BonusSnapshot (IBonusPeer bonusPeer) {
		x = bonusPeer.getX();
		y = bonusPeer.getY();
		name = bonusPeer.getName();
		imageName = bonusPeer.getImageFileName();
		imageIndex = bonusPeer.getImageIndex();
		//shape = bonusPeer.getShape();
	}
	/**
	 * {@inheritDoc}
	 */
	public String getImageFileName() {
		// TODO Auto-generated method stub
		return imageName;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	public double getX() {
		// TODO Auto-generated method stub
		return x;
	}

	public double getY() {
		// TODO Auto-generated method stub
		return y;
	}
	public int getImageIndex() {
		return imageIndex;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "x: " + x + " y: " + " name: " + name;
	}
}
//FINISH