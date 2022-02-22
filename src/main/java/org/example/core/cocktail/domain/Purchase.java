package org.example.core.cocktail.domain;

import java.time.LocalDateTime;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Purchase {

    @Id
    @GeneratedValue
    private Long id;
    private Integer amount;
    private LocalDateTime time;
    private String method;

    @ManyToOne
    @JoinColumn(name = "cocktail_type")
    private Cocktail cocktail;
}
