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
public class Cocktail {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;
    private Integer price;
    private Double proof;
}
