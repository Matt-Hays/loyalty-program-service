package com.courseproject.loyaltyservice.services;

import com.courseproject.loyaltyservice.models.LoyaltyAccount;
import com.courseproject.loyaltyservice.repositories.LoyaltyAccountRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class LoyaltyAccountService {
    private final LoyaltyAccountRepository loyaltyAccountRepository;

    public LoyaltyAccount createLoyaltyAccount(LoyaltyAccount loyaltyAccount) {
        return loyaltyAccountRepository.save(loyaltyAccount);
    }

    public LoyaltyAccount getLoyaltyAccountById(UUID id) {
        return loyaltyAccountRepository.findById(id).orElse(null);
    }

    public List<LoyaltyAccount> getAllLoyaltyAccounts() {
        return loyaltyAccountRepository.findAll();
    }

    @Transactional
    public LoyaltyAccount updateLoyaltyAccount(UUID id, LoyaltyAccount loyaltyAccount) throws OptimisticLockingFailureException {
        LoyaltyAccount oldAccount = getLoyaltyAccountById(id);
        if (oldAccount == null) return null;
        if (loyaltyAccount.getBalance() != null) {
            Double newBalance = oldAccount.getBalance();
            newBalance -= loyaltyAccount.getBalance();
            if (newBalance < 0) throw new IllegalArgumentException("Loyalty account balance cannot be negative");
            oldAccount.setBalance(newBalance);
        }
        if (loyaltyAccount.getMembershipLevel() != null) {
            oldAccount.setMembershipLevel(loyaltyAccount.getMembershipLevel());
        }
        return loyaltyAccountRepository.save(oldAccount);
    }

    public void deleteLoyaltyAccountById(UUID id) {
        loyaltyAccountRepository.deleteById(id);
    }
}
