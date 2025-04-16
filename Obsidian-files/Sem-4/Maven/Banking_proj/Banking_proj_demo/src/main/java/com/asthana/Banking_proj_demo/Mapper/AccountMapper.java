package com.asthana.Banking_proj_demo.Mapper;

import com.asthana.Banking_proj_demo.Entity.AccountEntity;
import com.asthana.Banking_proj_demo.dto.Accountdto;

public class AccountMapper {

    public static Accountdto  mapToAccountdto(AccountEntity accountEntity){

        Accountdto accountdto = new Accountdto(
            accountEntity.getId(),
            accountEntity.getAccountHolderName(),
            accountEntity.getBalance()

        );

        return accountdto;
    }

    public static AccountEntity mapToAccountEntity(Accountdto accountdto)
    {

        AccountEntity accountEntity = new AccountEntity(
            accountdto.getId(),
            accountdto.getAccountHolderName(),
            accountdto.getBalance()
        );

        return accountEntity ;
    }
    
}
