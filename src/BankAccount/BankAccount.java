package BankAccount;

public class BankAccount {

	public static BankAccountDAO bankAccountDAO;

	public static BankAccountDTO openAccount(String string) {
		BankAccountDTO bankAccountDTO = new BankAccountDTO();
		bankAccountDAO.save(bankAccountDTO);
		return bankAccountDTO;
	}

	public static BankAccountDTO getAccount(String string) {
		return bankAccountDAO.getAccount(string);
	}

	public static void deposit(String accountNumber, double amount,
			String description) {
		BankAccountDTO bankAccountDTO = bankAccountDAO.getAccount(accountNumber);
		bankAccountDTO.setBalance(bankAccountDTO.getBalance() + amount);
		bankAccountDTO.setDescription(description);
		bankAccountDAO.save(bankAccountDTO);
	}

}
