package com.customer.manager.services.interfaces;

import com.customer.manager.models.Purchase;

import java.util.List;

public interface IManagerService {
    void saveInRedis(String message);

    List<Purchase> getUserPurchases(String userId);
}
