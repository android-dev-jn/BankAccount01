package BankAccount;

public class BankAccountDTO {
	
	private String accountNumber;
	private String description;
	private double lastDeposit;
	
	
	public BankAccountDTO(double lastDeposit) {
		this.lastDeposit = lastDeposit;
	}

	public BankAccountDTO() {
		this.lastDeposit = 0;		
	}

	public int getBalance() {
		return 0;
	}


	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public void setBalance(double amount) {
		
	}

	public double getLastDeposit() {
		return lastDeposit;
	}

	public void setLastDeposit(double lastDeposit) {
		this.lastDeposit = lastDeposit;
	}

	public double getTimeStamp() {	
		return 0;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
