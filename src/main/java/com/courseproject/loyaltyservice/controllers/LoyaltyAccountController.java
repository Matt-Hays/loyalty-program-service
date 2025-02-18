package com.courseproject.loyaltyservice.controllers;

import com.courseproject.loyaltyservice.models.LoyaltyAccount;
import com.courseproject.loyaltyservice.services.LoyaltyAccountService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    public LoyaltyAccount getLoyaltyAccountById(@PathVariable UUID id) {
        return loyaltyAccountService.getLoyaltyAccountById(id);
    }

    @PatchMapping("/{id}")
    public LoyaltyAccount updateLoyaltyAccount(@PathVariable UUID id, @RequestBody @Valid LoyaltyAccount loyaltyAccount) {
        return loyaltyAccountService.updateLoyaltyAccount(id, loyaltyAccount);
    }

    @DeleteMapping("/{id}")
    public void deleteLoyaltyAccountById(@PathVariable UUID id) {
        loyaltyAccountService.deleteLoyaltyAccountById(id);
    }
}
