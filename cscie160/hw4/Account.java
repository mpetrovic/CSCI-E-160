package cscie160.hw4;

public class Account {
	private float amount;
	
	public Account() {
		amount = 0;
	}
	
	public float getAmount() {
		return amount;
	}
	
	public void setAmount(float amt) {
		amount = amt;
	}
	
	public void addAmount(float amt) {
		amount += amt;
	}
}