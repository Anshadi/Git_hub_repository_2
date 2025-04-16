package com.asthana.Banking_proj_demo.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.asthana.Banking_proj_demo.Entity.AccountEntity;

public  interface AccountRepository extends JpaRepository<AccountEntity,Long>{


    
}
