package cscie160.hw2;

/**
 * Defines a single floor
 */
public class Floor {
	private int floor_num;
	private int passengers_waiting_up;
	private int passengers_waiting_down;
	
	
	/**
	 * Constructor
	 *
	@param	floor	The number of this floor
	 */
	public Floor(floor) {
		floor_num = floor;
		passengers_waiting_up = 0;
		passengers_waiting_down = 0;
	}
	
	/**
	 * Put passengers onto the elevator.
	 
	@param	elev	The elevator to load passengers onto
	@param	going_up	The direction the elevator is going in
	 */
	public void unloadPassengers(Elevator elev, boolean going_up) {
		int passengers = going_up?passengers_waiting_up:passengers_waiting_down;
		for (int i=0; i<passengers; i++) {
			try {
				elev.boardPassenger(1);
			}
			catch (Exception e) {
				break;
			}
		}
		
		if (going_up) {
			passengers_waiting_up = passengers;
		}
		else {
			passengers_waiting_down = passengers;
		}
		
		if (passengers) {
			elev.registerRequest(floor_num, going_up);
		}
	}
	
	/**
	 * Testing function
	 */
	public void addPassenger(boolean going_up) {
		if (going_up) {
			passengers_waiting_up++;
		}
		else {
			passengers_waiting_down++;
		}
	}
}