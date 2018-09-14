package swen30006.strategies;

import java.util.ArrayList;
import java.util.List;

import swen30006.automail.*;

public class Automail {
	      

    public ArrayList<Robot> robots;
    public IMailPool mailPool;
    private RobotFactory robotFactory;


    public Automail(IMailPool mailPool, IMailDelivery delivery, final List<? extends Enum<?>> robotTypes) {
    	// Swap between simple provided strategies and your strategies here
    	    	
    	// Initialize the MailPool, robots array and robot factory
    	this.mailPool = mailPool;

		this.robots = new ArrayList<Robot>();

		this.robotFactory = new RobotFactory();

		// Traverse the robot types, initialise robots accordingly and add them to the list
    	for (Enum type : robotTypes){
			this.robots.add(robotFactory.createRobot(delivery, mailPool, (Simulation.RobotType) type));
		}

    }
    
}
