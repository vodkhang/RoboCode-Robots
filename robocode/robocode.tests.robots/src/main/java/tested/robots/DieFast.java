/*******************************************************************************
 * Copyright (c) 2001, 2010 Mathew A. Nelson and Robocode contributors
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://robocode.sourceforge.net/license/epl-v10.html
 *
 * Contributors:
 *     Pavel Savara
 *     - Initial implementation
 *******************************************************************************/
package tested.robots;


import robocode.Robot;
import robocode.exception.RobotException;


/**
 * @author Pavel Savara (original)
 */
public class DieFast extends Robot {
	public void run() {
		throw new RobotException();
	}
}
