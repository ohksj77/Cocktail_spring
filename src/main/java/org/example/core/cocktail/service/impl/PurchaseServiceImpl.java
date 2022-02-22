package org.example.core.cocktail.service.impl;

import org.example.core.cocktail.domain.Purchase;
import org.example.core.cocktail.repository.PurchaseRepository;
import org.example.core.cocktail.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;

    @Autowired
    public PurchaseServiceImpl(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    @Override
    public void purchaseCocktail(Purchase purchase) {
        purchaseRepository.save(purchase);
    }

    @Override
    public List<Purchase> searchAll() {
        return purchaseRepository.findAll();
    }
}
