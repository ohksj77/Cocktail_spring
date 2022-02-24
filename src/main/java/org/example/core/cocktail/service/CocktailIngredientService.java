package org.example.core.cocktail.service;

import org.example.core.cocktail.domain.Cocktail;
import org.example.core.cocktail.domain.CocktailIngredient;
import org.example.core.cocktail.domain.Ingredient;
import java.util.List;

public interface CocktailIngredientService {
    void modifyCoIn(Long id, Cocktail cocktail, Ingredient ingredient);
    void save(Cocktail cocktail, Ingredient ingredient);
}
