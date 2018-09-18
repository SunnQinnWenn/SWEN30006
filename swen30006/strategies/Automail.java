package swen30006.strategies;

import java.util.ArrayList;
import java.util.List;

import swen30006.automail.*;

public class Automail {
	      
	// use an array list to store the robots
    public ArrayList<Robot> robots;

    public IMailPool mailPool;

    // use a robotFactory to create robots
    private RobotFactory robotFactory;


    public Automail(IMailPool mailPool, IMailDelivery delivery, List<Simulation.RobotType> robotTypes) {
    	    	
    	// Initialize the MailPool, robots array and robot factory
    	this.mailPool = mailPool;
		this.robots = new ArrayList<Robot>();
		this.robotFactory = new RobotFactory();

		// Traverse the robot types, initialise robots accordingly and add them to the list
    	for (Simulation.RobotType type : robotTypes){
			this.robots.add(robotFactory.createRobot(delivery, mailPool, (Simulation.RobotType) type));
		}

    }
    
}
