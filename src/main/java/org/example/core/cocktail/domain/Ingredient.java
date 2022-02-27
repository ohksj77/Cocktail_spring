package org.example.core.cocktail.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
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
}
