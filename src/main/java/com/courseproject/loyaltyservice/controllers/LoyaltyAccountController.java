package com.courseproject.loyaltyservice.controllers;

import com.courseproject.loyaltyservice.models.LoyaltyAccount;
import com.courseproject.loyaltyservice.models.dto.LoyaltyAccountDTO;
import com.courseproject.loyaltyservice.services.LoyaltyAccountService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidParameterException;
import java.util.List;

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
    public LoyaltyAccount createLoyaltyAccount(@RequestBody LoyaltyAccountDTO loyaltyAccountDTO) {
        return loyaltyAccountService.createLoyaltyAccount(loyaltyAccountDTO);
    }

    @PostMapping("/{id}/credit/{points}")
    public ResponseEntity<LoyaltyAccount> creditPoints(@PathVariable Long id, @PathVariable("points") Double points) {
        try {
            LoyaltyAccount loyaltyAccount = loyaltyAccountService.creditPoints(id, points);
            return ResponseEntity.ok(loyaltyAccount);
        } catch (InvalidParameterException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{id}/deduct")
    public ResponseEntity<LoyaltyAccount> deductPoints(@PathVariable Long id, @RequestParam("points") Double points) {
        try {
            LoyaltyAccount loyaltyAccount = loyaltyAccountService.deductPoints(id, points);
            return ResponseEntity.ok(loyaltyAccount);
        } catch (InvalidParameterException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/{id}")
    public LoyaltyAccount updateLoyaltyAccount(@PathVariable Long id,
            @RequestBody @Valid LoyaltyAccount loyaltyAccount) {
        return loyaltyAccountService.updateLoyaltyAccount(id, loyaltyAccount);
    }

    @DeleteMapping("/{id}")
    public void deleteLoyaltyAccountById(@PathVariable Long id) {
        loyaltyAccountService.deleteLoyaltyAccountById(id);
    }
}
