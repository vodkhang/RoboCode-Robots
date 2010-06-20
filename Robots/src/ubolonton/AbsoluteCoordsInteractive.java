/*******************************************************************************
 * Copyright (c) 2001, 2010 Mathew A. Nelson and Robocode contributors
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://robocode.sourceforge.net/license/epl-v10.html
 *
 * Contributors:
 *     Flemming N. Larsen
 *     - Initial implementation
 *     Nguyễn Tuấn Anh
 *     - Modified controls
 *******************************************************************************/
package ubolonton;


import robocode.AdvancedRobot;
import robocode.util.Utils;
import static robocode.util.Utils.normalAbsoluteAngle;
import static robocode.util.Utils.normalRelativeAngle;

import java.awt.*;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.HashSet;
import java.util.Set;

/**
 * Interactive - a sample robot by Flemming N. Larsen.
 * <p/>
 * This is a robot that is controlled using the arrow keys and mouse only.
 * <p/>
 * Keys:
 * - Arrow up:    Move forward
 * - Arrow down:  Move backward
 * - Arrow right: Turn right
 * - Arrow left:  Turn left
 * Mouse:
 * - Moving:      Moves the aim, which the gun will follow
 * - Wheel up:    Move forward
 * - Wheel down:  Move backward
 * - Button 1:    Fire a bullet with power = 1
 * - Button 2:    Fire a bullet with power = 2
 * - Button 3:    Fire a bullet with power = 3
 * <p/>
 * The bullet color depends on the fire power:
 * - Power = 1:   Yellow
 * - Power = 2:   Orange
 * - Power = 3:   Red
 * <p/>
 * Note that the robot will continue firing as long as the mouse button is
 * pressed down.
 * <p/>
 * By enabling the "Paint" button on the robot console window for this robot,
 * a cross hair will be painted for the robots current aim (controlled by the
 * mouse).
 *
 * @author Flemming N. Larsen
 * @version 1.1
 * @since 1.3.4
 */
public class AbsoluteCoordsInteractive extends AdvancedRobot {

	// The coordinate of the aim (x,y)
	int aimX, aimY;

	// Fire power, where 0 = don't fire
	int firePower;
	
	private enum Keys {
		UP, DOWN, LEFT, RIGHT
	}
	
	private final Set<Keys> keys = new HashSet<Keys>();
	
	// Called when the robot must run
	public void run() {

		// Sets the colors of the robot
		// body = black, gun = white, radar = red
		setColors(Color.BLACK, Color.WHITE, Color.RED);

		// Loop forever
		for (;;) {
			setAhead(distanceToRun());

			setTurnRight(angleToTurnInDegrees());

			// Turns the gun toward the current aim coordinate (x,y) controlled by
			// the current mouse coordinate
			double angle = normalAbsoluteAngle(Math.atan2(aimX - getX(), aimY - getY()));

			setTurnGunRightRadians(normalRelativeAngle(angle - getGunHeadingRadians()));

			// Fire the gun with the specified fire power, unless the fire power = 0
			if (firePower > 0) {
				setFire(firePower);
			}

			// Execute all pending set-statements
			execute();

			// Next turn is processed in this loop..
		}
	}

	// Called when a key has been pressed
	public void onKeyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case VK_UP:
		case VK_W:
			keys.add(Keys.UP);
			break;
		case VK_DOWN:
		case VK_S:
			keys.add(Keys.DOWN);
			break;
		case VK_RIGHT:
		case VK_D:
			keys.add(Keys.RIGHT);
			break;
		case VK_LEFT:
		case VK_A:
			keys.add(Keys.LEFT);
			break;
		}
	}

	// Called when a key has been released (after being pressed)
	public void onKeyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case VK_UP:
		case VK_W:
			keys.remove(Keys.UP);
			break;
		case VK_DOWN:
		case VK_S:
			keys.remove(Keys.DOWN);
			break;
		case VK_RIGHT:
		case VK_D:
			keys.remove(Keys.RIGHT);
			break;
		case VK_LEFT:
		case VK_A:
			keys.remove(Keys.LEFT);
			break;
		}
	}

	// Called when the mouse wheel is rotated
	public void onMouseWheelMoved(MouseWheelEvent e) {
	}

	// Called when the mouse has been moved
	public void onMouseMoved(MouseEvent e) {
		// Set the aim coordinate = the mouse pointer coordinate
		aimX = e.getX();
		aimY = e.getY();
	}

	// Called when a mouse button has been pressed
	public void onMousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON3) {
			// Button 3: fire power = 3 energy points, bullet color = red
			firePower = 3;
			setBulletColor(Color.RED);
		} else if (e.getButton() == MouseEvent.BUTTON2) {
			// Button 2: fire power = 2 energy points, bullet color = orange
			firePower = 2;
			setBulletColor(Color.ORANGE);
		} else {
			// Button 1 or unknown button:
			// fire power = 1 energy points, bullet color = yellow
			firePower = 1;
			setBulletColor(Color.YELLOW);
		}
	}

	// Called when a mouse button has been released (after being pressed)
	public void onMouseReleased(MouseEvent e) {
		// Fire power = 0, which means "don't fire"
		firePower = 0;
	}

	// Called in order to paint graphics for this robot.
	// "Paint" button on the robot console window for this robot must be
	// enabled in order to see the paintings.
	public void onPaint(Graphics2D g) {
		// Draw a red cross hair with the center at the current aim
		// coordinate (x,y)
		g.setColor(Color.RED);
		g.drawOval(aimX - 15, aimY - 15, 30, 30);
		g.drawLine(aimX, aimY - 4, aimX, aimY + 4);
		g.drawLine(aimX - 4, aimY, aimX + 4, aimY);
	}

	private double angleToTurnInDegrees() {
		if (keys.isEmpty()) {
			return 0;
		}
		return Utils.normalRelativeAngleDegrees(desiredDirection() - getHeading());
	}
	
	private double distanceToRun() {
		if (keys.isEmpty()) {
			return 0;
		}
		if (Math.abs(angleToTurnInDegrees()) > 45) {
			return 5;
		}
		return Double.POSITIVE_INFINITY;
	}
	
	private double desiredDirection() {
		if (keys.contains(Keys.UP)) {
			if (keys.contains(Keys.RIGHT)) {
				return 45;
			}
			if (keys.contains(Keys.LEFT)) {
				return 315;
			}
			return 0;
		}
		if (keys.contains(Keys.DOWN)) {
			if (keys.contains(Keys.RIGHT)) {
				return 135;
			}
			if (keys.contains(Keys.LEFT)) {
				return 225;
			}
			return 180;
		}
		if (keys.contains(Keys.RIGHT)) {
			return 90;
		}
		if (keys.contains(Keys.LEFT)) {
			return 270;
		}
		return 0;
	}
}
