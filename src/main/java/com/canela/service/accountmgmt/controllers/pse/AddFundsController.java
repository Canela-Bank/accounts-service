package com.canela.service.accountmgmt.controllers.pse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.PatternProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

@RestController
@RequestMapping(value = "/api/accounts")
@Tag(name = "Account", description = "Account REST API")
public class AddFundsController {
    @Operation(summary = "Add funds via PSE", description = "Add funds to the selected account from another bank via PSE", tags = {"Account"})
    @PutMapping(value = "add-funds/pse/{accountId}")
    @CrossOrigin("*")
    public ResponseEntity<String> addFunds(@PathVariable(value = "accountId")
                                           @Parameter(name = "Amount id", description = "Number of the account that will be updated", example = "33023227") String id,
                                           @RequestBody PseRequest req) {

        URL url = null;
        String response = null;
        try {
            url = new URL("http://localhost:9010/pse/approval/send-response"); //TODO: Change when providers integrator is ready
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            int codeResponse = conn.getResponseCode();
            if(codeResponse == HttpURLConnection.HTTP_OK){
                //TODO: Calculate total fund and update in database
            }
        } catch (MalformedURLException | ProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("El body es " + response);
    }

    static class PseRequest {
        @Schema(name = "Amount", description = "Amount to add", example = "100000")
        private Long amount;

        public Long getAmount() {
            return amount;
        }

        public void setAmount(Long amount) {
            this.amount = amount;
        }
    }
}
