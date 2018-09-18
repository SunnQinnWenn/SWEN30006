package swen30006.automail;

import swen30006.strategies.IMailPool;

public class StandardRobot extends Robot {

	public StandardRobot(IMailDelivery delivery, IMailPool mailPool) {
		super(delivery, mailPool, Constants.CARELESS, Constants.STRONG);

		int tubeSize = 4;
		StorageTube tube = new StorageTube(tubeSize, Constants.CARELESS);
		super.setTube(tube);

	}

}
