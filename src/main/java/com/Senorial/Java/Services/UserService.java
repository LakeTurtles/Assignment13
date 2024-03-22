package com.Senorial.Java.Services;

import com.Senorial.Java.Domain.Account;
import com.Senorial.Java.Domain.Address;
import com.Senorial.Java.Domain.User;
import com.Senorial.Java.Repositories.AccountRepository;
import com.Senorial.Java.Repositories.AddressRepository;
import com.Senorial.Java.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private AccountRepository accountRepo;

    @Autowired
    private AddressRepository addressRepo;

    public Optional<List<User>> findByUsername(String username) {
        return Optional.ofNullable(userRepo.findByUsername(username));
    }

    public List<User> findByNameAndUsername(String name, String username) {
        return userRepo.findByNameAndUsername(name, username);
    }

    public List<User> findByCreatedDateBetween(LocalDate date1, LocalDate date2) {
        return userRepo.findByCreatedDateBetween(date1, date2);
    }

    public User findExactlyOneUserByUsername(String username) {
        List<User> users = userRepo.findExactlyOneUserByUsername(username);
        if (users.size() > 0)
            return users.get(0);
        else
            return new User();
    }

    public Set<User> findAll () {
        return userRepo.findAllUsersWithAccountsAndAddresses();
    }

    public User findById(Long userId) {
        Optional<User> userOpt = userRepo.findById(userId);
        return userOpt.orElse(new User());
    }

    public User saveUser(User user) {
        if (user.getUserId() == null) {



                Account checking = new Account();
                checking.setAccountName("Checking Account");

                checking.getUsers().add(user);         // <-------------

                Account savings = new Account();
                savings.setAccountName("Savings Account");

                savings.getUsers().add(user);     // <-------------

                user.getAccounts().add(checking);
                user.getAccounts().add(savings);

                accountRepo.save(checking);     // <-------------
                accountRepo.save(savings);      // <-------------



        }


        if(user.getAddress() == null){
            Address address = new Address();
            address.setAddressLine1("344 Ocean Blvd");
            address.setAddressLine2("Apt 1");
            address.setCity("Los Angeles");
            address.setRegion("California");
            address.setZipCode("90210");
            address.setCountry("U.S.A.");
            address.setUser(user);
            address.setUserId(user.getUserId());
            user.setAddress(address);
        }

        return userRepo.save(user);
    }

    public void updateAddress(User user) {
        User existingUser = userRepo.findById(user.getUserId()).orElse(null);
        Address existingAddress = addressRepo.findById(user.getUserId()).orElse(null);

        if (existingUser != null && existingAddress != null) {
            existingUser.setUsername(existingUser.getUsername());
            existingUser.setPassword(existingUser.getPassword());
            existingUser.setName(existingUser.getName());
            existingUser.setAccounts(existingUser.getAccounts());


            existingAddress.setAddressLine1(user.getAddress().getAddressLine1());
            existingAddress.setAddressLine2(user.getAddress().getAddressLine2());
            existingAddress.setCity(user.getAddress().getCity());
            existingAddress.setRegion(user.getAddress().getRegion());
            existingAddress.setCountry(user.getAddress().getCountry());
            existingAddress.setZipCode(user.getAddress().getCountry());

            userRepo.save(existingUser);
            addressRepo.save(existingAddress);
        }
    }

    public void deleteAddress(User user) {
        User existingUser = userRepo.findById(user.getUserId()).orElse(null);
        Address existingAddress = addressRepo.findById(user.getUserId()).orElse(null);

        if (existingUser != null && existingAddress != null) {
            existingUser.setUsername(existingUser.getUsername());
            existingUser.setPassword(existingUser.getPassword());
            existingUser.setName(existingUser.getName());
            existingUser.setAccounts(existingUser.getAccounts());

            existingAddress.setAddressLine1("");
            existingAddress.setAddressLine2("");
            existingAddress.setCity("");
            existingAddress.setRegion("");
            existingAddress.setCountry("");
            existingAddress.setZipCode("");

            userRepo.save(existingUser);
            addressRepo.save(existingAddress);
        }
    }

    public void delete(Long userId) {
        userRepo.deleteById(userId);
    }




}




