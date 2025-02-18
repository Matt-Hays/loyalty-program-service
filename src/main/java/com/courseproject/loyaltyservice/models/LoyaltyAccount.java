package com.courseproject.loyaltyservice.models;

import com.courseproject.loyaltyservice.models.enums.MembershipLevel;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class LoyaltyAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Version
    private Long version;

    @Min(0)
    @NotNull
    private Double balance;

    @Enumerated(EnumType.STRING)
    @NotNull
    private MembershipLevel membershipLevel = MembershipLevel.NONE;

    @OneToOne
    @NotNull
    @JoinColumn(nullable = false)
    private Customer customer;
}
