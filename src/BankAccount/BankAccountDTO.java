package BankAccount;

public class BankAccountDTO {

	private String accountNumber;
	private String description;
	private double balance;
	private double lastDeposit;
	private Long timestamp;

	public BankAccountDTO(String accountNumber, Long timestamp) {
		super();
		this.accountNumber = accountNumber;
		this.timestamp = timestamp;
	}

	public BankAccountDTO(double lastDeposit) {
		this.lastDeposit = lastDeposit;
	}

	public BankAccountDTO() {
		this.lastDeposit = 0;
	}

	public BankAccountDTO(String accountNumber) {
		this.accountNumber = accountNumber;
		this.balance = 0;
	}

	public BankAccountDTO(String accountNumber, double i) {
		this.accountNumber = accountNumber;
		this.balance = i;
	}

	public double getBalance() {
		return this.balance;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public void setBalance(double newBalance) {
		this.balance = newBalance;
	}

	public double getLastDeposit() {
		return lastDeposit;
	}

	public void setLastDeposit(double lastDeposit) {
		this.lastDeposit = lastDeposit;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
