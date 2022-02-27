package org.example.api;

import org.example.core.cocktail.domain.Cocktail;
import org.example.core.cocktail.domain.CocktailPurchase;
import org.example.core.cocktail.domain.Ingredient;
import org.example.core.cocktail.domain.Purchase;
import org.example.core.cocktail.service.CocktailService;
import org.example.core.cocktail.service.IngredientService;
import org.example.core.cocktail.service.OrderService;
import org.example.core.cocktail.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
public class CocktailController {

    private final PurchaseService purchaseService;
    private final CocktailService cocktailService;
    private final IngredientService ingredientService;
    private final OrderService orderService;


    @Autowired
    public CocktailController(PurchaseService purchaseService, CocktailService cocktailService, IngredientService ingredientService, OrderService orderService) {
        this.purchaseService = purchaseService;
        this.cocktailService = cocktailService;
        this.ingredientService = ingredientService;
        this.orderService = orderService;
    }

    @GetMapping
    public ModelAndView mainPage(ModelAndView mav) {
        mav.setViewName("index");
        return mav;
    }

    @PostMapping("/purchase") // 1번
    public void purchase(@RequestBody Map<Long, Integer> num, @RequestParam Purchase purchase){
        orderService.purchaseCocktail(num, purchase);
    }

    @PostMapping("/cocktail") // 3번
    public void addCocktail(@RequestBody Cocktail cocktail){
        cocktailService.addCocktail(cocktail);
    }

    @PostMapping("/ingredient") // 4번
    public void addIngredient(@RequestBody Ingredient ingredient){
        ingredientService.addIngredient(ingredient);
    }


    @GetMapping("/all")
    public List<Purchase> searchAllPurchase(){
        return purchaseService.searchAll();
    }

    @GetMapping("/searchByTime") // 2번
    public List<Purchase> searchByTime(){
        return purchaseService.searchByTime(LocalDateTime.now().minusHours(1));
    }

    @PostMapping("/ModifyCocktail") //3번
    public void modifyCocktail(@RequestParam Long id, @RequestParam Cocktail cocktail){
        cocktailService.modifyCocktail(id, cocktail);
    }

    @PostMapping("/ModifyIngredient") // 4번
    public void modifyIngredient(@RequestParam Long id, @RequestParam Ingredient ingredient){
        ingredientService.modifyIngredient(id, ingredient);
    }

    @GetMapping("/whichCocktail") // 5번
    public List<Cocktail> searchWhich(@RequestParam Purchase purchase){
        return orderService.searchWhich(purchase);
    }

    @GetMapping("/ascPurchase") // 6번
    public Map<Integer, Integer> searchAscPurchase(){
        return purchaseService.statByTime();
    }

    @GetMapping("/monthSale") // 7번
    public Map<Integer, Integer> searchHowMany(){
        return purchaseService.howManyPurchase();
    }

    @GetMapping("/netGain") // 8번
    public Map<Integer, Integer> gainByday(){
        return orderService.gainByDay();
    }
}
