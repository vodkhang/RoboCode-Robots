package KhangRobot;

import KhangRobot.Strategy.RobotStrategy;
import robocode.*;
import java.awt.Color;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Rules set up:
 *  If it is a ram tank: circle around but keep your guns shoot at him
 *
 */
/**
 * KhangRobot - a robot by KhangVo
 */
public class KhangRobot extends AdvancedRobot {
    // the strategy - one strategy

    private RobotStrategy robotStrategy;
    private double opponentEnergy = 100;
    // We will try to change the strategy when it is long time and there is nothing happen
    public static final int NUMBER_OF_TICK = 700;
    private int tickNumber = NUMBER_OF_TICK;

    public RobotStrategy getRobotStrategy() {
        return robotStrategy;
    }

    public double getOpponentEnergy() {
        return opponentEnergy;
    }

    public void setOpponentEnergy(double opponentEnergy) {
        this.opponentEnergy = opponentEnergy;
    }

    public void setAllAdjustTrue() {
        setAdjustGunForRobotTurn(true);
        setAdjustRadarForGunTurn(true);
        setAdjustRadarForRobotTurn(true);
    }

    public void setAllAdjustFalse() {
        setAdjustGunForRobotTurn(false);
        setAdjustRadarForGunTurn(false);
        setAdjustRadarForRobotTurn(false);
    }

    /**
     * Default behaviour
     */
    @Override
    public void run() {

        setAllColors(Color.blue);
        robotStrategy = StrategyFactory.generateNewStrategy(this);
        while (true) {
            robotStrategy.normalRun();
            setStrategy();
            tickNumber--;

            // make sure that if no event happens. We can stop the game
            if (tickNumber == 0) {
                StrategyFactory.setIsTimedOut(true);
                robotStrategy = StrategyFactory.generateNewStrategy(this);
                resetTimeout();
            }
        }
    }

    public void resetTimeout() {
        tickNumber = NUMBER_OF_TICK;
    }

    private void setStrategy() {
        robotStrategy = StrategyFactory.generateNewStrategy(this);
    }

    /**
     * Found it, go ahead, ram him and rape him
     */
    @Override
    public void onScannedRobot(ScannedRobotEvent e) {
        setOpponentEnergy(e.getEnergy());
        robotStrategy.onScannedRobot(e);
        setStrategy();
    }

    @Override
    public void onBulletHit(BulletHitEvent event) {
        setOpponentEnergy(event.getEnergy());
    }

    /**
     * When we hit by a bullet, call the appropriate method of the strategy and then set
     *   the strategy back again
     */
    @Override
    public void onHitByBullet(HitByBulletEvent e) {
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(this.getClass().getResource("6.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(inputStream);
            clip.loop(0);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        robotStrategy.onHitByBullet(e);
        setStrategy();
    }

    @Override
    public void onHitRobot(HitRobotEvent e) {
        setOpponentEnergy(e.getEnergy());
        robotStrategy.onHitRobot(e);
        setStrategy();
    }

    /**
     * We will never hit a wall with our current strategy
     * @param event
     */
    @Override
    public void onHitWall(HitWallEvent event) {
        robotStrategy.onHitWall(event);
        setStrategy();
    }
}
