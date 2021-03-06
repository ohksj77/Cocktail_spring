package org.example.api;

import org.example.core.cocktail.domain.*;
import org.example.core.cocktail.service.OrderService;
import org.example.core.cocktail.service.CocktailService;
import org.example.core.cocktail.service.IngredientService;
import org.example.core.cocktail.service.PurchaseService;
import org.example.core.cocktail.service.impl.CocktailServiceImpl;
import org.example.core.cocktail.service.impl.IngredientServiceImpl;
import org.example.core.cocktail.service.impl.OrderServiceImpl;
import org.example.core.cocktail.service.impl.PurchaseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

class CocktailControllerTest {
    private ApplicationContext context;
    private PurchaseService purchaseService;
    private CocktailService cocktailService;
    private IngredientService ingredientService;
    private OrderService orderService;
    private Purchase purchase;
    private Cocktail cocktail;
    private Ingredient ingredient;
    private CocktailIngredient cocktailIngredient;
    private CocktailPurchase cocktailPurchase;
    private List<Cocktail> cocktailList;
    private List<Ingredient> ingredientList;
    private List<Purchase> purchaseList;
    private List<CocktailIngredient>  cocktailIngredientList;

    @BeforeEach
    public void setUp() {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        purchaseService = context.getBean("purchaseServiceImpl", PurchaseServiceImpl.class);
        cocktailService = context.getBean("cocktailServiceImpl", CocktailServiceImpl.class);
        ingredientService = context.getBean("ingredientServiceImpl", IngredientServiceImpl.class);
        orderService = context.getBean("orderServiceImpl", OrderServiceImpl.class);

        purchaseService.deleteAllRecord();
        cocktailService.deleteAllCocktail();
        ingredientService.deleteAllIngredient();

        ingredient = new Ingredient(8L, "lemon", 3, 1000);
        cocktail = new Cocktail(8L, "midorishower", 5000, 17.5);
        purchase = new Purchase(254L, 10000 ,LocalDateTime.now().withNano(0), PurchaseMethod.CASH);

        cocktailPurchase = new CocktailPurchase(1L, purchase, cocktail);
        cocktailIngredient = new CocktailIngredient(1L, ingredient, cocktail);
    }

    @Test // ????????? ??????
    public void addCocktail(){
        cocktailService.addCocktail(cocktail);
    }

    @Test // ?????? ??? ?????? ???????????? ?????????????????? ??????
    public void purchase() {
        Map<Long, Integer> money = new HashMap<>();
        cocktailService.addCocktail(cocktail);
        money.put(cocktailService.searchByName(cocktail.getName()).getId() + 1L, 2);
        orderService.purchaseCocktail(money, purchase);
        cocktailPurchase.getPurchase().setId(purchaseService.searchByMethod(PurchaseMethod.CASH).get(0).getId());
        ingredientList.addAll(orderService.searchIngredient(cocktail));
        for (Ingredient ingredient : ingredientList) {
            ingredient.setNumber(ingredient.getNumber() - 1);
            ingredientService.modifyIngredient(ingredient.getId(), ingredient);
        }
        
        //????????? ????????? ?????? ??????
        List<Cocktail> ct = orderService.searchWhich(purchase);
        for (Cocktail cocktail : ct) {
            System.out.println("cocktail = " + cocktail.getName());
        }
    }

    @Test // ???????????? ?????????
    public void searchByTime(){
        LocalDateTime time = LocalDateTime.of(2022,2,21,7,0);
        List<Purchase> purchaseList = purchaseService.searchByTime(time);
        for (Purchase purchase : purchaseList) {
            System.out.println("???????????? : " + purchase.getTime());
        }
    }

    @Test // ???????????? ??????
    public void searchByDate(){
        LocalDate date = LocalDate.of(2022,2,23);
        List<Purchase> purchaseList = purchaseService.searchByDate(date);
        for (Purchase purchase : purchaseList) {
            System.out.println("?????? ?????? : " + purchase.getAmount());
        }
    }

    @Test // ???????????? ??????
    public void searchByMonth(){
        LocalDate month = LocalDate.of(2022,2,1);
        List<Purchase> purchaseList = purchaseService.searchByMonth(month);
        for (Purchase purchase : purchaseList) {
            System.out.println("?????? ?????? : " + purchase.getMethod());
        }
    }

    @Test // ????????? ?????? ??????
    public void ModifyCocktail(){
        cocktailService.modifyCocktail(44L, cocktail);
    }

    @Test // ????????? ????????? ?????? ?????? ??????
    public void deleteOneCocktail(){
        cocktailService.deleteAllByName(cocktail.getName());
    }

    @Test // ????????? ?????? ??????
    public void searchCocktailById(){
        cocktailService.searchById(44L).ifPresent(m ->{
            System.out.println("??????.toString() : " + m.toString());
        });
    }

    @Test // ????????? ?????? ??????
    public void searchAllCocktail(){
        List<Cocktail> cocktailList = cocktailService.searchAll();
        for (Cocktail cocktail : cocktailList) {
            System.out.println("????????? ?????? : " + cocktail.getPrice());
        }
    }

    @Test // ?????? ??????
    public void addIngredient(){
        ingredientService.addIngredient(ingredient);
    }

    @Test // ?????? ??????
    public void modifyIngredient(){
        ingredientService.modifyIngredient(43L, ingredient);
    }

    @Test // ?????? ?????? ??????
    public void searchIngredient(){
        Optional<Ingredient> ingredient = ingredientService.searchById(43L);
        ingredient.ifPresent(System.out::println);
    }

    @Test // ?????? ?????? ??????
    public void searchAllIngredient(){
        List<Ingredient> ingredientList = ingredientService.searchAll();
        for (Ingredient ingredient : ingredientList) {
            System.out.println("????????? : " + ingredient.getName());
        }
    }

    @Test // ?????? ?????? ??? ??????
    public void deleteAllIngredient(){
        ingredientService.deleteAllIngredient();
    }

    @Test // ?????? ?????? ?????? ??? ??????
    public void deletePurchaseRecord(){
        purchaseService.deleteAllRecord();
    }

    @Test // ????????? ????????? ?????? ??????
    public void deleteAllCocktail(){
        cocktailService.deleteAllCocktail();
    }

    @Test // ????????? ??????
    public void statisticsByDay(){
        Map<Integer, Integer> data = purchaseService.statByDay();
        for(int i = 0; i < data.size(); i++){
            System.out.println(i + " : " + data.get(i));
        }
    }

    @Test // ????????? ??????
    public void statisticsByTime(){
        Map<Integer, Integer> data = purchaseService.statByTime();
        for(int i = 0; i < data.size(); i++){
            System.out.println(i + " : " + data.get(i));
        }
    }

    @Test // ?????? ??????
    public void statisticsByMonth(){
        Map<Integer, Integer> data = purchaseService.statByMonth();
        for(int i = 0; i < data.size(); i++){
            System.out.println(i + " : " + data.get(i));
        }
    }

    @Test // ?????? ?????? ??????
    public void howManySaleByMonth(){
        Map<Integer, Integer> data = purchaseService.howManyPurchase();
        for (Integer i : data.keySet()) {
            System.out.println(i + " : " + data.get(i));
        }
    }

    @Test // ??????????????? ?????????
    public void gainByWeek(){
        Map<Integer, Integer> data = orderService.gainByWeek();
        for (Integer i : data.keySet()) {
            System.out.println(i + " : " + data.get(i));
        }
    }
}
