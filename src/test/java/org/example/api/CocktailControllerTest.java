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
import org.junit.jupiter.api.BeforeAll;
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

    @Test // 칵테일 추가
    public void addCocktail(){
        cocktailService.addCocktail(cocktail);
    }

    @Test // 결제 및 어떤 칵테일이 판매되었는지 조회
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
        
        //판매된 칵테일 종류 조회
        List<Cocktail> ct = orderService.searchWhich(purchase);
        for (Cocktail cocktail : ct) {
            System.out.println("cocktail = " + cocktail.getName());
        }
    }

    @Test // 결제조회 시간별
    public void searchByTime(){
        LocalDateTime time = LocalDateTime.of(2022,2,21,7,0);
        List<Purchase> purchaseList = purchaseService.searchByTime(time);
        for (Purchase purchase : purchaseList) {
            System.out.println("구매일자 : " + purchase.getTime());
        }
    }

    @Test // 결제조회 일별
    public void searchByDate(){
        LocalDate date = LocalDate.of(2022,2,23);
        List<Purchase> purchaseList = purchaseService.searchByDate(date);
        for (Purchase purchase : purchaseList) {
            System.out.println("구매 금액 : " + purchase.getAmount());
        }
    }

    @Test // 결제조회 월별
    public void searchByMonth(){
        LocalDate month = LocalDate.of(2022,2,1);
        List<Purchase> purchaseList = purchaseService.searchByMonth(month);
        for (Purchase purchase : purchaseList) {
            System.out.println("구매 수단 : " + purchase.getMethod());
        }
    }

    @Test // 칵테일 정보 수정
    public void ModifyCocktail(){
        cocktailService.modifyCocktail(44L, cocktail);
    }

    @Test // 칵테일 하나와 해당 재료 삭제
    public void deleteOneCocktail(){
        cocktailService.deleteAllByName(cocktail.getName());
    }

    @Test // 칵테일 하나 조회
    public void searchCocktailById(){
        cocktailService.searchById(44L).ifPresent(m ->{
            System.out.println("객체.toString() : " + m.toString());
        });
    }

    @Test // 칵테일 전체 조회
    public void searchAllCocktail(){
        List<Cocktail> cocktailList = cocktailService.searchAll();
        for (Cocktail cocktail : cocktailList) {
            System.out.println("칵테일 가격 : " + cocktail.getPrice());
        }
    }

    @Test // 재료 추가
    public void addIngredient(){
        ingredientService.addIngredient(ingredient);
    }

    @Test // 재료 수정
    public void modifyIngredient(){
        ingredientService.modifyIngredient(43L, ingredient);
    }

    @Test // 재료 하나 조회
    public void searchIngredient(){
        Optional<Ingredient> ingredient = ingredientService.searchById(43L);
        ingredient.ifPresent(System.out::println);
    }

    @Test // 재료 전체 조회
    public void searchAllIngredient(){
        List<Ingredient> ingredientList = ingredientService.searchAll();
        for (Ingredient ingredient : ingredientList) {
            System.out.println("재료명 : " + ingredient.getName());
        }
    }

    @Test // 재료 모두 다 삭제
    public void deleteAllIngredient(){
        ingredientService.deleteAllIngredient();
    }

    @Test // 결제 내역 모두 다 삭제
    public void deletePurchaseRecord(){
        purchaseService.deleteAllRecord();
    }

    @Test // 재료와 칵테일 전체 삭제
    public void deleteAllCocktail(){
        cocktailService.deleteAllCocktail();
    }

    @Test // 요일별 통계
    public void statisticsByDay(){
        Map<Integer, Integer> data = purchaseService.statByDay();
        for(int i = 0; i < data.size(); i++){
            System.out.println(i + " : " + data.get(i));
        }
    }

    @Test // 시간별 통계
    public void statisticsByTime(){
        Map<Integer, Integer> data = purchaseService.statByTime();
        for(int i = 0; i < data.size(); i++){
            System.out.println(i + " : " + data.get(i));
        }
    }

    @Test // 월별 통계
    public void statisticsByMonth(){
        Map<Integer, Integer> data = purchaseService.statByMonth();
        for(int i = 0; i < data.size(); i++){
            System.out.println(i + " : " + data.get(i));
        }
    }

    @Test // 월별 판매 개수
    public void howManySaleByMonth(){
        Map<Integer, Integer> data = purchaseService.howManyPurchase();
        for (Integer i : data.keySet()) {
            System.out.println(i + " : " + data.get(i));
        }
    }

    @Test // 일주일단위 순이익
    public void gainByWeek(){
        Map<Integer, Integer> data = orderService.gainByWeek();
        for (Integer i : data.keySet()) {
            System.out.println(i + " : " + data.get(i));
        }
    }
}
