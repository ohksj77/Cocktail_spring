package org.example.api;

import org.example.core.cocktail.domain.Cocktail;
import org.example.core.cocktail.domain.Ingredient;
import org.example.core.cocktail.domain.Purchase;
import org.example.core.cocktail.service.CocktailService;
import org.example.core.cocktail.service.IngredientService;
import org.example.core.cocktail.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class CocktailController {

    private final PurchaseService purchaseService;
    private final CocktailService cocktailService;
    private final IngredientService ingredientService;


    @Autowired
    public CocktailController(PurchaseService purchaseService, CocktailService cocktailService, IngredientService ingredientService) {
        this.purchaseService = purchaseService;
        this.cocktailService = cocktailService;
        this.ingredientService = ingredientService;
    }

    @GetMapping
    public ModelAndView mainPage(ModelAndView mav) {
        mav.setViewName("index");
        return mav;
    }

    @PostMapping("/purchase")
    public void purchase(@RequestBody Integer num, @RequestParam Purchase purchase){
        purchaseService.purchaseCocktail(num, purchase);
    }

    @GetMapping("/all")
    public List<Purchase> searchAllPurchase(){
        return purchaseService.searchAll();
    }

    @PostMapping("/cocktail")
    public void addCocktail(@RequestBody Cocktail cocktail){
        cocktailService.addCocktail(cocktail);
    }

    @PostMapping("/ingredient")
    public void addIngredient(@RequestBody Ingredient ingredient){
        ingredientService.addIngredient(ingredient);
    }
}
