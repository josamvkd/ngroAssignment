package com.example.demo.view;

import com.example.demo.utils.CustomUtils;

public class StatementView {
	 private String accountId;
	 private String accountType;
	 private String accountNumber;
	 private String statementId;
	 private String datefield;
	 private String amount;
	 
	public StatementView(String accountId, String accountType, String accountNumber, String statementId, String datefield,
			String amount) {
		super();
		this.accountId = accountId;
		this.accountType = accountType;
		this.accountNumber = CustomUtils.encode(accountNumber);
		this.statementId = statementId;
		this.datefield = datefield;
		this.amount = amount;
	}
	
	public String getAccountId() {
		return accountId;
	}
	public String getAccountType() {
		return accountType;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public String getStatementId() {
		return statementId;
	}
	public String getDatefield() {
		return datefield;
	}
	public String getAmount() {
		return amount;
	}
}
