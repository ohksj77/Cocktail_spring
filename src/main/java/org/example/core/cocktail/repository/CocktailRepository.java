package org.example.core.cocktail.repository;

import org.example.core.cocktail.domain.Cocktail;
import org.example.core.cocktail.domain.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CocktailRepository extends JpaRepository<Cocktail, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Cocktail u SET u.price = ?2, u.proof = ?3 WHERE u.id = ?1")
    void updateCocktail(Long id, Integer price, Integer proof);

    @Modifying
    @Transactional
    @Query("DELETE FROM Cocktail u WHERE u.cocktail = ?1")
    void deleteByName(String name);

    @Query("SELECT u FROM Cocktail u WHERE u.cocktail = ?1")
    Object findByName(String name);
}
