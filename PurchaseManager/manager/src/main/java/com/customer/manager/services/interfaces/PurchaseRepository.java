package com.customer.manager.services.interfaces;

import com.customer.manager.models.Purchase;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

//Spring Data Repository
public interface PurchaseRepository extends CrudRepository<Purchase,String>{
    List<Purchase> findByUserId(String userId);
}
