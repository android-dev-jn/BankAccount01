package BankAccount;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
		ArgumentCaptor<BankAccountDTO> argumentCaptor = ArgumentCaptor
				.forClass(BankAccountDTO.class);
		verify(mockBankAccountDAO, times(1)).save(argumentCaptor.capture());

		assertEquals("123456789", argumentCaptor.getValue().getAccountNumber());
		assertTrue(0 == argumentCaptor.getValue().getBalance());
	}

	@Test
	public void testGetAccount() {
		String accountNumber = "01234567890";
		BankAccountDTO bankAccountDTO = BankAccount.getAccount(accountNumber);
		verify(mockBankAccountDAO, times(1)).getAccount(accountNumber);
	}

	@Test
	public void testDepositTransaction() {
		String accountNumber = "1234567890";
		double amount = 100, DELTA = 1e-2;
		String description = "Bonus";
		
		BankAccountDTO bankAccount = new BankAccountDTO(accountNumber, 50);
		when(mockBankAccountDAO.getAccount(bankAccount.getAccountNumber()))
		.thenReturn(bankAccount);
		BankAccount.deposit(accountNumber, amount, description);
		
		ArgumentCaptor<BankAccountDTO> argument = ArgumentCaptor
				.forClass(BankAccountDTO.class);
		verify(mockBankAccountDAO, times(1)).save(argument.capture());
		System.out.println("balance = " + argument.getValue().getBalance());
		assertEquals(150, argument.getValue().getBalance(), DELTA);
		assertEquals(accountNumber, argument.getValue().getAccountNumber());
	}

}
