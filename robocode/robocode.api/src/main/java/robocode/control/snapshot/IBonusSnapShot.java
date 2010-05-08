package robocode.control.snapshot;

import java.awt.Shape;
// vodkhang@gmail.com

/**
 * 
 */
public interface IBonusSnapShot {
	/**
	 * Get the name of the snapshot to display
	 * @return : name of the snapshot
	 */
	public String getName();
	
	/**
	 * Get the x position of the snapshot
	 * @return x position
	 */
	public double getX();
	
	/**
	 * Get the y position of the snapshot
	 * @return y position
	 */
	public double getY();

	/**
	 * Get the image name to draw image easier
	 * @return name of the image to display
	 */
	public String getImageFileName();
	/**
	 * 
	 * Used for temprorarily only, be careful yourself
	 * @return the shape to draw
	 */
//	public Shape getShape();

}
//FINISH