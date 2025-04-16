package com.asthana.Banking_proj_demo.Services;

import java.util.List;

import com.asthana.Banking_proj_demo.dto.Accountdto;

public interface AccountServices {

    Accountdto createAccount(Accountdto accountdto);

    Accountdto getAccountById(Long Id);

    Accountdto deposit(Long id , double amount);

    Accountdto withdraw(Long id , double amount);

    List<Accountdto> getAllAccounts();

    void deleteAccount(Long id);
    
}
