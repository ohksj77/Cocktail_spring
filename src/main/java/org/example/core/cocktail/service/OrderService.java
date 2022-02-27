package org.example.core.cocktail.service;

import org.example.core.cocktail.domain.Cocktail;
import org.example.core.cocktail.domain.CocktailPurchase;
import org.example.core.cocktail.domain.Ingredient;
import org.example.core.cocktail.domain.Purchase;

import java.util.Map;
import java.util.List;

public interface OrderService {
    void purchaseCocktail(Map<Long, Integer> money, Purchase purchase);
    List<Ingredient> searchIngredient(Cocktail cocktail);
    List<Cocktail> searchWhich(Purchase purchase);
    Map<Integer, Integer> gainByDay();
    Map<Integer, Integer> gainByWeek();
    Map<Integer, Integer> gainByMonth();
    List<Purchase> searchAll();
}
