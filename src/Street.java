/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Phareal
 */
public class Street {
    private int junctionA, junctionB, direction, timeToTraverse, dist;
    private boolean dirty;

    public Street(int junctionA, int junctionB, int direction, int timeToTraverse, int dist) {
        this.junctionA = junctionA;
        this.junctionB = junctionB;
        this.direction = direction;
        this.timeToTraverse = timeToTraverse;
        this.dist = dist;
	this.dirty = false;
    }

    public boolean isDirty() {
	return dirty;
    }

    public void setDirty(boolean dirty) {
	this.dirty = dirty;
    }

    public int getJunctionA() {
        return junctionA;
    }

    public void setJunctionA(int junctionA) {
        this.junctionA = junctionA;
    }

    public int getJunctionB() {
        return junctionB;
    }

    public void setJunctionB(int junctionB) {
        this.junctionB = junctionB;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getTimeToTraverse() {
        return timeToTraverse;
    }

    public void setTimeToTraverse(int timeToTraverse) {
        this.timeToTraverse = timeToTraverse;
    }

    public int getDist() {
        return dist;
    }

    public void setDist(int dist) {
        this.dist = dist;
    }
    
    
}
