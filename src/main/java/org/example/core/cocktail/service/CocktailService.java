package org.example.core.cocktail.service;

import org.example.core.cocktail.domain.Cocktail;

import java.util.Optional;
import java.util.List;

public interface CocktailService {
    void addCocktail(Cocktail cocktail);
    void modifyCocktail(Long id, Cocktail cocktail);
    void deleteCocktail(Cocktail cocktail);
    void deleteAllByName(String name);
    Optional searchById(Long id);
    Cocktail searchByName(String name);
    void deleteAllCocktail();
    List<Cocktail> searchAll();
}
