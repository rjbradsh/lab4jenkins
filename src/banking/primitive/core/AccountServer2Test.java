package banking.primitive.core;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AccountServer2Test {
	private static AccountServer accountServer = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		accountServer = AccountServerFactory.getMe().lookup();
	}

	@Before
	public void setUp() throws Exception {
		accountServer.newAccount("Checking", "CheckingTest1", 100.0f);
		accountServer.newAccount("Savings", "SavingsTest1", 200.0f);
		accountServer.newAccount("Checking", "CheckingTest2", 300.0f);
		accountServer.newAccount("Savings", "SavingsTest2", 400.0f);
	}

	@Test
	public void testGetActiveAccounts() {
		Account savings = accountServer.getAccount("CheckingTest2");
		Account checking = accountServer.getAccount("SavingsTest1");
		savings.setState(Account.State.CLOSED);
		checking.setState(Account.State.CLOSED);
		List<Account> accounts = accountServer.getActiveAccounts();
		for (Account acc : accounts) {
			assertTrue(!acc.equals(savings));
			assertTrue(!acc.equals(checking));
		}
	}
}
