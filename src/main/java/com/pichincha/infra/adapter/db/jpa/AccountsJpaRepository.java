package com.pichincha.infra.adapter.db.jpa;

import com.pichincha.infra.adapter.db.entites.AccountsDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AccountsJpaRepository extends CrudRepository<AccountsDto, Long> {

     AccountsDto findByAccountIdAndIsActive(Long accountId, Boolean isActive);

     @Query(value = "SELECT a FROM AccountsDto a WHERE a.accountType.accountTypeId  = ?1 "
             + "AND a.accountNumber = ?2")
     AccountsDto findByIdentificationTypeIdAndNumber(Long typeId, Long number);
}
