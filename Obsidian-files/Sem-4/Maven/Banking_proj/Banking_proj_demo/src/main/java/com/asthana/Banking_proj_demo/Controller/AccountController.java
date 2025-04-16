package com.asthana.Banking_proj_demo.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asthana.Banking_proj_demo.Services.AccountServices;
import com.asthana.Banking_proj_demo.dto.Accountdto;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    
    
    
    @Autowired
    AccountServices accountServices ;
    
    
    public AccountController(AccountServices accountServices) {
        this.accountServices = accountServices;
    }
    

@PostMapping
public ResponseEntity<Accountdto> createAccount(@RequestBody Accountdto accountdto) {
    return new ResponseEntity<>(accountServices.createAccount(accountdto),HttpStatus.CREATED);   
}


@GetMapping("/{id}")
public ResponseEntity<Accountdto> getAccountById(@PathVariable Long id) {
    Accountdto accountdto = accountServices.getAccountById(id);
    return ResponseEntity.ok(accountdto);
}


@PutMapping("/{id}/deposit")
public ResponseEntity<Accountdto> deposit(@PathVariable Long id , @RequestBody Map<String , Double> deposit) {

    Double Amount =deposit.get("amount");

Accountdto accountdto = accountServices.deposit(id, Amount);

    return ResponseEntity.ok(accountdto);
}



@PutMapping("/{id}/withdraw")
public ResponseEntity<Accountdto> withdraw(@PathVariable Long id , @RequestBody Map<String , Double> withdraw) {

    double Amount = withdraw.get("amount");

    Accountdto accountdto = accountServices.withdraw(id, Amount);

    return ResponseEntity.ok(accountdto);
}


@GetMapping("/getAllAccounts")
public ResponseEntity<List<Accountdto>> getAllAccounts() {

    List<Accountdto> accountdtos = accountServices.getAllAccounts();

    return ResponseEntity.ok(accountdtos);
}


@DeleteMapping("/{id}")
public ResponseEntity<String> deleteAccount(@PathVariable Long id){
    
    accountServices.deleteAccount(id);

    return ResponseEntity.ok("Account Succesfully Deleted");

}

    
}
