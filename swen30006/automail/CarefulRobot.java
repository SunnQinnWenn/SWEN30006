package automail;
import exceptions.ExcessiveDeliveryException;
import exceptions.FragileItemBrokenException;
import exceptions.ItemTooHeavyException;
import strategies.IMailPool;

public class CarefulRobot extends Robot {

    private boolean firstStep;

    public CarefulRobot(IMailDelivery delivery, IMailPool mailPool) {
        super(delivery, mailPool, Constants.CAREFUL, Constants.STRONG);
        StorageTube tube = new StorageTube(3, Constants.CAREFUL);
        super.createTube(tube);

        firstStep = true;
    }

    // The CarefulRobot will not move at the first step
    public void step() throws ExcessiveDeliveryException, ItemTooHeavyException, FragileItemBrokenException {
        if (this.current_state == RobotState.DELIVERING) {
        	if (firstStep) {
        		firstStep = false;
        	}
        	else {
        		super.step();
        		firstStep = true;
        	}
        }
        else if (this.current_state == RobotState.RETURNING) {
        	if (firstStep) {
        		firstStep = false;
        	}
        	else {
        		super.step();
        		firstStep = true;
        	}
        }
        else if (this.current_state == RobotState.WAITING) {
        	firstStep = true;
        	super.step();
        }
        else {
            super.step();
        }
        

    }
}
