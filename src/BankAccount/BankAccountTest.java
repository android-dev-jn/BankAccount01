package BankAccount;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BankAccountTest {
	
	@Test
	public void openNewAccountReturnEmptyBalance() {	
		BankAccount bankAccount = new BankAccount();
		BankAccountDTO bankAccountDTO = bankAccount.open("1234567890");
		assertEquals(0, bankAccountDTO.getBalance());
	}
	
	@Test
	public void testPersistentOfBankAccount() {	
		BankAccount bankAccount = new BankAccount();
		BankAccountDTO bankAccountDTO = bankAccount.open("1234567890");
		assertEquals(0, bankAccountDTO.getBalance());
	}

}
