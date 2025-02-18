package com.courseproject.loyaltyservice.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Customer {
    @Id
    @Column(unique = true, nullable = false)
    private UUID id;

    @Version
    private Long version;

    @OneToOne
    private LoyaltyAccount loyaltyAccount;
}
