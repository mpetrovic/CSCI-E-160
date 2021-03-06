package cscie160.hw2;
	
/**
 * Simulator a single elevator.
 */
public class Elevator {

	public final static int MAX_CAPACITY = 10;
	public final static int NUM_FLOORS = 7;
	
	private static Floor[] floors = null;
	
	private int current_floor;
	private boolean going_up;
	
	/*
	 * Stores the number of passengers heading for each floor.
	 * If the elevator is called from a floor with no passengers, 
	 * the p_t for that floor will be -1.
	 */
	private int[] passenger_targets;
	private int[] stops;
	
	/**
	 * Class constructor.
	 */
	public Elevator() {
		current_floor = 0;
		going_up = true;
	
		passenger_targets = new int[NUM_FLOORS+1];
		stops = new int[NUM_FLOORS+1];
		if (floors == null) {
			floors = new Floor[NUM_FLOORS+1];
			for (int i=1; i<NUM_FLOORS+1; i++) {
				floors[i] = new Floor(i);
			}
		}
	}
	
	/**
	 * Moves the elevator up or down. 
	 * Stops if the new floor is one of its target floors.
	 */
	public void move() {
		if (going_up) {
			current_floor++;
		}
		else {
			current_floor--;
		}
		
		if (current_floor == 1) {
			going_up = true;
		}
		else if (current_floor == NUM_FLOORS) {
			going_up = false;
		}
		
		if (passenger_targets[current_floor] != 0 || stops[current_floor] != 0) {
			stop();
		}
	}
	
	/**
	 * Disembarks all passengers heading for this floor.
	 * Clears the target for this floor.
	 * Prints out status information.
	 */
	public void stop() {
		passenger_targets[current_floor] = 0;
		floors[current_floor].unloadPassengers(this, going_up);
		// they didn't board anyone. the passengers are going the other way
		if (passenger_targets[current_floor] == 0) {
			registerRequest(current_floor, !going_up);
		}
		
		System.out.println("\r\nStopping on floor "+(current_floor));
		System.out.println(this);
	}
	
	/**
	 * Adds a passenger to the elevator. 
	 * Sets the passenger's destination as a target.
	
	@param	floor	The passenger's target floor.
	@throw	ElevatorFullException
	*/
	public void boardPassenger(int floor) throws ElevatorFullException {
		if (getPassengers() < MAX_CAPACITY) {
			passenger_targets[floor]++;
		}
		else {
			throw new ElevatorFullException();
		}
	}
	
	/**
	 * Registers a request for the elevator to stop at the given floor
	 
	@param	floor	The floor the request was made from
	@param	dir		The direction the request wants to go in. Ignored for now
	 */
	public void registerRequest(int floor, boolean dir) {
		stops[floor] = dir?1:-1;
	}
	
	private int getPassengers() {
		int passengers = 0;
		for (int i=0; i<passenger_targets.length; i++) {
			if (passenger_targets[i] > 0) {
				passengers += passenger_targets[i];
			}
		}
		return passengers;
	}
	
	/**
	 * Override of toString
	 */
	public String toString() {
		return "Current Passengers: "+getPassengers()+"\r\nCurrent Floor: "+(current_floor)+"\r\nDirection: "+(going_up?"Up":"Down");
	}
	
	/**
	 * Testing function
	 */
	public static void addPassenger(int floor, boolean going_up) {
		floors[floor].addPassenger(going_up);
	}
	
	/**
	 * For testing purposes
	 */
	static public void main(String argv[]) {
		Elevator elev = new Elevator();
		
		try {
			elev.boardPassenger(2);
			elev.boardPassenger(2);
			elev.boardPassenger(3);
		}
		catch (ElevatorFullException e) {
		}
		
		for (int j=1; j<20; j++) {
			int floor = j%NUM_FLOORS + j/NUM_FLOORS;
			addPassenger(floor, false);
			elev.registerRequest(floor, false);
		}
		
		for (int i=0; i<30; i++) {
			elev.move();
		}
	}
}