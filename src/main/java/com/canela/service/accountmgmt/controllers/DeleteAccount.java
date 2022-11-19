package com.canela.service.accountmgmt.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

@RestController
@RequestMapping("/api/accounts")
public class DeleteAccount {

    @DeleteMapping("/delete/{account}")
    public ResponseEntity<String> delete(@PathVariable String account){
        URL url = null;
        try {
            url = new URL("http://localhost:3002/graphql?query=mutation%7B%0A%20%20deleteAccount(id%"+account+"A%20%222%22)%7B%0A%20%20%20%20message%2C%0A%20%20%20%20data%20%7B%0A%20%20%20%20%20%20id%2C%0A%20%20%20%20%20%20balance%2C%0A%20%20%20%20%20%20user_id%2C%0A%20%20%20%20%20%20user_document_type%0A%20%20%20%20%7D%0A%20%20%7D%0A%7D%0A");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            int response = conn.getResponseCode();
            if(response == HttpURLConnection.HTTP_OK){
                return ResponseEntity.status(HttpStatus.OK).body("Eliminado");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(404).body("No se pudo eliminar");
    }
}
