package BankAccount;

public class BankAccount {

	public static BankAccountDAO bankAccountDAO;

	public static BankAccountDTO openAccount(String string) {
		BankAccountDTO bankAccountDTO = new BankAccountDTO();
		bankAccountDAO.save(string);
		return bankAccountDTO;
	}

	public static BankAccountDTO getAccount(String string) {
		BankAccountDTO bankAccountDTO = bankAccountDAO.getAccount(string);
		BankAccountDTO bankAccountDTO2 = new BankAccountDTO();
		bankAccountDTO2.setAccountNumber(string);
		bankAccountDTO2.setBalance(0);
		return bankAccountDTO2;
	}

	public static void deposit(String string, double amount, String string2) {
		BankAccountDTO bankAccountDTO = bankAccountDAO.getAccount(string);
		BankAccountDTO bankAccountDTO2 = new BankAccountDTO();
		bankAccountDTO2.setAccountNumber(string);
		bankAccountDTO2.setBalance(0);
	}

}
