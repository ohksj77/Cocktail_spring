package org.example.core.cocktail.service.impl;

import org.example.core.cocktail.domain.Cocktail;
import org.example.core.cocktail.domain.CocktailIngredient;
import org.example.core.cocktail.domain.Ingredient;
import org.example.core.cocktail.domain.Purchase;
import org.example.core.cocktail.repository.PurchaseRepository;
import org.example.core.cocktail.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;

    @Autowired
    public PurchaseServiceImpl(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    @Override
    public void purchaseCocktail(Integer num, Purchase purchase) {
        Cocktail cocktail = purchase.getCocktail();
        if(num * cocktail.getPrice() != purchase.getAmount()){
            throw new RuntimeException();
        }
        List<CocktailIngredient> ingredientList = cocktail.getIngredient();
        for (CocktailIngredient ingredient : ingredientList) {
            if(ingredient.getIngredient().getNumber() < 1){
                throw new RuntimeException();
            }
        }
        purchase.setAmount(num * cocktail.getPrice());
        purchaseRepository.save(purchase);
    }

    @Override
    public List<Purchase> searchAll() {
        return purchaseRepository.findAll();
    }

    @Override
    public List<Purchase> searchByTime(LocalDateTime time) {
        return purchaseRepository.searchByTime(time, time.plusHours(1));
    }

    @Override
    public List<Purchase> searchByDate(LocalDate date) {
        return purchaseRepository.searchByDate(date.atStartOfDay(), date.plusDays(1).atStartOfDay());
    }

    @Override
    public List<Purchase> searchByMonth(LocalDate date) {
        return purchaseRepository.searchByDate(date.atStartOfDay(), date.plusMonths(1).atStartOfDay());
    }

    @Override
    public void deleteAllRecord() {
        purchaseRepository.deleteAll();
    }

    @Override
    public Cocktail searchWhich(Purchase purchase) {
        return purchaseRepository.whichCocktail(purchase.getId()).get();
    }
}
