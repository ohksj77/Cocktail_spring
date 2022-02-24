package org.example.core.cocktail.repository;

import org.example.core.cocktail.domain.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Ingredient u SET u.price = ?2, u.number = ?3, u.name = ?4 WHERE u.id = ?1")
    void updateIngredient(Long id, Integer price, Integer number, String name);

    @Modifying
    @Transactional
    @Query("DELETE FROM Ingredient u WHERE u.name = ?1")
    void deleteAllByName(String name);
}
