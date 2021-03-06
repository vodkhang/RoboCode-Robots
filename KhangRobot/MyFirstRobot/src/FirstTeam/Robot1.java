package FirstTeam;

import KhangRobot.KhangRobot;
import KhangRobot.StrategyFactory;
import java.awt.Color;
import robocode.HitRobotEvent;
import robocode.ScannedRobotEvent;

public class Robot1 extends KhangRobot {
    // the strategy - one strategy

    @Override
    protected void setStrategy() {
        robotStrategy = StrategyFactory.wallWhenCrowdedIndian(this, 5);
    }

    @Override
    public void onScannedRobot(ScannedRobotEvent e) {
        if (isTeammate(e.getName() + "*")) {
            setTurnRadarRightRadians(Double.POSITIVE_INFINITY);
            return;
        }
        super.onScannedRobot(e);
    }

    @Override
    public void onHitRobot(HitRobotEvent e) {
        if (isTeammate(e.getName() + "*")) {
            // back up quickly
            setAhead(-10*getVelocity());
        }
        super.onHitRobot(e);
    }

}
