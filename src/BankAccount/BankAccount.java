package BankAccount;

public class BankAccount {

	public static BankAccountDAO bankAccountDAO;

	public static BankAccountDTO openAccount(String string) {
		BankAccountDTO bankAccountDTO = new BankAccountDTO();
		bankAccountDAO.save(string);
		return bankAccountDTO;
	}

}
