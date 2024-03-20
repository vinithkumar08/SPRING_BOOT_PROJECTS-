package BankingApp;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AccountService {
    @Autowired
    Repo accountRepository;
	
	public Accounts createAccount(Accounts account) {
		
	    return accountRepository.save(account);
    }

    public Optional<Accounts> getAccount(Long id) {
    	
        return accountRepository.findById(id);
    }

    public Accounts deposit(Long id, double amount) {
       Accounts account = getAccount(id).orElseThrow(() -> new RuntimeException("Account not found"));
       account.setBalance(account.getBalance() + amount);
    
       return accountRepository.save(account);
    }

    public Accounts withdraw(Long id, double amount) {
       Accounts account = getAccount(id).orElseThrow(() -> new RuntimeException("Account not found"));
           if (account.getBalance() < amount) {
             throw new RuntimeException("Insufficient funds");
           }
       account.setBalance(account.getBalance() - amount);
    
       return accountRepository.save(account);
    }
}

