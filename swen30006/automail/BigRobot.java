package automail;
import strategies.IMailPool;

public class BigRobot extends Robot {
    public BigRobot(IMailDelivery delivery, IMailPool mailPool) {

        super(delivery, mailPool, Constants.CARELESS, Constants.STRONG);
        StorageTube tube = new StorageTube(6, Constants.CARELESS);
        super.createTube(tube);
//        System.out.println("A BigRobot is created ");
    }
}
