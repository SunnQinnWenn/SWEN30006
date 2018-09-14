package swen30006.automail;

import swen30006.strategies.IMailPool;

public class RobotFactory {

	public Robot createRobot(IMailDelivery delivery, IMailPool mailPool, Simulation.RobotType robotType){

	    switch (robotType) {
            case Standard:
                return new StandardRobot(delivery, mailPool);

            case Weak:
                return new WeakRobot(delivery, mailPool);

            case Big:
                return new BigRobot(delivery, mailPool);

            case Careful:
                return new CarefulRobot(delivery, mailPool);

        }
        return null;
    }
}
