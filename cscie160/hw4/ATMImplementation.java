package cscie160.hw4;

public class ATMImplementation implements ATM {
	
	private Account accounts;
	
	public ATMImplementation() {
		accounts = new Account();
	}
	
	public void deposit(float amount) throws ATMException {
		accounts.addAmount(amount);
	}
	
	public void withdraw(float amount) throws ATMException {
		accounts.addAmount(-amount);
	}
	
	public Float getBalance() throws ATMException {
		return new Float(accounts.getAmount());
	}
}