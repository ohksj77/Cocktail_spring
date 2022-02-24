package org.example.core.cocktail.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Ingredient {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Integer number;
    private Integer price;

    @OneToMany(mappedBy="cocktail")
    private List<CocktailIngredient> cocktail;
}
