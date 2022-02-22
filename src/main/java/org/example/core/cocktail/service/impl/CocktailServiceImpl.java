package org.example.core.cocktail.service.impl;

import lombok.NoArgsConstructor;
import org.example.core.cocktail.domain.Cocktail;
import org.example.core.cocktail.repository.CocktailRepository;
import org.example.core.cocktail.service.CocktailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CocktailServiceImpl implements CocktailService {

    private final CocktailRepository cocktailRepository;

    @Autowired
    public CocktailServiceImpl(CocktailRepository cocktailRepository) {
        this.cocktailRepository = cocktailRepository;
    }

    @Override
    public void addCocktail(Cocktail cocktail) {
        cocktailRepository.save(cocktail);
    }
}
