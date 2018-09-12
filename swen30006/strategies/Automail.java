package strategies;

import java.util.ArrayList;
import java.util.List;

import automail.IMailDelivery;
import automail.Robot;
import automail.Simulation.RobotType;
import automail.StrongRobot;
import automail.WeakRobot;
import automail.CarefulRobot;
import automail.BigRobot;

public class Automail {
	      
    public Robot[] robot;
    public IMailPool mailPool;
    
    public Automail(IMailPool mailPool, IMailDelivery delivery, final List<? extends Enum<?>> robotTypes) {
    	// Swap between simple provided strategies and your strategies here
    	    	
    	/** Initialize the MailPool */
    	
    	this.mailPool = mailPool;
    	
    	robotTypes.forEach(robo -> System.out.println(robo));
    	
    	/** Initialize robots */
    	robot = new Robot[3];
    	robot[0] = new CarefulRobot(delivery, mailPool);
    	robot[1] = new StrongRobot(delivery, mailPool);
    	robot[2] = new BigRobot(delivery, mailPool);
    }
    
}
