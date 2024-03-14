package com.Senorial.Java.Web;

import com.Senorial.Java.Domain.Account;
import com.Senorial.Java.Domain.Transaction;
import com.Senorial.Java.Domain.User;
import com.Senorial.Java.Services.AccountService;
import com.Senorial.Java.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;

@Controller
@RequestMapping("/users/{userId}/accounts")
public class AccountController {

	@Autowired
	private UserService userService;
	@Autowired
	private AccountService accountService;

	@PostMapping("")
	public String createAccount (@PathVariable Long userId) {
		Account account = new Account();
		User user = userService.findById(userId);
		account.getUsers().add(user);
		user.getAccounts().add(account);
		account.setAccountName("Account #" + user.getAccounts().size());
		account = accountService.save(account);

		return "redirect:/users/"+userId+"/accounts/"+account.getAccountId();
	}

	@PostMapping("{accountId}")
	public String saveAccount(Account account, @PathVariable Long userId) {
		account = accountService.save(account);
		return "redirect:/users/"+userId+"/accounts/"+account.getAccountId();
	}

	@GetMapping("{accountId}")
	public String getAccount(ModelMap model, @PathVariable Long userId, @PathVariable Long accountId) {
		Account account = accountService.findById(accountId);
		User user = userService.findById(userId);
		model.put("account", account);
		model.put("user", user);
		return "account";
	}

	@PostMapping("{accountId}/add-transaction")
	public String addTransaction(
			@PathVariable Long userId,
			@PathVariable Long accountId,
			@RequestParam("transactionDate") String transactionDate,
			@RequestParam("amount") Double amount,
			@RequestParam("type") String type) {

		LocalDateTime parsedDate = LocalDate.parse(transactionDate).atStartOfDay();
		accountService.addTransaction(accountId, parsedDate, amount, type);

		// Redirect back to the account details page after adding the transaction
		return "redirect:/users/"+userId+"/accounts/"+ accountId;
	}

	@GetMapping("/account-details/{accountId}")
	public String showAccountDetails2(@PathVariable Long accountId, Model model) {
		Account account = accountService.findById(accountId);
		model.addAttribute("account", account);
		return "account-details";
	}

//	@PostMapping("/add-transaction")
//	public String addTransaction(@RequestParam Double amount, @RequestParam String type, @RequestParam Long accountId) {
//		Account tempAccount = accountService.findById(accountId);
//
//		Transaction transaction = new Transaction();
//		transaction.setAccount(tempAccount);
//		transaction.setAmount(amount);
//		transaction.setType(type);
//		transaction.setTransactionDate(LocalDateTime.now());
//		tempAccount.getTransactions().add(transaction);
//
//		accountService.save(tempAccount);
//		return "redirect:/account-details/" + accountId;
//	}
}
