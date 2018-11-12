package com.capg.paymentwallet.bean;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Bank_Accounts")
public class AccountBean {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int accountId;
	
	
	private double balance;
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private CustomerBean customerBean;
	
	//@Temporal(TemporalType.DATE)
	private Date dateOfOpening;
	
	private double initialDeposit;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<WalletTransaction> allTransactions;

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public CustomerBean getCustomerBean() {
		return customerBean;
	}

	public void setCustomerBean(CustomerBean customerBean) {
		this.customerBean = customerBean;
	}

	public Date getDateOfOpening() {
		return dateOfOpening;
	}

	public void setDateOfOpening(Date dateOfOpening) {
		this.dateOfOpening = dateOfOpening;
	}

	public double getInitialDeposit() {
		return initialDeposit;
	}

	public void setInitialDeposit(double initialDeposit) {
		this.initialDeposit = initialDeposit;
	}

	public List<WalletTransaction> getAllTransactions() {
		return allTransactions;
	}

	public void setAllTransactions(List<WalletTransaction> allTransactions) {
		this.allTransactions = allTransactions;
	}

	@Override
	public String toString() {
		return "AccountBean [accountId=" + accountId + ", balance=" + balance
				+ ", customerBean=" + customerBean + ", dateOfOpening="
				+ dateOfOpening + ", initialDeposit=" + initialDeposit
				+ ", allTransactions=" + allTransactions + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountBean other = (AccountBean) obj;
		if (accountId != other.accountId)
			return false;
		return true;
	}
	
	public void addTransation(WalletTransaction wt){
		this.allTransactions.add(wt);
	}
	

}
