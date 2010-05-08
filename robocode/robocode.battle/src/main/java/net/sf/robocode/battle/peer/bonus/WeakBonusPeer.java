package net.sf.robocode.battle.peer.bonus;

public class WeakBonusPeer extends StrengthPowerBonusPeer {
	private static final int ACTIVE_TIME_LIFE = 100;
	public WeakBonusPeer(double x, double y) {
		super (x, y, "weak", "weak", ACTIVE_TIME_LIFE, StrengthPowerBonusPeer.WEAK_STRONG);
		numberOfImages = 4;
	}	
}
