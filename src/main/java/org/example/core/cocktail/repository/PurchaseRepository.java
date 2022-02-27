package org.example.core.cocktail.repository;

import org.example.core.cocktail.domain.Cocktail;
import org.example.core.cocktail.domain.Ingredient;
import org.example.core.cocktail.domain.Purchase;
import org.example.core.cocktail.domain.PurchaseMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    @Query("SELECT u FROM Purchase u WHERE u.time > ?1 and u.time < ?2")
    List<Purchase> searchByDate(LocalDateTime startTime, LocalDateTime endTIme);

    @Query("SELECT u FROM Purchase u WHERE u.id = ?1")
    Purchase whichCocktail(Long id);


    @Query("SELECT u FROM Purchase u WHERE u.method = ?1")
    List<Purchase> findByMethod(PurchaseMethod method);
}
