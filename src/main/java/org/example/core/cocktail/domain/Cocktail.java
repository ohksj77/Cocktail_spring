package org.example.core.cocktail.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cocktail {

    @Id
    @GeneratedValue
    private Long id;
    private String cocktail;

    @ManyToOne
    @JoinColumn(name = "cocktail_ingr")
    private Ingredient ingredient;
    private Integer price;
    private Integer proof;
}
