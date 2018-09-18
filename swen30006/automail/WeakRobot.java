package swen30006.automail;
import swen30006.strategies.IMailPool;

public class WeakRobot extends Robot {

	public WeakRobot(IMailDelivery delivery, IMailPool mailPool) {
		super(delivery, mailPool, Constants.CARELESS, Constants.WEAK);

		int tubeSize = 4;
		StorageTube tube = new StorageTube(tubeSize, Constants.CARELESS);
		super.setTube(tube);
	}

}
