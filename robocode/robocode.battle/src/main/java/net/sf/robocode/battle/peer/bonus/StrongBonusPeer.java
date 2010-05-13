package net.sf.robocode.battle.peer.bonus;

public class StrongBonusPeer extends StrengthPowerBonusPeer {
	public StrongBonusPeer(double x, double y) {
		super(x, y, "strong", "strong", BonusPeer.STANDARD_TIME_LIFE, StrengthPowerBonusPeer.BONUS_STRONG);	
		numberOfImages = 4;
		additionalImageName = "strong";		
	}
}
