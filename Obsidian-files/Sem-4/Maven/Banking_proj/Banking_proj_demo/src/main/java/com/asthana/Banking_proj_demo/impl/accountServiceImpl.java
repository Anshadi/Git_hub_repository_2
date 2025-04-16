package com.asthana.Banking_proj_demo.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.asthana.Banking_proj_demo.Entity.AccountEntity;
import com.asthana.Banking_proj_demo.Mapper.AccountMapper;
import com.asthana.Banking_proj_demo.Repository.AccountRepository;
import com.asthana.Banking_proj_demo.Services.AccountServices;
import com.asthana.Banking_proj_demo.dto.Accountdto;

public class accountServiceImpl implements AccountServices {

@Autowired
    AccountRepository accountRepository;    

    
    public accountServiceImpl(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
}


    @Override
    public Accountdto createAccount(Accountdto accountdto) {
        AccountEntity accountEntity = AccountMapper.mapToAccountEntity(accountdto);
        accountRepository.save(accountEntity);
        return AccountMapper.mapToAccountdto(accountEntity);

    }


    @Override
    public Accountdto getAccountById(Long id) {
        AccountEntity accountEntity = accountRepository.findById(id).orElseThrow(()-> new RuntimeException ("Account Does Not Exist"));
        return AccountMapper.mapToAccountdto(accountEntity);
    }


    
    

@Override
    public Accountdto deposit(Long id, double amount) {
        AccountEntity accountEntity = accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account Does Not Exist"));
        double newAmount = accountEntity.getBalance() + amount ;
        accountEntity.setBalance(newAmount);

        AccountEntity savedAccountEntity = accountRepository.save(accountEntity);

        return AccountMapper.mapToAccountdto(savedAccountEntity);
    
    }


@Override
public Accountdto withdraw(Long id, double amount) {
    AccountEntity accountEntity = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account Does Not Exist"));

    if(accountEntity.getBalance() < amount)
    throw new RuntimeException("Insufficient Balance");


    double newAmount = accountEntity.getBalance() - amount;

    accountEntity.setBalance(newAmount);

    AccountEntity savedAccountEntity = accountRepository.save(accountEntity);

    return AccountMapper.mapToAccountdto(savedAccountEntity);
}


@Override
public List<Accountdto> getAllAccounts() {

    List<AccountEntity> accountEntities = accountRepository.findAll();

    List<Accountdto> accountdtos = accountEntities.stream().map((accountEntity) -> AccountMapper.mapToAccountdto(accountEntity))
    .collect(Collectors.toList());

    return accountdtos;

}


@Override
public void deleteAccount(Long id) {

    accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account Does not Exist"));
    
    accountRepository.deleteById(id);
}
}
