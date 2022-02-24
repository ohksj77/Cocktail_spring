package org.example.core.cocktail.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Cocktail {

    @Id
    @GeneratedValue
    private Long id;
    private String cocktail;

    @OneToMany(mappedBy="ingredient")
    private List<CocktailIngredient> ingredient;
    private Integer price;
    private Integer proof;
}
