package com.customer.manager.services.implementations;

import com.customer.manager.models.Purchase;
import com.customer.manager.services.interfaces.IManagerService;
import com.customer.manager.services.interfaces.PurchaseRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerService implements IManagerService {

    @Autowired
    PurchaseRepository purchaseRepository;

    //Consume messages from Kafka
    @KafkaListener(topics = "purchases", groupId = "group_id",containerFactory = "purchaseListener")
    public void listen(String message) throws JsonProcessingException {
        System.out.println("Received '" + message +"' from the purchases topic." );
        saveInRedis(message);

    }

    //Write data into the DB
    @Override
    public void saveInRedis(String message) {
        ObjectMapper mapper = new ObjectMapper();
        Purchase purchase = null;
        try {
            purchase = mapper.readValue(message, Purchase.class);
        } catch (JsonProcessingException jpe) {
            jpe.printStackTrace();
        }
        purchaseRepository.save(purchase);
    }

    //Read data from the DB
    @Override
    public List<Purchase> getUserPurchases(String userId) {
        return purchaseRepository.findByUserId(userId);
    }
}
