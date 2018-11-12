package com.capg.paymentwallet.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="Bank_W_Transactions")
public class WalletTransaction {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int transactionId;
	
	private double transactionAmt;
	
	private int transactionType;
	// 1- depostit 2- withdraw 3-fund transfer
	
	@Transient
	private AccountBean beneficiaryAccountBean; // in case of transfer
	
	
	@Temporal(TemporalType.DATE)
	private Date transactionDate;
	
	
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public double getTransactionAmt() {
		return transactionAmt;
	}
	public void setTransactionAmt(double transactionAmt) {
		this.transactionAmt = transactionAmt;
	}
	public int getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(int transactionType) {
		this.transactionType = transactionType;
	}
	public AccountBean getBeneficiaryAccountBean() {
		return beneficiaryAccountBean;
	}
	public void setBeneficiaryAccountBean(AccountBean beneficiaryAccountBean) {
		this.beneficiaryAccountBean = beneficiaryAccountBean;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	@Override
	public String toString() {
		return "WalletTransaction [transactionId=" + transactionId
				+ ", transactionAmt=" + transactionAmt + ", transactionType="
				+ transactionType + ", beneficiaryAccountBean="
				+ beneficiaryAccountBean + ", transactionDate="
				+ transactionDate + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + transactionId;
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
		WalletTransaction other = (WalletTransaction) obj;
		if (transactionId != other.transactionId)
			return false;
		return true;
	}
	
	
	

}
