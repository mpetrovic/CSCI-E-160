package cscie160.hw2;

public class ElevatorFullException extends Exception {
	public ElevatorFullException() { super("Elevator Full!"); }
	public ElevatorFullException(String msg) { super("Elevator Full: "+msg); }
}