package net.sf.robocode.battle.peer.bonus;

import net.sf.robocode.battle.peer.RobotPeer;

public class PoisonBonusPeer extends EnergyBonusPeer{	
	public PoisonBonusPeer(double x, double y) {		
		super(x, y, "Poison", "poison", BonusPeer.STANDARD_TIME_LIFE, 4);		
		additionalImageName = "poison";
		maxNumberAdditionalImage = 10;
	}
	public void affect() {
		if (isAffecting()) {
			RobotPeer robot = getAffectedRobot();			
			if (robot.getEnergy() > 50) {
				additionalEnergy = -20;
			} else {
				additionalEnergy = (int) (robot.getEnergy()/2 - robot.getEnergy());
			}
			super.affect();
		}
	}
//	public boolean applyBonusToRobot(RobotPeer robot) {
//		// TODO Auto-generated method stub
//		if (isRobotInRange(robot, affectRadius)) {
//			if (robot.getEnergy() > 50) {
//				robot.updateEnergy(additionalEnergy);
//			} else {
//				robot.updateEnergy(robot.getEnergy()/2 - robot.getEnergy());
//			}
//			activeTimeLife = -1;
//			return true;
//		}
//		return false;
//	}
}