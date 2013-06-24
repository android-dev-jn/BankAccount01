package BankAccount;

public class BankAccountDTO {
	
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

	public Object[] getAccountNumber() {
		return null;
	}

	public void setAccountNumber(String string) {
		
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

}
