/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package KhangRobot.Strategy;

import robocode.BulletHitEvent;
import robocode.HitByBulletEvent;
import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;

/**
 *
 * @author Khang Vo
 */
public interface RobotStrategy {
    public void onScannedRobot(ScannedRobotEvent e);
    public void onHitByBullet(HitByBulletEvent e) ;
    public void onHitRobot(HitRobotEvent e);
    public void onHitWall(HitWallEvent event) ;
    public void normalRun() ;
    public void onBulletHit(BulletHitEvent e);
}
