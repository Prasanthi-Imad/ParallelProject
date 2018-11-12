package com.capg.paymentwallet.ui;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.capg.paymentwallet.bean.AccountBean;
import com.capg.paymentwallet.bean.CustomerBean;
import com.capg.paymentwallet.bean.WalletTransaction;
import com.capg.paymentwallet.exception.CustomerException;
import com.capg.paymentwallet.service.AccountServiceImpl;
import com.capg.paymentwallet.service.IAccountService;

public class Client {

	IAccountService service = new AccountServiceImpl();
	CustomerBean customer = new CustomerBean();
	Scanner scanner = new Scanner(System.in);
	AccountBean account = new AccountBean();

	public static void main(String[] args) throws Exception {
		char ch;
		Client client = new Client();
		do {
			System.out.println("========Payment wallet application========");
			System.out.println("1. Create Account ");
			System.out.println("2. Show Balance ");
			System.out.println("3. Deposit ");
			System.out.println("4. Withdraw ");
			System.out.println("5. Fund Transfer");
			System.out.println("6. Print Transactions");
			System.out.println("7. Exit");
			System.out.println("Choose an option");
			int option = client.scanner.nextInt();

			switch (option) {
			case 1:
				client.create();

				break;
			case 2:
				client.showbalance();

				break;

			case 3:
				client.deposit();

				break;

			case 4:
				client.withdraw();

				break;

			case 5:
				client.fundtransfer();

				break;

			case 6:
				client.printTransaction();

				break;
			case 7:
				System.exit(0);

				break;

			default:
				System.out.println("invalid option");
				break;
			}

			System.out
					.println("\n\n***Do you want to continue press Y/N***\t: ");
			ch = client.scanner.next().charAt(0);

		} while (ch == 'y' || ch == 'Y');

	}

	void create() throws Exception {

		System.out.print("\t Enter Customer firstname\t\t: ");
		String fname = scanner.next();

		System.out.print("\t Enter Customer lastname\t\t: ");
		String lname = scanner.next();

		System.out.print("\t Enter  Customer  email id\t\t: ");
		String email = scanner.next();

		System.out.print("\t Enter  Customer  phone number\t\t: ");
		String phone = scanner.next();

		System.out.print("\t Enter  Customer PAN number\t\t: ");
		String pan = scanner.next();

		System.out.print("\t Enter  Customer  address\t\t: ");
		String address = scanner.next();

		CustomerBean customerBean = new CustomerBean();
		customerBean.setAddress(address);
		customerBean.setEmailId(email);
		customerBean.setPanNum(pan);
		customerBean.setPhoneNo(phone);
		customerBean.setFirstName(fname);
		customerBean.setLastName(lname);

		/*
		 * System.out.println("Enter  Account ID"); int accId =
		 * scanner.nextInt();
		 */

		// int accId = account.getAccountId();

		/*
		 * LocalDate dateOfOpening = java.time.LocalDate.now();
		 * DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMMM yyyy");
		 */

		System.out.print("\t Enter Date of Opening (DD/MM/YYYY)\t: ");
		String accDateInput = scanner.next();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dateOfOpening = sdf.parse(accDateInput);

		System.out.print("\t Enter balance to create account\t: ");
		double balance = scanner.nextDouble();

		AccountBean accountBean = new AccountBean();
		// accountBean.setAccountId(accId);
		accountBean.setBalance(balance);
		accountBean.setInitialDeposit(balance);
		accountBean.setDateOfOpening(dateOfOpening);
		accountBean.setCustomerBean(customerBean);

		boolean result = service.createAccount(accountBean);

		if (result) {
			System.out
					.println("\n****Congratulations, Customer account has been created successfully...****\n");
		} else {
			System.out.println("\n\t***Enter valid details..***\n\t");
		}
	}

	void showbalance() throws CustomerException, Exception {
		System.out.print("\t Enter Account ID\t\t: ");
		int accId = scanner.nextInt();

		AccountBean accountBean = service.findAccount(accId);

		if (accountBean == null) {
			System.out.println("\n\t\t*** Account Does not exist ***\n\n\t\t");
			return;
		}

		double balance = accountBean.getBalance();
		System.out.println("\t Name of the customer\t\t: "
				+ accountBean.getCustomerBean().getFirstName() + " "
				+ accountBean.getCustomerBean().getLastName());
		System.out.println("\t Phone number\t\t\t: "
				+ accountBean.getCustomerBean().getPhoneNo());
		System.out.println("\t Your balance is\t\t: " + balance);

	}

