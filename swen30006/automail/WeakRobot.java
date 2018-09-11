package automail;
import strategies.IMailPool;

public class WeakRobot extends Robot {

	public WeakRobot(IMailDelivery delivery, IMailPool mailPool) {

		super(delivery, mailPool, Constants.CARELESS, Constants.WEAK);
		StorageTube tube = new StorageTube(4, Constants.CARELESS);
		super.createTube(tube);
//		System.out.println("A WeakRobot is created ");
	}

}
