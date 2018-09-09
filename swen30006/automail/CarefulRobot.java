package automail;

import strategies.IMailPool;

public class CarefulRobot extends Robot {

	public CarefulRobot(IMailDelivery delivery, IMailPool mailPool) {
		super(delivery, mailPool);
		// TODO Auto-generated constructor stub
		this.setStrong(Constants.STRONG);
		this.createTube(new StorageTube(3, Constants.CAREFUL));
	}

}
