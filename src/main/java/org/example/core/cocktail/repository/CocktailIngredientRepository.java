package org.example.core.cocktail.repository;

import org.example.core.cocktail.domain.Cocktail;
import org.example.core.cocktail.domain.CocktailIngredient;
import org.example.core.cocktail.domain.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CocktailIngredientRepository extends JpaRepository<CocktailIngredient, Long> {
    @Query("SELECT u.cocktail FROM CocktailIngredient u")
    List<Cocktail> findCocktail();

    @Query("SELECT u.ingredient FROM CocktailIngredient u where u.cocktail = ?1")
    List<Ingredient> findIngredient(Cocktail cocktail);
}
