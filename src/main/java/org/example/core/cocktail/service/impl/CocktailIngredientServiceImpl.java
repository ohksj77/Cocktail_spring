package org.example.core.cocktail.service.impl;

import org.example.core.cocktail.domain.Cocktail;
import org.example.core.cocktail.domain.CocktailIngredient;
import org.example.core.cocktail.domain.Ingredient;
import org.example.core.cocktail.repository.CocktailIngredientRepository;
import org.example.core.cocktail.service.CocktailIngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CocktailIngredientServiceImpl implements CocktailIngredientService {

    private final CocktailIngredientRepository cocktailingredientRepository;

    @Autowired
    public CocktailIngredientServiceImpl(CocktailIngredientRepository cocktailingredientRepository) {
        this.cocktailingredientRepository = cocktailingredientRepository;
    }

    @Override
    public void modifyCoIn(Long id, Cocktail cocktail, Ingredient ingredient) {
        cocktailingredientRepository.updateCoIn(id, cocktail, ingredient);
    }

    @Override
    public void save(Cocktail cocktail, Ingredient ingredient) {
        cocktailingredientRepository.saveOne(cocktail, ingredient);
    }
}
