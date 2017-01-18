
import java.util.ArrayList;
import java.util.Scanner;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Phareal
 */
public class Coreographer {
    // Get main data
    /* @PARAMS:
        N junctions,
        M streets,
        T time,
        C cars,
        S initial junction for cars
    */
    private static Integer n, m, t, c, s;
    private static String[] line1;
    private static final Scanner sc = new Scanner(System.in);
    
    private static ArrayList<Junction> junctions = new ArrayList<>();
    private static ArrayList<Street> streets = new ArrayList<>();
    private static ArrayList<SVC> fleet = new ArrayList<>();
    private static boolean debug = false;
    
    // Main
    public static void main(String[] args) {
        line1 = sc.nextLine().trim().split(" ");
        
        n = Integer.valueOf(line1[0]);
        m = Integer.valueOf(line1[1]);
        t = Integer.valueOf(line1[2]);
        c = Integer.valueOf(line1[3]);
        s = Integer.valueOf(line1[4]);
        
        junctions.clear();
        for (int i = 0; i < n; i++) {
            String tmp = sc.nextLine();
            if (isJunction(tmp)) {
                junctions.add(new Junction(Long.valueOf(tmp.split("")[0]), Long.valueOf(tmp.split("")[1])));
            }
        }
        
        streets.clear();
        for (int i = 0; i < m; i++) {
            String tmp = sc.nextLine();
            if (isStreet(tmp)) {
                streets.add(new Street(Integer.valueOf(tmp.split(" ")[0]), Integer.valueOf(tmp.split(" ")[1]), Integer.valueOf(tmp.split(" ")[2]), Integer.valueOf(tmp.split(" ")[3]), Integer.valueOf(tmp.split(" ")[4])));
            }
        }
	
	fleet.clear();
	// Now the input data is parsed correctly
	// We have to schedule movement of our cars to maximise length of total streets they move on
	// @TODO: Determinates itineraries for at least one of our cars
	for (int i = 0; i < c; i++) {
	    // @TODO: Add this car to our fleet
	    SVC $this = new SVC(t, s, junctions);
	    fleet.add($this);
	    
	    // Run inner coreographer
	    // DO NOT RUN THIS IN AN ASYNC TASK
	    scheduleMovements($this);
	    
	    // Print
	    if (debug) {
		System.out.println("\nCar : " + i + "\nInitial junction : " + $this.getStartAt() + "\nVisits : " + $this.getMyJunctions().size() + "\nAvailable time : " + $this.getAvailableTime());
		for (Junction junction : $this.getMyJunctions()) {
		    try {
			Junction nextJunction = $this.getMyJunctions().get($this.getMyJunctions().indexOf(junction)+1);
			System.out.println("From junction <" + junctions.indexOf(junction) + "> to junction <" + junctions.indexOf(nextJunction) + ">");
		    } catch (Exception e) {
		    }
		}
	    }
	}
	
	if (!debug) {
	    System.out.println("\n----------------------------START----------------------------\n");
	    System.out.println(fleet.size());
	    for (int i = (fleet.size()-1); i >= 0; i--) {
		SVC svc = fleet.get(i);
		System.out.println(svc.getMyJunctions().size());
		for (Junction junction : svc.getMyJunctions()) {
		    System.out.println(junctions.indexOf(junction));
		}
	    }
	    System.out.println("\n----------------------------FINISH----------------------------\n");
	}
    }
    
    private static ArrayList<Street> getCanTakeStreets(SVC svc, Junction currentJunction) {
	ArrayList<Street> tmp = new ArrayList<>();
	for (Street street : streets) {
	    if (street.getJunctionA() == getJunctionIndex(currentJunction) && !street.isDirty()) {
		tmp.add(street);
	    }
	}
	return tmp;
    }
    
    private static ArrayList<Street> getStreetsInTime(SVC svc, ArrayList<Street> possibleStreets) {
	ArrayList<Street> tmp = new ArrayList<>();
	for (Street street : possibleStreets) {
	    if (svc.getAvailableTime() >= street.getTimeToTraverse()) {
		tmp.add(street);
	    }
	}
	return tmp;
    }
    
    private static Street getMaxDistanceStreet(ArrayList<Street> possibleStreets) {
	Street tmp = null;
	int isSupposedToBeMax = 0;
	for (Street street : possibleStreets) {
	    if (street.getDist() > isSupposedToBeMax) {
		isSupposedToBeMax = street.getDist();
		tmp = street;
	    }
	}
	return tmp;
    }
    
    private static int getJunctionIndex(Junction currentJunction) {
	return junctions.indexOf(currentJunction);
    }
    
    private static boolean isJunction(String tmp) {
        return true;
    }
    
    private static boolean isStreet(String tmp) {
        return true;
    }

    private static void scheduleMovements(SVC svc) {
	// @TODO: Final state of the svc. if true, can move to some junction starting from its actual junction; if false, the svc can not move again. Use another svc
	boolean state = false;
	
	// Schedule so
	do {
	    // @TODO: Get possible streets to move on
	    ArrayList<Street> possibleStreets;

	    // @TODO: Determinate the right street to move on
	    Street rightStreet;

	    // @TODO: Get the can-take streets
	    possibleStreets = getCanTakeStreets(svc, svc.getMyJunctions().get(svc.getMyJunctions().size()-1));

	    // @TODO: Get the streets in time
	    possibleStreets = getStreetsInTime(svc, possibleStreets);

	    // @TODO: Get the street with the long time
	    rightStreet = getMaxDistanceStreet(possibleStreets);
	    
	    if (rightStreet != null) {
		// @TODO: Get the junction over there
		Junction destJunction = junctions.get(rightStreet.getJunctionB());

		// @TODO: Move the car along the street to the dest junction
		state = svc.moveTo(rightStreet, destJunction);

		if (state) {
		    rightStreet.setDirty(true);
		}
	    } else {
		state = false;
	    }
	} while (state);
    }
}
