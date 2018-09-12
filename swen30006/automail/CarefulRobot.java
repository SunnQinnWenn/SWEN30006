package automail;

import automail.Robot.RobotState;
import exceptions.ExcessiveDeliveryException;
import exceptions.FragileItemBrokenException;
import exceptions.ItemTooHeavyException;
import strategies.IMailPool;

public class CarefulRobot extends Robot {
	boolean delay = true;

	public CarefulRobot(IMailDelivery delivery, IMailPool mailPool) {
		super(delivery, mailPool);
		// TODO Auto-generated constructor stub
		this.setStrong(Constants.STRONG);
		this.setCareful(true);
		this.createTube(new StorageTube(3, Constants.CAREFUL));
	}
	
	@Override
	public void moveTowards(int destination) throws FragileItemBrokenException {
        if(getCurrent_floor() < destination){
        	if (delay) {
        		delay = false;
        	}
        	else {
        		setCurrent_floor(getCurrent_floor() + 1);
        	}
        }
        else{
            setCurrent_floor(getCurrent_floor() - 1);
            if (getCurrent_floor() == Building.MAILROOM_LOCATION) {
            	delay = true;
            }
        }
    }
}
