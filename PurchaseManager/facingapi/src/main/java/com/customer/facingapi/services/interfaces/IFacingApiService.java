package com.customer.facingapi.services.interfaces;

import com.customer.facingapi.models.Purchase;

import java.io.IOException;
import java.util.List;

public interface IFacingApiService {
    void sendMessage(Purchase message);

    List<Purchase> getUserPurchases(String userId);

    void getManagerUserPurchases(String userId, StringBuilder sb) throws IOException;
}
