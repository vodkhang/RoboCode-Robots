/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package KhangRobot;

import KhangRobot.Strategy.RamStrategy;
import KhangRobot.Strategy.RobotStrategy;
import KhangRobot.Strategy.SpinBot;
import KhangRobot.Strategy.Ubolonto;
import KhangRobot.Strategy.WallStrategy;

/**
 *
 * @author Khang Vo
 */
public class StrategyFactory {

    private static boolean isTimedOut = false;

    public static void setIsTimedOut(boolean isTimedOut) {
        StrategyFactory.isTimedOut = isTimedOut;
    }

    private static RobotStrategy getRamStrategy(KhangRobot khangRobot) {
        RobotStrategy robotStrategy = khangRobot.getRobotStrategy();
        if (!(robotStrategy instanceof RamStrategy)) {
            robotStrategy = new RamStrategy(khangRobot);
        }
        return robotStrategy;
    }

    private static RobotStrategy getFirstRobotStrategy(KhangRobot khangRobot) {
        RobotStrategy robotStrategy = khangRobot.getRobotStrategy();
        if (!(robotStrategy instanceof Ubolonto)) {
            robotStrategy = new Ubolonto(khangRobot);
        }
        return robotStrategy;
    }

    private static RobotStrategy getSpinBotStrategy(KhangRobot khangRobot) {
        RobotStrategy robotStrategy = khangRobot.getRobotStrategy();
        if (!(robotStrategy instanceof SpinBot)) {
            robotStrategy = new SpinBot(khangRobot);
        }
        return robotStrategy;
    }

    private static RobotStrategy getWallStrategy(KhangRobot khangRobot) {
        RobotStrategy robotStrategy = khangRobot.getRobotStrategy();
        if (!(robotStrategy instanceof WallStrategy)) {
            robotStrategy = new WallStrategy(khangRobot);
        }
        return robotStrategy;
    }

    /**
     * 
     * @param khangRobot
     * @return
     */
    private static RobotStrategy favorRamStrategy(KhangRobot khangRobot) {
        // in this factory method, I tried to favor ramming strategy
        RobotStrategy robotStrategy = khangRobot.getRobotStrategy();
        if (robotStrategy == null) {
            return new RamStrategy(khangRobot);
        }
        double energy = khangRobot.getEnergy();
        double energyOpponentMoreEnergy = khangRobot.getOpponentEnergy() - energy;

        if (energyOpponentMoreEnergy < 10) {
            return robotStrategy;
        }
        if (energy > 60 && energyOpponentMoreEnergy < 20) {
            // seems that this guy does not against ram strategy a lot. Go ahead
            return getRamStrategy(khangRobot);
        }

        if (energy < 60 && Math.abs(energyOpponentMoreEnergy) < 15) {
            // seems that this guy is also a ram strategy
            // we change to anti ram
            return getFirstRobotStrategy(khangRobot);
        }
        if (energy <= 40) {
            // When we have a really low energy, the best thing is withdraw from the match
            //  and wait for the chances
            return getSpinBotStrategy(khangRobot);
        }
        return robotStrategy;
    }

    public static RobotStrategy favorFirstRobotStrategy(KhangRobot khangRobot) {
        double energy = khangRobot.getEnergy();
        double opponentEnergy = khangRobot.getOpponentEnergy();
        double energyMoreThanOpponent = energy - opponentEnergy;
        if (Math.abs(energyMoreThanOpponent) < 30) {
            return getFirstRobotStrategy(khangRobot);
        }
        if (energyMoreThanOpponent > 30) {
            return getRamStrategy(khangRobot);
        }
        return getFirstRobotStrategy(khangRobot);
    }

    public static RobotStrategy generateNewStrategy(KhangRobot mainRobot) {
        if (mainRobot.getOthers() > 3) {
            mainRobot.resetTimeout();
            return getFirstRobotStrategy(mainRobot);
        } 
        // if not timed out yet, we favor ram strategy
        if (!isTimedOut) {
            return favorFirstRobotStrategy(mainRobot);
        } else {
            // return a ram strategy
            return getFirstRobotStrategy(mainRobot);
        }
    }
}

