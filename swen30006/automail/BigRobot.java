package swen30006.automail;
import swen30006.strategies.IMailPool;

public class BigRobot extends Robot {
    public BigRobot(IMailDelivery delivery, IMailPool mailPool) {
        super(delivery, mailPool, Constants.CARELESS, Constants.STRONG);

        int tubeSize = 6;
        StorageTube tube = new StorageTube(tubeSize, Constants.CARELESS);
        super.setTube(tube);
    }
}
