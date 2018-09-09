package automail;

import strategies.IMailPool;

public class StrongRobot extends Robot {

	public StrongRobot(IMailDelivery delivery, IMailPool mailPool) {
		super(delivery, mailPool);
		// TODO Auto-generated constructor stub
		this.setStrong(Constants.STRONG);
		this.createTube(new StorageTube(4, Constants.CARELESS));
	}

}
