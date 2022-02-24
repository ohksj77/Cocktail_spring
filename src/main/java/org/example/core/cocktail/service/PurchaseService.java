package org.example.core.cocktail.service;

import org.example.core.cocktail.domain.Cocktail;
import org.example.core.cocktail.domain.Purchase;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface PurchaseService {
    void purchaseCocktail(Integer num, Purchase purchase);
    List<Purchase> searchAll();
    List<Purchase> searchByTime(LocalDateTime time);
    List<Purchase> searchByDate(LocalDate date);
    List<Purchase> searchByMonth(LocalDate date);
    void deleteAllRecord();
    Cocktail searchWhich(Purchase purchase);
}
