package org.example.core.cocktail.service.impl;

import org.example.core.cocktail.domain.*;
import org.example.core.cocktail.repository.CocktailIngredientRepository;
import org.example.core.cocktail.repository.CocktailPurchaseRepository;
import org.example.core.cocktail.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class OrderServiceImpl implements OrderService {

    private final CocktailPurchaseRepository cocktailPurchaseRepository;
    private final CocktailIngredientRepository cocktailIngredientRepository;

    @Autowired
    public OrderServiceImpl(CocktailPurchaseRepository cocktailPurchaseRepository, CocktailIngredientRepository cocktailingredientRepository) {
        this.cocktailPurchaseRepository = cocktailPurchaseRepository;
        this.cocktailIngredientRepository = cocktailingredientRepository;
    }

    @Override
    public void purchaseCocktail(Map<Long, Integer> money, Purchase purchase) {
        List<Cocktail> cp = cocktailPurchaseRepository.findWhichCocktail(purchase);
        List<Ingredient> ingredientList = new ArrayList<>();
        int sum = 0;
        int i = 0;
        for (Cocktail cocktail : cp){
            sum += money.get(cocktail.getId()) * cocktail.getPrice();
            ingredientList.addAll(cocktailIngredientRepository.findIngredient(cocktail));
        }
        if(sum != purchase.getAmount())
            throw new RuntimeException();
        for (Cocktail cocktail : cp)
            ingredientList.addAll(cocktailIngredientRepository.findIngredient(cocktail));
        for (Ingredient ingredient : ingredientList)
            if(ingredient.getNumber() < 1)
                throw new RuntimeException();
        purchase.setAmount(sum);
        purchase.setTime(LocalDateTime.now());
        for (Cocktail cocktail : cp) {
            CocktailPurchase cocktailPurchase = CocktailPurchase.builder().cocktail(cocktail).purchase(purchase).build();
            cocktailPurchaseRepository.save(cocktailPurchase);
        }
    }

    @Override
    public List<Ingredient> searchIngredient(Cocktail cocktail) {
        return cocktailIngredientRepository.findIngredient(cocktail);
    }

    @Override
    public List<Cocktail> searchWhich(Purchase purchase) {
        return cocktailPurchaseRepository.findWhichCocktail(purchase);
    }

    @Override
    public Map<Integer, Integer> gainByDay() {
        Map<Integer, List<Purchase>> stat = searchAll().stream().collect((Collectors.groupingBy(p -> p.getTime().getDayOfYear())));
        return netGain(stat);
    }

    @Override
    public Map<Integer, Integer> gainByWeek() {
        Map<Integer, List<Purchase>> stat = searchAll().stream().collect((Collectors.groupingBy(p -> p.getTime().getDayOfWeek().getValue())));
        return netGain(stat);
    }

    @Override
    public Map<Integer, Integer> gainByMonth() {
        Map<Integer, List<Purchase>> stat = searchAll().stream().collect((Collectors.groupingBy(p -> p.getTime().getMonthValue())));
        return netGain(stat);
    }

    @Override
    public List<Purchase> searchAll() {
        return cocktailPurchaseRepository.findAllPurchase();
    }

    public Map<Integer, Integer> netGain(Map<Integer, List<Purchase>> stat){
        Map<Integer, Integer> result = new HashMap<>();
        for (Integer i : stat.keySet()){
            int sum = 0, tmp = 0;
            for(int j = 0; j < stat.get(i).size(); j++){
                List<Ingredient> ingredient = new ArrayList<>();
                List<Cocktail> cocktailList = cocktailPurchaseRepository.findWhichCocktail(stat.get(i).get(j));
                for (Cocktail cocktail : cocktailList) {
                    ingredient.addAll(cocktailIngredientRepository.findIngredient(cocktail));
                }
                sum += stat.get(i).get(j).getAmount();
                for (Ingredient c : ingredient) {
                    sum -= c.getPrice();
                }
            }
            result.put(i, sum);
        }
        return result;
    }
}
