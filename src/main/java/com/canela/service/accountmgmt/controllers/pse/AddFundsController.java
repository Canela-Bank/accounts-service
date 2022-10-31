package com.canela.service.accountmgmt.controllers.pse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.PatternProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/accounts")
@Tag(name = "Account", description = "Account REST API")
public class AddFundsController {
    @Operation(summary = "Add funds via PSE", description = "Add funds to the selected account from another bank via PSE", tags = {"Account"})
    @PostMapping(value = "add-funds/pse/{accountId}")
    @CrossOrigin("*")
    public ResponseEntity<String> addFunds(@PathVariable(value = "accountId")
                                           @Parameter(name = "Amount id", description = "Number of the account that will be updated", example = "33023227") String id,
                                           @RequestBody PseRequest req) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
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
