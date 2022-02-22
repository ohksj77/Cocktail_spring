package org.example.core.cocktail.service;

import org.example.core.cocktail.domain.Purchase;

import java.util.List;

public interface PurchaseService {
    void purchaseCocktail(Purchase purchase);
    List<Purchase> searchAll();
}
