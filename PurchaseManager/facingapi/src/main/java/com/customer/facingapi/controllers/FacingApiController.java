package com.customer.facingapi.controllers;

import com.customer.facingapi.models.Purchase;
import com.customer.facingapi.services.interfaces.IFacingApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/facingapi")
@RestController
public class FacingApiController {

    @Autowired
    IFacingApiService facingApiService;

    //Handle a “buy” request and publish the data object to Kafka.
    @PostMapping(value = "/addPurchase",consumes = "application/json",produces = "application/json")
    public void addPurchase(@RequestBody Purchase purchase) {
        facingApiService.sendMessage(purchase);
    }

    //Handle a “getAllUserBuys” and send a GET request to Customer Management service and present the response
    @GetMapping(value = "/getallUserbuys/{userId}",consumes = "application/json",produces = "application/json")
    public List<Purchase> getAllUserBuys(@PathVariable String userId) {
        return facingApiService.getUserPurchases(userId);
    }


}
