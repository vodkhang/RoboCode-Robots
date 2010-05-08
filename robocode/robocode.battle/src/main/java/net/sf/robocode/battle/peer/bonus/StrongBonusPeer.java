package net.sf.robocode.battle.peer.bonus;

public class StrongBonusPeer extends StrengthPowerBonusPeer {
	private static final int ACTIVE_TIME_LIFE = 100;	
	public StrongBonusPeer(double x, double y) {
		super(x, y, "strong", "strong", ACTIVE_TIME_LIFE, StrengthPowerBonusPeer.BONUS_STRONG);	
		numberOfImages = 4;
	}
}
