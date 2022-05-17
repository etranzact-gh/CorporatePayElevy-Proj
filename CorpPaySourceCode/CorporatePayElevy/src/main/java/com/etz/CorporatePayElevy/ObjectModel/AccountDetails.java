package com.etz.CorporatePayElevy.ObjectModel;

public class AccountDetails {
	
	int companyId;
	String accountType;
	int totalAmount;
	int total_transactions;
	String elevy;
	
	

	public String getElevy() {
		return elevy;
	}

	public void setElevy(String elevy) {
		this.elevy = elevy;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public int setTotalAmount(int totalAmount) {
		return this.totalAmount = totalAmount;
	}

	public int getTotal_transactions() {
		return total_transactions;
	}

	public int setTotal_transactions(int total_transactions) {
		return this.total_transactions = total_transactions;
	}

	public String getAccountType() {
		return accountType;
	}

	public String setAccountType(String accountType) {
		return this.accountType = accountType;
	}

	public int getCompanyId() {
		return companyId;
	}

	public int setCompanyId(int companyId) {
		return this.companyId = companyId;
	}
	
	

}
