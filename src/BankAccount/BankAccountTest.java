package BankAccount;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.reset;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class BankAccountTest {
	@Mock
	BankAccountDAO mockBankAccountDAO;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		reset(mockBankAccountDAO);
		BankAccount.bankAccountDAO = mockBankAccountDAO;
	}

	@Test
	public void testOpenAccount() {
		BankAccountDTO bankAccountDTO = BankAccount.openAccount("123456789");
		ArgumentCaptor<String> argumentCaptor = ArgumentCaptor
				.forClass(String.class);
		verify(mockBankAccountDAO, times(1)).save(argumentCaptor.capture());

		assertEquals("123456789", argumentCaptor.getValue());
		assertTrue(0 == bankAccountDTO.getBalance());
	}
	
	@Test
	public void testGetAccount() {
		BankAccountDTO bankAccountDTO = BankAccount.openAccount("1234567890");
		BankAccountDTO bankAccountDTO2 = BankAccount.getAccount("1234567890");
		ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
		assertEquals(bankAccountDTO.getAccountNumber(), bankAccountDTO2.getAccountNumber());
		assertEquals(bankAccountDTO.getBalance(), bankAccountDTO2.getBalance());
		verify(mockBankAccountDAO,times(1)).getAccount(argumentCaptor.capture());
		assertEquals("1234567890", argumentCaptor.getValue());
	}
	
	@Test
	public void testDepositAmount() {
		double amount = 100;
		BankAccountDTO bankAccountDTO = BankAccount.openAccount("1234567890");
		BankAccount.deposit("1234567890", amount, "Bonus");
		BankAccountDTO bankAccountDTO2 = BankAccount.getAccount("1234567890");
		assertEquals(amount, bankAccountDTO2.getLastDepositAmount());
	}
}
