package swen30006.automail;

import swen30006.strategies.IMailPool;

public class StandardRobot extends Robot {

	public StandardRobot(IMailDelivery delivery, IMailPool mailPool) {

		super(delivery, mailPool, Constants.CARELESS, Constants.STRONG);
		StorageTube tube = new StorageTube(4, Constants.CARELESS);
		super.createTube(tube);
//		System.out.println("A Standard is created ");
	}

}
