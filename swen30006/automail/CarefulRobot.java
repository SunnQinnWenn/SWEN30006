package swen30006.automail;
import swen30006.exceptions.ExcessiveDeliveryException;
import swen30006.exceptions.FragileItemBrokenException;
import swen30006.exceptions.ItemTooHeavyException;
import swen30006.strategies.IMailPool;

public class CarefulRobot extends Robot {

    // a boolean flag to check if robot is taking the first step
    private boolean firstStep;

    public CarefulRobot(IMailDelivery delivery, IMailPool mailPool) {
        super(delivery, mailPool, Constants.CAREFUL, Constants.STRONG);

        int tubeSize = 3;
        StorageTube tube = new StorageTube(tubeSize, Constants.CAREFUL);
        super.setTube(tube);

        firstStep = true;
    }



    /**
     * Generic function that moves the robot towards the destination
     * @param destination the floor towards which the robot is moving
     */
    public void moveTowards(int destination) throws FragileItemBrokenException {
        // careful robot only moves if it is not the first step
        if (!firstStep){
            super.moveTowards(destination);
            firstStep = true;
        } else {
            firstStep = false;
        }
    }

}
