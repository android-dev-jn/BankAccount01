package BankAccount;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class BankAccountTest {
	@Mock
	BankAccountDAO mockBankAccountDAO = mock(BankAccountDAO.class);
	TransactionDAO mockTransactionDAO = mock(TransactionDAO.class);
	Calendar mockCalendar = mock(Calendar.class);

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		reset(mockBankAccountDAO);
		reset(mockCalendar);
		BankAccount.bankAccountDAO = mockBankAccountDAO;
		BankAccount.transactionDAO = mockTransactionDAO;
		BankAccount.calendar = mockCalendar;
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

	// 3
	@Test
	public void testDeposit() {
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
		assertEquals(150, argument.getValue().getBalance(), DELTA);
		assertEquals(accountNumber, argument.getValue().getAccountNumber());
	}

	// 4
	@Test
	public void testGetTransactionTimestamp() {
		String accountNumber = "1234567890";
		double amount = 100;
		long timestamp = 1000;
		String description = "deposit 100";

		BankAccountDTO bankAccount = new BankAccountDTO(accountNumber, 50);
		when(mockBankAccountDAO.getAccount(bankAccount.getAccountNumber()))
				.thenReturn(bankAccount);
		when(mockCalendar.getTimeInMillis()).thenReturn(timestamp);

		TransactionDTO transactionDTO = new TransactionDTO(accountNumber,
				timestamp, amount, description);
		BankAccount.deposit(accountNumber, amount, description);
		ArgumentCaptor<TransactionDTO> argumentCaptor = ArgumentCaptor
				.forClass(TransactionDTO.class);
		verify(mockTransactionDAO).createTransaction(argumentCaptor.capture());
		assertEquals(timestamp, argumentCaptor.getValue().getTimestamp());
	}

	// 5
	@Test
	public void testWithdraw() {
		String accountNumber = "1234567890";
		double amount = 50, DELTA = 1e-2;
		String description = "withdraw 50";

		BankAccountDTO bankAccount = new BankAccountDTO(accountNumber, 150);
		when(mockBankAccountDAO.getAccount(bankAccount.getAccountNumber()))
				.thenReturn(bankAccount);
		BankAccount.withdraw(accountNumber, amount, description);

		ArgumentCaptor<BankAccountDTO> argument = ArgumentCaptor
				.forClass(BankAccountDTO.class);
		verify(mockBankAccountDAO, times(1)).save(argument.capture());
		assertEquals(100, argument.getValue().getBalance(), DELTA);
		assertEquals(accountNumber, argument.getValue().getAccountNumber());
	}

	// 6
	@Test
	public void testWithdrawTransaction() {
		String accountNumber = "1234567890";
		double amount = 50, DELTA = 1e-2;
		long timestamp = 1000;
		String description = "withdraw 100";

		BankAccountDTO bankAccount = new BankAccountDTO(accountNumber, 150);
		when(mockBankAccountDAO.getAccount(bankAccount.getAccountNumber()))
				.thenReturn(bankAccount);
		when(mockCalendar.getTimeInMillis()).thenReturn(timestamp);

		TransactionDTO transactionDTO = new TransactionDTO(accountNumber,
				timestamp, amount, description);
		BankAccount.withdraw(accountNumber, amount, description);
		ArgumentCaptor<TransactionDTO> argumentCaptor = ArgumentCaptor
				.forClass(TransactionDTO.class);
		verify(mockTransactionDAO).createTransaction(argumentCaptor.capture());
		assertEquals(timestamp, argumentCaptor.getValue().getTimestamp());
		assertEquals(50, argumentCaptor.getValue().getAmount(), DELTA);
	}

	// 7
	@Test
	public void testGetTransactionOccurred() {
		String accountNumber = "0123456789";
		ArgumentCaptor<String> accountNubmerCaptor = ArgumentCaptor
				.forClass(String.class);
		BankAccount.getTransactionOccurred(accountNumber);
		verify(mockTransactionDAO, times(1)).getTransactionOccurred(
				accountNubmerCaptor.capture());
		assertEquals(accountNumber, accountNubmerCaptor.getValue());
	}

	// 8
	@Test
	public void testGestListTransaction() {
		String accountNumber = "1234567890";
		long startTime = 1000, stopTime = 2000;

		BankAccount.getTransactions(accountNumber, startTime, stopTime);
		ArgumentCaptor<String> accountNubmerCaptor = ArgumentCaptor
				.forClass(String.class);
		verify(mockTransactionDAO, times(1)).getTransactions(accountNumber,
				startTime, stopTime);
	}

}
