package org.example.core.cocktail.service;

import org.example.core.cocktail.domain.Cocktail;
import org.example.core.cocktail.domain.CocktailPurchase;
import org.example.core.cocktail.domain.Purchase;
import org.example.core.cocktail.domain.PurchaseMethod;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface PurchaseService {
    List<Purchase> searchAll();
    List<Purchase> searchByTime(LocalDateTime time);
    List<Purchase> searchByDate(LocalDate date);
    List<Purchase> searchByMonth(LocalDate date);
    List<Purchase> searchByMethod(PurchaseMethod method);
    void deleteAllRecord();
    Map<Integer, Integer> statByDay();
    Map<Integer, Integer> statByTime();
    Map<Integer, Integer> statByMonth();
    Map<Integer, Integer> howManyPurchase();
}
