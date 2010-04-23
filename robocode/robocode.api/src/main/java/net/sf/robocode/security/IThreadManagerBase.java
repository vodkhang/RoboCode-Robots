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
package net.sf.robocode.security;


import java.io.FileOutputStream;
import java.io.IOException;


/**
 * @author Pavel Savara (original)
 */
public interface IThreadManagerBase {
	boolean isSafeThread();
	FileOutputStream createRobotFileStream(String fileName, boolean append) throws IOException;
}
