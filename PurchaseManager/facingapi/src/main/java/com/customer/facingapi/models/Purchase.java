package com.customer.facingapi.models;

import lombok.Data;

import java.util.Date;

@Data
public class Purchase{
    private String purchaseId;
    private String userId;
    private String userName;
    private String price;
    private Date timeStamp;

}
