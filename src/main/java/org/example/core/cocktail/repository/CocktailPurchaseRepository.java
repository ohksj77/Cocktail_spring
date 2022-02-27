package org.example.core.cocktail.repository;

import org.example.core.cocktail.domain.Cocktail;
import org.example.core.cocktail.domain.CocktailPurchase;
import org.example.core.cocktail.domain.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CocktailPurchaseRepository extends JpaRepository<CocktailPurchase, Long> {
    @Query("SELECT u.purchase FROM CocktailPurchase u")
    List<Purchase> findPurchase();

    @Query("SELECT u.cocktail FROM CocktailPurchase u")
    List<Cocktail> findCocktail();

    @Query("SELECT u.cocktail FROM CocktailPurchase u where u.purchase = ?1")
    List<Cocktail> findWhichCocktail(Purchase purchase);

    @Query("SELECT u.purchase FROM CocktailPurchase u")
    List<Purchase> findAllPurchase();
}
