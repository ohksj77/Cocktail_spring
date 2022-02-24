package org.example.core.cocktail.repository;

import org.example.core.cocktail.domain.Cocktail;
import org.example.core.cocktail.domain.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    @Query("SELECT u FROM Purchase u WHERE u.time > ?1 and u.time < ?2")
    List<Purchase> searchByTime(LocalDateTime startTime, LocalDateTime endTIme);

    @Query("SELECT u FROM Purchase u WHERE u.time > ?1 and u.time < ?2")
    List<Purchase> searchByDate(LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT u FROM Purchase u WHERE u.time > ?1 and u.time < ?2")
    List<Purchase> searchByMonth(LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT u.cocktail FROM Purchase u WHERE u.id = ?1")
    Optional<Cocktail> whichCocktail(Long id);
}
