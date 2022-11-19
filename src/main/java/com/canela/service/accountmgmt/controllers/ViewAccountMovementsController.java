package com.canela.service.accountmgmt.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.canela.service.accountmgmt.entities.GraphQLLibrary;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@RequestMapping(value = "/api/accounts")
public class ViewAccountMovementsController {

	 @GetMapping(value = "/viewMovements" )
	 @CrossOrigin("*")
	    public ResponseEntity<String> viewMovements(@RequestBody movementsRequest request) {	 
		 
		 
		 try {
			 String json = GraphQLLibrary.queryGraphQLService("http://localhost:3001/graphql", "getMovementsByOriginAccount", "query{getMovementsByOriginAccount(account_id:\""+request.accountId+"\"){\n"
				 		+ "  id\n"
				 		+ "  origin_account\n"
				 		+ "  destination_account\n"
				 		+ "  amount\n"
				 		+ "  movement_date\n"
				 		+ "	}  \n"
				 		+ "}");	 	 
			 
			 Object[] jsonResponse = new ObjectMapper().readValue(json, Object[].class);  
			 
			 if(jsonResponse.length != 0 ) {
				 return ResponseEntity.status(HttpStatus.ACCEPTED).body(json.toString()); 
			 }
			 else {
				 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No existen movimientos recientes en la cuenta."); 
			 }
			 
			 
		} catch (Exception e) {
			throw new RuntimeException(e); 
		}
			
			 	
		
		 

}
	 static class movementsRequest {

	     private String accountId;


	     public String getAccountId() {
	            return accountId;
	        }

	     public void setAccountId(String accountId) {
	            this.accountId = accountId;
	        }
	    }
}


