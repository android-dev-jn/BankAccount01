package BankAccount;

import java.util.Calendar;

public class BankAccount {

	public static BankAccountDAO bankAccountDAO;
	public static TransactionDAO transactionDAO;
	public static Calendar calendar;

	public static BankAccountDTO openAccount(String accountNumber) {
		BankAccountDTO bankAccountDTO = new BankAccountDTO(accountNumber);
		bankAccountDAO.save(bankAccountDTO);
		return bankAccountDTO;
	}

	public static BankAccountDTO getAccount(String string) {
		return bankAccountDAO.getAccount(string);
	}

	public static void deposit(String accountNumber, double amount,
			String description) {
		BankAccountDTO bankAccountDTO = bankAccountDAO
				.getAccount(accountNumber);
		bankAccountDTO.setBalance(bankAccountDTO.getBalance() + amount);
		bankAccountDTO.setDescription(description);
		bankAccountDAO.save(bankAccountDTO);
		long timestamp = calendar.getTimeInMillis();
		TransactionDTO transactionDTO = new TransactionDTO(accountNumber,
				timestamp, amount, description);
		transactionDAO.createTransaction(transactionDTO);
		// transactionDAO.saveTransaction(transactionDTO);

	}

	public static void getTransactionOccurred(String accountNumber) {
		transactionDAO.getTransactionOccurred(accountNumber);
	}

	public static Calendar getCalendar() {
		return calendar;
	}

	public static void setCalendar(Calendar calendar) {
		BankAccount.calendar = calendar;
	}

	public static void withdraw(String accountNumber, double amount,
			String description) {
		BankAccountDTO bankAcountDTO = bankAccountDAO.getAccount(accountNumber);
		bankAcountDTO.setBalance(bankAcountDTO.getBalance() - amount);
		bankAcountDTO.setDescription(description);
		bankAccountDAO.save(bankAcountDTO);
		long timestamp = calendar.getTimeInMillis();
		TransactionDTO transactionDTO = new TransactionDTO(accountNumber,
				timestamp, amount, description);
		transactionDAO.createTransaction(transactionDTO);
	}

	public static void getTransactions(String accountNumber, long startTime,
			long stopTime) {
		transactionDAO.getTransactions(accountNumber, startTime, stopTime);
	}

}
