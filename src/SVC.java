
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Phareal
 */
public class SVC {
    private int totalTime, startAt, elapsedTime;
    private ArrayList<Junction> myJunctions = new ArrayList<>();
    private ArrayList<Street> myStreets = new ArrayList<>();
    
    public SVC(int totalTime, int startAt, ArrayList<Junction> junctions) {
	this.totalTime = totalTime;
	this.startAt = startAt;
	this.myJunctions.add(junctions.get(this.startAt));
	this.elapsedTime = 0;
    }
    
    public boolean moveTo(Street rightStreet, Junction destJunction) {
	if (rightStreet.getTimeToTraverse() <= this.getAvailableTime()) {
	    this.elapsedTime += rightStreet.getTimeToTraverse();
	    this.getMyJunctions().add(destJunction);
	    this.getMyStreets().add(rightStreet);
	    return true;
	}
	return false;
    }
    
    public int getAvailableTime() {
	return (getTotalTime() - getElapsedTime());
    }

    public int getStartAt() {
	return startAt;
    }

    public void setStartAt(int startAt) {
	this.startAt = startAt;
    }

    public int getElapsedTime() {
	return elapsedTime;
    }

    public void setElapsedTime(int elapsedTime) {
	this.elapsedTime = elapsedTime;
    }
    
    public int getTotalTime() {
	return totalTime;
    }

    public void setTotalTime(int totalTime) {
	this.totalTime = totalTime;
    }

    public ArrayList<Junction> getMyJunctions() {
	return myJunctions;
    }

    public void setMyJunctions(ArrayList<Junction> myJunctions) {
	this.myJunctions = myJunctions;
    }

    public ArrayList<Street> getMyStreets() {
	return myStreets;
    }

    public void setMyStreets(ArrayList<Street> myStreets) {
	this.myStreets = myStreets;
    }
}
