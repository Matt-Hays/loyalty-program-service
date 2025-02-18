package com.courseproject.loyaltyservice.repositories;

import com.courseproject.loyaltyservice.models.LoyaltyAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LoyaltyAccountRepository extends JpaRepository<LoyaltyAccount, UUID> {
}
