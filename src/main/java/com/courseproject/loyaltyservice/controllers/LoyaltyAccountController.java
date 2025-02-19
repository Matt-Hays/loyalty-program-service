package com.courseproject.loyaltyservice.controllers;

import com.courseproject.loyaltyservice.models.LoyaltyAccount;
import com.courseproject.loyaltyservice.services.LoyaltyAccountService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.lang.Long;

@RestController
@AllArgsConstructor
@RequestMapping("v1/loyalty")
public class LoyaltyAccountController {
    private final LoyaltyAccountService loyaltyAccountService;

    @GetMapping
    public List<LoyaltyAccount> getAllLoyaltyAccounts() {
        return loyaltyAccountService.getAllLoyaltyAccounts();
    }

    @GetMapping("/{id}")
    public LoyaltyAccount getLoyaltyAccountById(@PathVariable Long id) {
        return loyaltyAccountService.getLoyaltyAccountById(id);
    }

    @PostMapping
    public LoyaltyAccount createLoyaltyAccount(@RequestBody @Valid LoyaltyAccount loyaltyAccount) {
        return loyaltyAccountService.createLoyaltyAccount(loyaltyAccount);
    }

    @PatchMapping("/{id}")
    public LoyaltyAccount updateLoyaltyAccount(@PathVariable Long id, @RequestBody @Valid LoyaltyAccount loyaltyAccount) {
        return loyaltyAccountService.updateLoyaltyAccount(id, loyaltyAccount);
    }

    @DeleteMapping("/{id}")
    public void deleteLoyaltyAccountById(@PathVariable Long id) {
        loyaltyAccountService.deleteLoyaltyAccountById(id);
    }
}
