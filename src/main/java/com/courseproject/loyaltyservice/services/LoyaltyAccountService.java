package com.courseproject.loyaltyservice.services;

import com.courseproject.loyaltyservice.models.Customer;
import com.courseproject.loyaltyservice.models.LoyaltyAccount;
import com.courseproject.loyaltyservice.models.dto.LoyaltyAccountDTO;
import com.courseproject.loyaltyservice.repositories.LoyaltyAccountRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;

@Service
@AllArgsConstructor
public class LoyaltyAccountService {
    private final LoyaltyAccountRepository loyaltyAccountRepository;
    private final CustomerService customerService;
    private final RedisTemplate<Long, LoyaltyAccount> redisTemplate;

    public LoyaltyAccount createLoyaltyAccount(LoyaltyAccountDTO loyaltyAccountDTO) {
        Customer customer = customerService.getCustomerById(loyaltyAccountDTO.customerDTO().id());
        LoyaltyAccount loyaltyAccount = new LoyaltyAccount();
        loyaltyAccount.setBalance(loyaltyAccountDTO.balance());

        customer.setLoyaltyAccount(loyaltyAccount);
        loyaltyAccount.setCustomer(customer);
        return loyaltyAccountRepository.save(loyaltyAccount);
    }

    public LoyaltyAccount creditPoints(Long id, Double points) {
        LoyaltyAccount loyaltyAccount = getLoyaltyAccountById(id);
        if (loyaltyAccount == null || 0 > points) {
            throw new InvalidParameterException();
        }
        ;

        loyaltyAccount.setBalance(loyaltyAccount.getBalance() + points);
        return loyaltyAccountRepository.save(loyaltyAccount);
    }

    public LoyaltyAccount deductPoints(Long id, Double points) {
        LoyaltyAccount loyaltyAccount = getLoyaltyAccountById(id);
        if (loyaltyAccount == null || 0 > points || loyaltyAccount.getBalance() < points) {
            throw new InvalidParameterException();
        }
        ;

        loyaltyAccount.setBalance(loyaltyAccount.getBalance() - points);
        return loyaltyAccountRepository.save(loyaltyAccount);
    }

    public LoyaltyAccount getLoyaltyAccountById(Long id) {
        LoyaltyAccount a = redisTemplate.opsForValue().get(id);
        if (a == null) {
            a = loyaltyAccountRepository.findById(id).orElseThrow(EntityNotFoundException::new);
            redisTemplate.opsForValue().set(a.getId(), a);
        }
        return a != null ? a : loyaltyAccountRepository.findById(id).orElse(null);
    }

    public List<LoyaltyAccount> getAllLoyaltyAccounts() {
        return loyaltyAccountRepository.findAll();
    }

    @Transactional
    public LoyaltyAccount updateLoyaltyAccount(Long id, LoyaltyAccount loyaltyAccount)
            throws OptimisticLockingFailureException {
        LoyaltyAccount oldAccount = getLoyaltyAccountById(id);
        if (oldAccount == null)
            return null;
        if (loyaltyAccount.getBalance() != null) {
            Double newBalance = oldAccount.getBalance();
            newBalance -= loyaltyAccount.getBalance();
            if (newBalance < 0)
                throw new IllegalArgumentException("Loyalty account balance cannot be negative");
            oldAccount.setBalance(newBalance);
        }
        if (loyaltyAccount.getMembershipLevel() != null) {
            oldAccount.setMembershipLevel(loyaltyAccount.getMembershipLevel());
        }
        return loyaltyAccountRepository.save(oldAccount);
    }

    public void deleteLoyaltyAccountById(Long id) {
        loyaltyAccountRepository.deleteById(id);
    }
}
