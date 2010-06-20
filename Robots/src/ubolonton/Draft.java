package ubolonton;

import java.awt.Color;

import robocode.Robot;

public class Draft extends Robot {
	public void run() {
		setColors(Color.red,Color.blue,Color.green);
		//setAdjustGunForRobotTurn(true);
		//setAdjustRadarForGunTurn(true);
		//setAdjustRadarForRobotTurn(true);
	    while(true) {
	    	ahead(500);
	    	back(500);
		}
	}

}
