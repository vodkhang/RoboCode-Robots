package net.sf.robocode.battle.peer.bonus;

public class HealthBonusPeer extends EnergyBonusPeer{
	public static final int ADDITION_ENERGY = 50;
	public HealthBonusPeer(double x, double y) {
		super(x, y, "Health", "health", BonusPeer.STANDARD_TIME_LIFE, 4);		
		additionalImageName = "health";
		additionalEnergy = ADDITION_ENERGY;
		maxNumberAdditionalImage = 4;
	}	
}
