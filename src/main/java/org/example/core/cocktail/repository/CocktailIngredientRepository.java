package org.example.core.cocktail.repository;

import org.example.core.cocktail.domain.Cocktail;
import org.example.core.cocktail.domain.CocktailIngredient;
import org.example.core.cocktail.domain.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CocktailIngredientRepository extends JpaRepository<CocktailIngredient, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE CocktailIngredient u SET u.cocktail = ?2, u.ingredient = ?3 WHERE u.id = ?1")
    void updateCoIn(Long id, Cocktail cocktail, Ingredient ingredient);

    @Query(value = "INSERT INTO CocktailIngredient u(u.cocktail, u.ingredient) VALUES(?1, ?2)", nativeQuery = true)
    void saveOne(Cocktail cocktail, Ingredient ingredient);
}
