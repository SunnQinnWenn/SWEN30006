package swen30006.automail;

import java.util.Stack;

import swen30006.exceptions.FragileItemBrokenException;
import swen30006.exceptions.TubeFullException;

public class StorageTube {
	
	private int capacity;
	private boolean careful;
	private boolean containFragile = false;
    public Stack<MailItem> tube;

    /**
     * Constructor for the storage tube
     */
    
    public StorageTube(int capacity, boolean careful) {
    	this.tube = new Stack<MailItem>();
    	this.capacity = capacity;
    	this.careful = careful;
    }

    /**
     * @return if the storage tube is full
     */
    public boolean isFull(){
        return tube.size() == this.capacity;
    }

    /**
     * @return if the storage tube is empty
     */
    public boolean isEmpty(){
        return tube.isEmpty();
    }
    
    /**
     * @return the first item in the storage tube (without removing it)
     */
    public MailItem peek() {
    	return tube.peek();
    }

    /**
     * Add an item to the tube
     * @param item The item being added
     * @throws TubeFullException thrown if an item is added which exceeds the capacity
     */
    public void addItem(MailItem item) throws TubeFullException, FragileItemBrokenException {
        if(tube.size() < capacity){
        	if (item.getFragile() && !careful || item.getFragile() && containFragile) {
        		throw new FragileItemBrokenException();
        	} 
        	if (tube.isEmpty()) {
        		tube.add(item);
        		//Fragile check
        		if (item.getFragile()) {
        			//robot is not a careful robot, item break
        			if (!careful) {
        				throw new FragileItemBrokenException();
        			}
        			//robot is careful robot, mark that tube contains fragile item
        			else {
        				this.containFragile = true;
        			}
        		}
        	} 
        	//Item is fragile, and robot not careful or already contain fragile item = break
        	else {
        		tube.add(item);
        		if (item.getFragile()) {
                    throw new FragileItemBrokenException();
        		}
        	}
        } else {
            throw new TubeFullException();
        }
    }

    /** @return the size of the tube **/
    public int getSize(){
    	return tube.size();
    }
    
    /** @return the capacity of the tube **/
    public int getCapacity() {
    	return this.capacity;
    }
    
    /** @return whether tube contains a fragile item **/
    public boolean getHaveFragile() {
    	return this.containFragile;
    }

    /** @return whether tube is held by careful robot or not **/
    public boolean getCareful() {
        return this.careful;
    }

    /**
     * @return the first item in the storage tube (after removing it)
     */
    public MailItem pop(){
        if (this.peek().getFragile() == true) {
            this.setHaveFragile(false);
        }
        return tube.pop();
    }

    /** set whether tube contains fragile item **/
    private void setHaveFragile(boolean haveFragile) {
    	this.containFragile = haveFragile;
    }



}
