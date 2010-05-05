package FirstTeam;

import KhangRobot.KhangRobot;
import KhangRobot.StrategyFactory;
import java.awt.Color;
import robocode.ScannedRobotEvent;

public class Robot1 extends KhangRobot {
    // the strategy - one strategy

    @Override
    protected void setStrategy() {
        robotStrategy = StrategyFactory.wallWhenCrowdedIndian(this, 3);
    }

    @Override
    public void onScannedRobot(ScannedRobotEvent e) {
        if (isTeammate(e.getName() + "*")) {
            setTurnRadarRightRadians(Double.POSITIVE_INFINITY);
            return;
        }
        super.onScannedRobot(e);
    }


}
