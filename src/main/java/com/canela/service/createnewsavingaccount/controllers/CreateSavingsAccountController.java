package com.canela.service.createnewsavingaccount.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

@RestController
@RequestMapping(value = "/api/accounts")
public class CreateSavingsAccountController {
    @PostMapping(value = "accounts/{accountId}/create-savings" )
    public ResponseEntity<String> createNewAccount(@PathVariable String initialAmount) {
        try{
            URL url = new URL("http://localhost:9010/accounts/new-savings");
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
