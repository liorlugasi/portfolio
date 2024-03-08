package com.customer.facingapi.services.implementations;

import com.customer.facingapi.models.Purchase;
import com.customer.facingapi.services.interfaces.IFacingApiService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.io.*;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

@Service
public class FacingApiService implements IFacingApiService {
    @Autowired
    private KafkaTemplate<String, Purchase> kafkaTemplate;

    private final static String TOPIC_NAME = "purchases";

    //Handle a “buy” request and publish the data object to Kafka.
    @Override
    public void sendMessage(Purchase message)
    {
        kafkaTemplate.send(TOPIC_NAME,message);
    }



    //Handle a “getAllUserBuys” and send a GET request to Customer Management service and present the response
    @Override
    public List<Purchase> getUserPurchases(String userId) {
        List<Purchase> purchases = null;
        try {
            StringBuilder sb=new StringBuilder();
            getManagerUserPurchases(userId,sb);
            ObjectMapper mapper = new ObjectMapper();
            try {
                purchases = mapper.readValue(sb.toString(), new TypeReference<List<Purchase>>(){});
            } catch (JsonProcessingException jpe) {
                jpe.printStackTrace();
            }

        } catch (ProtocolException pe) {
            pe.printStackTrace();
        } catch (MalformedURLException mue) {
            mue.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return purchases;
    }

    @Override
    public void getManagerUserPurchases(String userId, StringBuilder sb) throws IOException {
        URL url = new URL("http://localhost:8081/manager/getuserpurchase/" + userId);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", "application/json; charset=utf8");
        conn.setRequestProperty("Accept", "application/json");
        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + conn.getResponseCode());
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line=br.readLine())!=null){
            sb.append(line);
        }
        conn.disconnect();
        br.close();
    }
}
