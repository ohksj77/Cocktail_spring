package org.example.core.cocktail.service.impl;

import org.example.core.cocktail.domain.Ingredient;
import org.example.core.cocktail.repository.IngredientRepository;
import org.example.core.cocktail.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public void addIngredient(Ingredient ingredient) {
        ingredientRepository.save(ingredient);
    }

    @Override
    public void modifyIngredient(Long id, Ingredient ingredient) {
        ingredientRepository.updateIngredient(id, ingredient.getPrice(), ingredient.getNumber(), ingredient.getName());
    }

    @Override
    public void deleteAllIngredient() {
        ingredientRepository.deleteAll();
    }

    @Override
    public void deleteById(Long id) {
        ingredientRepository.deleteById(id);
    }

    @Override
    public void deleteByName(String name) {
        ingredientRepository.deleteAllByName(name);
    }


    @Override
    public Optional<Ingredient> searchById(Long id) {
        return ingredientRepository.findById(id);
    }

    @Override
    public List<Ingredient> searchAll() {
        return ingredientRepository.findAll();
    }


}
