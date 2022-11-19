package com.canela.service.createnewsavingaccount.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/accounts")
public class CreateSavingsAccountController {
    @PostMapping(value = "/create-savings" )
    public ResponseEntity<String> createNewAccount(@RequestBody String initialAmount) throws JsonProcessingException {
        Map<String, Object> values = new ObjectMapper().readValue(initialAmount, HashMap.class);
        String InitialAmount = (String) values.get("InitialAmount");
        try{
            URL url = new URL("http://localhost:8080/graphql?query=mutation{createCreditQuery(InitalAmount)");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            int resp = conn.getResponseCode();
            if(resp== HttpURLConnection.HTTP_OK){
                return ResponseEntity.status(HttpStatus.OK).body("Se agrego una cuenta de ahorros");
            }
        }catch (MalformedURLException | ProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(404).body("No se pudo crear una cuenta de ahorros");
    }
}
