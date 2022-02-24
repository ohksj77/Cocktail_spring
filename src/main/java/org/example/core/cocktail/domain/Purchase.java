package org.example.core.cocktail.domain;

import java.time.LocalDateTime;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Purchase {

    @Id
    @GeneratedValue
    private Long id;
    private Integer amount;
    private LocalDateTime time;

    @Enumerated(EnumType.STRING)
    private PurchaseMethod method;

    @ManyToOne
    @JoinColumn(name = "cocktail_type")
    private Cocktail cocktail;
}
