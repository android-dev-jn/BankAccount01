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
}
