//package com.Senorial.Java.Web;
//
//import com.Senorial.Java.Domain.Account;
//import com.Senorial.Java.Domain.Transaction;
//import com.Senorial.Java.Domain.User;
//import com.Senorial.Java.Services.AccountService;
//import com.Senorial.Java.Services.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.Collections;
//
//@Controller
//public class AccountController2 {
//
//	@Autowired
//	private AccountService accountService;
//	@Autowired
//	private UserService userService;
//
//	@GetMapping("users/{userId}/accounts/{accountId}")
//	public String getAccounts (ModelMap model, @PathVariable Long accountId, @PathVariable Long userId) {
//		Account account = accountService.findAccountById(accountId);
//		User user = account.getUsers().get(0);
//
//		model.put("account", account);
//		model.put("user", user);
//
//		return "accounts";
//	}
//
//	@PostMapping("users/{userId}/accounts")
//	public String postOneAccount (@PathVariable Long userId, @ModelAttribute Account account) {
////		account.setUsers((List<User>) userService.findById(userId));
////		userService.findById(userId).getAccounts();
//
////		List<User> users = (List<User>) userService.findById(userId);
//		User users = userService.findById(userId);
//		account.setUsers(Collections.singletonList(users));
////		account.setUsers((List<User>) users);
////		users.get(0).getAccounts().add(account);
//		users.getAccounts().add(account);
//		userService.saveUser(users);
//		accountService.save(account);
//
//
//		return "redirect:/users/" + userId;
//	}
//
//	@PostMapping("users/{userId}/accounts/{accountId}")
//	public String updateAccountName (@PathVariable Long userId, @PathVariable Long accountId, @ModelAttribute Account account) {
//		Account currentAccount = accountService.findAccountById(accountId);
//		currentAccount.setAccountName(account.getAccountName());
//		accountService.save(currentAccount);
//
//		userService.saveUser(userService.findById(userId));
//
//		System.out.println(account.getAccountName());
//		return "redirect:/users/" + userId + "/accounts/" + accountId;
//	}
//
//
//	@GetMapping("users/{userId}/account/{accountId}")
//	public String showAccountDetails(
//			@PathVariable Long userId,
//			@PathVariable Long accountId, Model model) {
//		Account account = accountService.findAccountById(accountId);
//		model.addAttribute("account", account);
//		return "account";
//	}
//
//	@PostMapping("users/{userId}/account/{accountId}/add-transaction")
//	public String addTransaction(
//			@PathVariable Long userId,
//			@PathVariable Long accountId,
//			@RequestParam("transactionDate") String transactionDate,
//			@RequestParam("amount") Double amount,
//			@RequestParam("type") String type) {
//
//		LocalDateTime parsedDate = LocalDate.parse(transactionDate).atStartOfDay();
//		accountService.addTransaction(accountId, parsedDate, amount, type);
//
//		// Redirect back to the account details page after adding the transaction
//		return "redirect:/account/{accountId}";
//	}
//
//	@GetMapping("/account-details/{accountId}")
//	public String showAccountDetails2(@PathVariable Long accountId, Model model) {
//		Account account = accountService.findAccountById(accountId);
//		model.addAttribute("account", account);
//		return "account-details";
//	}
//
//	@PostMapping("/add-transaction")
//	public String addTransaction(@RequestParam Double amount, @RequestParam String type, @RequestParam Long accountId) {
//		Account tempAccount = accountService.findAccountById(accountId);
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
//}
