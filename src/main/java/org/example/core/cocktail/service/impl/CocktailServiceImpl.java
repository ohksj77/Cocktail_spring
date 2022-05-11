package org.example.core.cocktail.service.impl;

import org.example.core.cocktail.domain.Cocktail;
import org.example.core.cocktail.repository.CocktailRepository;
import org.example.core.cocktail.service.CocktailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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

    @Override
    public void modifyCocktail(Long id, Cocktail cocktail) {
        cocktailRepository.updateCocktail(id, cocktail.getPrice(), cocktail.getProof());
    }

    @Override
    public void deleteCocktail(Cocktail cocktail) {
        cocktailRepository.deleteById(cocktail.getId());
    }

    @Override
    public void deleteAllByName(String name) {
        cocktailRepository.deleteByName(name);
    }


    @Override
    public Optional<Cocktail> searchById(Long id) {
        return cocktailRepository.findById(id);
    }

    @Override
    public Cocktail searchByName(String name) {
        return cocktailRepository.findByName(name);
    }

    @Override
    public void deleteAllCocktail() {
        cocktailRepository.deleteAll();
    }

    @Override
    public List<Cocktail> searchAll() {
        return cocktailRepository.findAll();
    }

    @Override
    public Cocktail searchId(String name) {
        return cocktailRepository.findByName(name);
    }
}
