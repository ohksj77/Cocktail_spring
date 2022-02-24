package org.example.api;

import org.example.core.cocktail.domain.*;
import org.example.core.cocktail.service.CocktailIngredientService;
import org.example.core.cocktail.service.impl.CocktailIngredientServiceImpl;
import org.example.core.cocktail.service.CocktailService;
import org.example.core.cocktail.service.IngredientService;
import org.example.core.cocktail.service.PurchaseService;
import org.example.core.cocktail.service.impl.CocktailServiceImpl;
import org.example.core.cocktail.service.impl.IngredientServiceImpl;
import org.example.core.cocktail.service.impl.PurchaseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class CocktailControllerTest {
    private ApplicationContext context;
    private PurchaseService purchaseService;
    private CocktailService cocktailService;
    private IngredientService ingredientService;
    private CocktailIngredientService cocktailIngredientService;
    private Purchase purchase;
    private Cocktail cocktail;
    private Ingredient ingredient;
    private CocktailIngredient cocktailIngredient;
    private List<CocktailIngredient> cocktailList;

    @BeforeEach
    public void setUp() {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        purchaseService = context.getBean("purchaseServiceImpl", PurchaseServiceImpl.class);
        cocktailService = context.getBean("cocktailServiceImpl", CocktailServiceImpl.class);
        ingredientService = context.getBean("ingredientServiceImpl", IngredientServiceImpl.class);
        cocktailIngredientService = context.getBean("cocktailIngredientServiceImpl", CocktailIngredientServiceImpl.class);
        ingredient = new Ingredient();
        ingredient.setId(8L);
        ingredient.setNumber(3);
        cocktail = new Cocktail();
        cocktail.setPrice(5000);
        cocktail.setCocktail("Yummy10w01000121"); // 이름 바꾸지 않으면 테스트 통과 안된다.....
        cocktailIngredient = new CocktailIngredient(8L, ingredient, cocktail);
        cocktailList = new ArrayList<CocktailIngredient>();
        cocktailList.add(cocktailIngredient);
        cocktail.setIngredient(cocktailList);
        purchase = new Purchase(2L, 10000 ,LocalDateTime.now().withNano(0), PurchaseMethod.CASH, cocktail);
    }

    @Test // 칵테일 추가
    public void addCocktail(){
        cocktailService.addCocktail(cocktail);
    }

    @Test // 결제 및 어떤 칵테일이 판매되었는지 조회
    public void purchase() {
        cocktailService.addCocktail(cocktail);
        cocktail.setId((cocktailService.searchByName(cocktail.getCocktail())).getId());
        purchaseService.purchaseCocktail(2, purchase);
        Cocktail cocktail1 = purchase.getCocktail();
        List<CocktailIngredient> ingredientList = cocktail1.getIngredient();
        for (CocktailIngredient ingredient : ingredientList) {
            ingredient.getIngredient().setNumber(ingredient.getIngredient().getNumber() - 1);
            ingredientService.modifyIngredient(ingredient.getId(), ingredient.getIngredient());
        }
        //판매된 칵테일 종류 조회
        Cocktail ct = purchaseService.searchWhich(purchase);
        System.out.println("Cocktail = " + ct.getCocktail());
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
        cocktailService.deleteAllByName(cocktail.getCocktail());
    }

    @Test // 재료와 칵테일 전체 삭제
    public void deleteAllCocktail(){
        cocktailService.deleteAllCocktail();
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
}
