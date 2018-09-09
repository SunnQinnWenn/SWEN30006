package automail;

import strategies.IMailPool;

public class BigRobot extends Robot {

	public BigRobot(IMailDelivery delivery, IMailPool mailPool) {
		super(delivery, mailPool);
		// TODO Auto-generated constructor stub
		this.setStrong(Constants.STRONG);
		this.createTube(new StorageTube(6, Constants.CARELESS));
	}

}
