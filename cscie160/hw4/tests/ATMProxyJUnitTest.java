package cscie160.hw4.tests;

import junit.framework.*;
import cscie160.hw4.*;

public class ATMProxyJUnitTest extends TestCase {
	
	private ATM atm = null;
	
	public ATMProxyJUnitTest(String name) {
		super(name);
	}
	
	public void setUp() {
		try {
			atm = new ATMProxy("localhost", 7777);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void tearDown() {
		atm = null;
	}
	
	public void testDeposit() {
		try {
			atm.deposit(50000);
			float balance = atm.getBalance();
			assertTrue("Deposit failed: Expected 50000, got "+balance,
						balance == 50000);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void testWithdraw() {
		try {
			atm.deposit(50000);
			atm.withdraw(20000);
			float balance = atm.getBalance();
			assertTrue("Withdraw failed: Expected 30000, got "+balance,
				balance == 30000);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}