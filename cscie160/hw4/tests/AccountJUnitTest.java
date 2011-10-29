package cscie160.hw4.tests;

import junit.framework.*;
import cscie160.hw4.Account;

public class AccountJUnitTest extends TestCase {

	private Account account = null;
	
	public AccountJUnitTest(String name) {
		super(name);
	}
	
	public void setUp() {
		account = new Account();
	}
	
	public void tearDown() {
		account = null;
	}
	
	public void testSetAmount() {
		Account acc = new Account();
		acc.setAmount(50000);
		assertTrue("Set Amount failed: expected 50000, got "+acc.getAmount(),
			acc.getAmount() == 50000);
	}
	
	public void testAddToAccount() {
		Account acc = new Account();
		acc.setAmount(20000);
		acc.addAmount(30000);
		assertTrue("Add To Account failed: expected 50000, got "+acc.getAmount(),
			acc.getAmount() == 50000);
	}
}