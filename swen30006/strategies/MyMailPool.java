package swen30006.strategies;

import java.util.LinkedList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.function.Consumer;

import swen30006.automail.MailItem;
import swen30006.automail.PriorityMailItem;
import swen30006.automail.Robot;
import swen30006.automail.StorageTube;
import swen30006.exceptions.TubeFullException;
import swen30006.strategies.IMailPool;
import swen30006.exceptions.FragileItemBrokenException;

public class MyMailPool implements IMailPool {
	private class Item {
		int priority;
		int destination;
		boolean heavy;
		boolean fragile;
		MailItem mailItem;	
		// Use stable sort to keep arrival time relative positions
		
		public Item(MailItem mailItem) {
			priority = (mailItem instanceof PriorityMailItem) ? ((PriorityMailItem) mailItem).getPriorityLevel() : 1;
			heavy = mailItem.getWeight() >= 2000;
			destination = mailItem.getDestFloor();
			this.mailItem = mailItem;
			fragile = mailItem.getFragile();
		}
	}
	
	public class ItemComparator implements Comparator<Item> {
		@Override
		public int compare(Item i1, Item i2) {
			int order = 0;
			if (i1.priority < i2.priority) {
				order = 1;
			} else if (i1.priority > i2.priority) {
				order = -1;
			} else if (i1.destination < i2.destination) {
				order = 1;
			} else if (i1.destination > i2.destination) {
				order = -1;
			}
			return order;
		}
	}
	
	private LinkedList<Item> pool;
	private LinkedList<Item> fragilePool;
	private LinkedList<Robot> robots;
	private int lightCount;
	private int count;

	public MyMailPool(){
		// Start empty
		pool = new LinkedList<Item>();
		fragilePool = new LinkedList<Item>();
		lightCount = 0;
		robots = new LinkedList<Robot>();
	}
	
	public void addToPool(MailItem mailItem) {
		Item item = new Item(mailItem);
		if (item.fragile) {
			fragilePool.add(item);
			fragilePool.sort(new ItemComparator());
		}
		else {
			pool.add(item);
			if (!item.heavy) lightCount++;
			pool.sort(new ItemComparator());
		}
	}
	
	@Override
	public void step() throws FragileItemBrokenException {
		for (Robot robot: (Iterable<Robot>) robots::iterator) { fillStorageTube(robot); }
		//System.out.println(this.pool.size() + " " + this.fragilePool.size());
	}
	
	private void fillStorageTube(Robot robot) throws FragileItemBrokenException {
		StorageTube tube = robot.getTube();
		StorageTube temp = new StorageTube(tube.getCapacity(), tube.getCareful());
		try { // Get as many items as available or as fit
				if (robot.isCareful() && !fragilePool.isEmpty()) {
					if(temp.getSize() < temp.getCapacity() && !robot.getTube().getHaveFragile()) {
						Item item = fragilePool.remove();
						temp.addItem(item.mailItem);
						while (!temp.isEmpty()) tube.addItem(temp.pop());
						robot.dispatch();
						return;
					}	
				}
				if (robot.isStrong()) {
					while(temp.getSize() < temp.getCapacity() && !pool.isEmpty() ) {
						Item item = pool.remove();
						if (!item.heavy) lightCount--;
						temp.addItem(item.mailItem);
					}
				} else {
					ListIterator<Item> i = pool.listIterator();
					while(temp.getSize() < temp.getCapacity() && lightCount > 0) {
						Item item = i.next();
						if (!item.heavy) {
							temp.addItem(item.mailItem);
							i.remove();
							lightCount--;
						}
					}
				}
				if (temp.getSize() > 0) {
					while (!temp.isEmpty()) tube.addItem(temp.pop());
					robot.dispatch();
				}
		}
		catch(TubeFullException e){
			e.printStackTrace();
		}
	}

	@Override
	public void registerWaiting(Robot robot) { // assumes won't be there
		if (robot.isStrong()) {
			robots.add(robot); 
		} else {
			robots.addLast(robot); // weak robot last as want more efficient delivery with highest priorities
		}
	}

	@Override
	public void deregisterWaiting(Robot robot) {
		robots.remove(robot);
	}

}
