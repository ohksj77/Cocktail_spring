package org.example.core.cocktail.service;

import org.example.core.cocktail.domain.Ingredient;
import java.util.List;
import java.util.Optional;

public interface IngredientService {
    void addIngredient(Ingredient ingredient);
    void modifyIngredient(Long id, Ingredient ingredient);
    void deleteAllIngredient();
    void deleteById(Long id);
    void deleteByName(String name);
    Optional<Ingredient> searchById(Long id);
    List<Ingredient> searchAll();
}
