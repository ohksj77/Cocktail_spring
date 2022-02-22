package org.example.api;

import org.aspectj.lang.annotation.Before;
import org.example.core.cocktail.domain.Cocktail;
import org.example.core.cocktail.domain.Ingredient;
import org.example.core.cocktail.domain.Purchase;
import org.example.core.cocktail.repository.PurchaseRepository;
import org.example.core.cocktail.service.CocktailService;
import org.example.core.cocktail.service.IngredientService;
import org.example.core.cocktail.service.PurchaseService;
import org.example.core.cocktail.service.impl.CocktailServiceImpl;
import org.example.core.cocktail.service.impl.IngredientServiceImpl;
import org.example.core.cocktail.service.impl.PurchaseServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CocktailControllerTest {

    private PurchaseService purchaseService;
    private CocktailService cocktailService;
    private IngredientService ingredientService;

    @Test
    public void setUp(PurchaseService purchaseService, CocktailService cocktailService, IngredientService ingredientService) {
        this.purchaseService = purchaseService;
        this.cocktailService = cocktailService;
        this.ingredientService = ingredientService;
    }

    @Test
    @PostMapping("/purchase")
    public void purchase(@RequestBody Purchase purchase){
        purchaseService.purchaseCocktail(purchase);
    }

    @Test
    @GetMapping("/all")
    public List<Purchase> searchAllPurchase(){
        return purchaseService.searchAll();
    }

    @Test
    @PostMapping("/cocktail")
    public void addCocktail(@RequestBody Cocktail cocktail){
        cocktailService.addCocktail(cocktail);
    }

    @Test
    @PostMapping("/ingredient")
    public void addIngredient(@RequestBody Ingredient ingredient){
        ingredientService.addIngredient(ingredient);
    }
}
