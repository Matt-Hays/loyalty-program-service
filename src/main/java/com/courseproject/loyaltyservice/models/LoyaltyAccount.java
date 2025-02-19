package com.courseproject.loyaltyservice.models;

import com.courseproject.loyaltyservice.models.enums.MembershipLevel;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class LoyaltyAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    private Long version;

    @Min(0)
    @NotNull
    private Double balance;

    @Enumerated(EnumType.STRING)
    @NotNull
    private MembershipLevel membershipLevel = MembershipLevel.NONE;

    @OneToOne(cascade = CascadeType.ALL)
    @NotNull
    @JoinColumn(nullable = false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private Customer customer;
}
