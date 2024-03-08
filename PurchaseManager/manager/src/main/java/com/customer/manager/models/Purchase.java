package com.customer.manager.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.index.Indexed;
import java.util.Date;

@Data
@RedisHash("purchase")
public class Purchase {
    @Id
    @JsonProperty("purchaseId")
    private String purchaseId;
    @JsonProperty("userId")
    @Indexed
    private String userId;
    @JsonProperty("userName")
    @Indexed
    private String userName;
    @JsonProperty("price")
    @Indexed
    private String price;
    @JsonProperty("timeStamp")
    @Indexed
    private Date timeStamp;

}
