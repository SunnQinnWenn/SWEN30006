package automail;

import strategies.IMailPool;

public class WeakRobot extends Robot {

	public WeakRobot(IMailDelivery delivery, IMailPool mailPool) {
		super(delivery, mailPool);
		// TODO Auto-generated constructor stub
		this.createTube(new StorageTube(4, Constants.CARELESS));
	}

}
