package org.example.core.cocktail.service.impl;

import org.example.core.cocktail.domain.*;
import org.example.core.cocktail.repository.PurchaseRepository;
import org.example.core.cocktail.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;

    @Autowired
    public PurchaseServiceImpl(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    @Override
    public List<Purchase> searchAll() {
        return purchaseRepository.findAll();
    }

    @Override
    public List<Purchase> searchByTime(LocalDateTime time) {
        return purchaseRepository.searchByDate(time, time.plusHours(1));
    }

    @Override
    public List<Purchase> searchByDate(LocalDate date) {
        return purchaseRepository.searchByDate(date.atStartOfDay(), date.plusDays(1).atStartOfDay());
    }

    @Override
    public List<Purchase> searchByMonth(LocalDate date) {
        return purchaseRepository.searchByDate(date.atStartOfDay(), date.plusMonths(1).atStartOfDay());
    }

    @Override
    public List<Purchase> searchByMethod(PurchaseMethod method) {
        return purchaseRepository.findByMethod(method);
    }

    @Override
    public void deleteAllRecord() {
        purchaseRepository.deleteAll();
    }

    @Override
    public Map<Integer, Integer> statByDay() {
        Map<Integer, List<Purchase>> stat = searchAll().stream().collect((Collectors.groupingBy(p -> p.getTime().getDayOfYear())));
        return ascSale(stat);
    }

    @Override
    public Map<Integer, Integer> statByTime() {
        Map<Integer, List<Purchase>> stat = searchAll().stream().collect((Collectors.groupingBy(p -> p.getTime().getHour())));
        return ascSale(stat);
    }

    @Override
    public Map<Integer, Integer> statByMonth() {
        Map<Integer, List<Purchase>> stat = searchAll().stream().collect((Collectors.groupingBy(p -> p.getTime().getMonthValue())));
        return ascSale(stat);
    }

    @Override
    public Map<Integer, Integer> howManyPurchase() {
        Map<Integer, List<Purchase>> stat = searchAll().stream().collect((Collectors.groupingBy(p -> p.getTime().getMonthValue())));
        Map<Integer, Integer> result = new HashMap<>();
        for (Integer i : stat.keySet()) {
            result.put(i, stat.get(i).size());
        }
        return result;
    }

    public Map<Integer, Integer> ascSale(Map<Integer, List<Purchase>> stat) {
        Map<Integer, Integer> result = new HashMap();
        for (Integer i : stat.keySet()) {
            result.put(i, stat.get(i).stream().map(Purchase::getAmount).reduce(Integer::sum).orElse(0));
        }
        return result;
    }
}
