package com.Senorial.Java.Services;

import com.Senorial.Java.Domain.Account;
import com.Senorial.Java.Domain.Transaction;
import com.Senorial.Java.Domain.User;
import com.Senorial.Java.Repositories.AccountRepository;
import com.Senorial.Java.Repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Service class responsible for handling operations related to accounts.
 */
@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepo;

	@Autowired
	private UserRepository userRepository;
	/**
	 * Retrieves all accounts from the repository.
	 * @return List of all accounts.
	 */
	public List<Account> findAll() {
		return accountRepo.findAll();
	}

	/**
	 * Saves an account to the repository.
	 * @param account The account to be saved.
	 * @return The saved account.
	 */
	public Account saveAccount(Account account) {
		return accountRepo.save(account);
	}

	/**
	 * Retrieves an account by its ID.
	 * @param accountId The ID of the account to be retrieved.
	 * @return The account if found, else an empty Account object.
	 */
	public Account findById(Long accountId) {
		Optional<Account> accountOptional = accountRepo.findById(accountId);
		return accountOptional.orElse(new Account());
	}

	/**
	 * Deletes an account by its ID.
	 * @param accountId The ID of the account to be deleted.
	 */
	public void delete(Long accountId) {
		accountRepo.deleteById(accountId);
	}

	/**
	 * Finds the index of an account in the provided list of accounts.
	 * @param accounts List of accounts.
	 * @param accountId ID of the account to find.
	 * @return Index of the account in the list (starting from 1) if found, otherwise 0.
	 */
	public Integer findAccountIndex(List<Account> accounts, Long accountId) {
		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i).getAccountId().equals(accountId)) {
				return i + 1; // Indexing starts from 1
			}
		}
		return 0; // Account not found
	}

	/**
	 * Assigns a name to a new bank account based on its index and saves it.
	 * @param account The account to name.
	 * @param user The user associated with the account.
	 */
	public void nameNewBankAccount(Account account, User user) {
		Integer accountIndex = findAccountIndex(user.getAccounts(), account.getAccountId());
		account.setAccountName("Account # " + accountIndex);
		saveAccount(account);
	}

	/**
	 * Creates a new bank account associated with a user and saves it.
	 * @param account The account to be created and saved.
	 * @param user The user associated with the account.
	 */
	public void createAndSaveNewBankAccount(Account account, User user) {
		account.getUsers().add(user);
		user.getAccounts().add(account);
		saveAccount(account);
	}

	/**
	 * Saves an account to the repository.
	 * @param account The account to be saved.
	 * @return The saved account.
	 */
	public Account save(Account account) {
		return accountRepo.save(account);
	}

	/**
	 * Adds a transaction to an account identified by its ID.
	 * It creates a new Transaction object, associates it with the account, and saves the account.
	 * @param accountId The ID of the account to which the transaction belongs.
	 * @param transactionDate The date of the transaction.
	 * @param amount The amount of the transaction.
	 * @param type The type of the transaction.
	 */
	public void addTransaction(Long accountId, LocalDateTime transactionDate, Double amount, String type) {
		System.out.println("ADD TRANSACTION WAS CALLED **********************");
		Account account = findById(accountId);

		// Logging transaction details for debugging
		System.out.println("Account Id = " + accountId + ", Transaction date = " + transactionDate + ", Amount = " + amount + ", Type= " + type);

		if (account.getAccountId() != null) {
			// Creating a new Transaction object
			Transaction transaction = new Transaction();
			transaction.setTransactionDate(transactionDate);
			transaction.setAmount(amount);
			transaction.setType(type);
			transaction.setAccount(account);
			// Associating the transaction with the account
			account.getTransactions().add(transaction);


			// Saving the updated account
			accountRepo.save(account);

			// Logging updated account transactions for debugging
			System.out.println("Account ID = " + account.getAccountId() + ", Account Transactions = " + account.getTransactions());
			System.out.println("END OF TRANSACTION *******************************");


//			ADD TRANSACTION WAS CALLED **********************
//			Hibernate: select a1_0.account_id,a1_0.account_name from accounts a1_0 where a1_0.account_id=?
//			Account Id = 1, Transaction date = 2024-03-15T00:00, Amount = 0.01, Type= s
//			Hibernate: select t1_0.account_id,t1_0.transaction_id,t1_0.amount,t1_0.transaction_date,t1_0.type from transactions t1_0 where t1_0.account_id=?
//			Account ID = 1, Account Transactions = []
//			END OF TRANSACTION *******************************
		}
	}
}
