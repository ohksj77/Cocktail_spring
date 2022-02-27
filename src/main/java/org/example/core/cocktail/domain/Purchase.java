package org.example.core.cocktail.domain;

import java.time.LocalDateTime;
import java.util.List;

import lombok.*;

import javax.persistence.*;

@Builder
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
}