	void deposit() throws Exception {
		System.out.println("\t Enter Account ID\t\t: ");
		int accId = scanner.nextInt();

		AccountBean accountBean = service.findAccount(accId);

		System.out.print("\t Enter amount that you want to deposit\t\t: ");
		double depositAmt = scanner.nextDouble();

		WalletTransaction wt = new WalletTransaction();
		wt.setTransactionType(1);
		wt.setTransactionDate(new Date());
		wt.setTransactionAmt(depositAmt);
		wt.setBeneficiaryAccountBean(null);

		accountBean.addTransation(wt);

		if (accountBean == null) {
			System.out.println("\n*** Account Does not exist ***\n");
			return;
		}

		boolean result = service.deposit(accountBean, depositAmt);

		if (result) {
			System.out.println("\t Deposited Money into Account ");
		} else {
			System.out.println("\t NOT Deposited Money into Account ");
		}

	}

	void withdraw() throws Exception {
		System.out.println("\t Enter Account ID\t\t: ");
		int accId = scanner.nextInt();

		AccountBean accountBean = service.findAccount(accId);

		System.out.println("\t Enter amount that you want to withdraw\t\t: ");
		double withdrawAmt = scanner.nextDouble();

		WalletTransaction wt = new WalletTransaction();
		wt.setTransactionType(2);
		wt.setTransactionDate(new Date());
		wt.setTransactionAmt(withdrawAmt);
		wt.setBeneficiaryAccountBean(null);

		accountBean.addTransation(wt);

		if (accountBean == null) {
			System.out.print("\t Account Does not exist\n");
			return;
		}

		boolean result = service.withdraw(accountBean, withdrawAmt);
		if (result) {
			System.out.print("\t Withdaw Money from Account done\t");
		} else {
			System.out.print("\t Withdaw Money from Account -Failed\t");
		}

	}

	void fundtransfer() throws Exception {
		System.out.print("\t Enter Account ID to Transfer Money From\t\t:");
		int srcAccId = scanner.nextInt();

		AccountBean accountBean1 = service.findAccount(srcAccId);

		System.out.print("\t Enter Account ID to Transfer Money to\t\t:");
		int targetAccId = scanner.nextInt();

		AccountBean accountBean2 = service.findAccount(targetAccId);

		System.out.print("\t Enter amount that you want to transfer\t\t:");
		double transferAmt = scanner.nextDouble();

		WalletTransaction wt = new WalletTransaction();
		wt.setTransactionType(3);
		wt.setTransactionDate(new Date());
		wt.setTransactionAmt(transferAmt);
		wt.setBeneficiaryAccountBean(accountBean2);

		accountBean1.addTransation(wt);

		boolean result = service.fundTransfer(accountBean1, accountBean2,
				transferAmt);

		if (result) {
			System.out.println("\t Transfering Money from Account done");
		} else {
			System.out.println("\t Transfering Money from Account Failed ");
		}

	}

	void printTransaction() throws Exception {
		System.out
				.println("\t Enter Account ID for printing Transaction Details\t\t:");
		int accId = scanner.nextInt();

		AccountBean accountBean = service.findAccount(accId);

		List<WalletTransaction> transactions = accountBean.getAllTransactions();

		System.out.println("\t Customer Details [ "
				+ accountBean.getCustomerBean().getFirstName() + " "
				+ accountBean.getCustomerBean().getLastName() + " , "
				+ accountBean.getCustomerBean().getEmailId() + " , "
				+ accountBean.getCustomerBean().getPhoneNo() + " , "
				+ accountBean.getCustomerBean().getAddress() + " ]");

		System.out
				.println("------------------------------------------------------------------");

		for (WalletTransaction wt : transactions) {

			String str = "";
			if (wt.getTransactionType() == 1) {
				str = str + "DEPOSIT";
			}
			if (wt.getTransactionType() == 2) {
				str = str + "WITHDRAW";
			}
			if (wt.getTransactionType() == 3) {
				str = str + "FUND TRANSFER";
			}

			str = str + "\t\t" + wt.getTransactionDate();

			str = str + "\t\t" + wt.getTransactionAmt();
			System.out.println(str);
		}

		System.out
				.println("------------------------------------------------------------------");

	}
}
