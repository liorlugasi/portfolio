package com.customer.manager.controllers;

import com.customer.manager.models.Purchase;
import com.customer.manager.services.interfaces.IManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager")
public class managerController {

    @Autowired
    IManagerService managerService;

    //Handle a “getAllUserBuys” and send a GET request to Customer Management service and present the response
    @GetMapping(value = "/getuserpurchase/{userId}",consumes = "application/json",produces = "application/json")
    public List<Purchase> getUserPurchase(@PathVariable String userId) {
        return managerService.getUserPurchases(userId);
    }

}
