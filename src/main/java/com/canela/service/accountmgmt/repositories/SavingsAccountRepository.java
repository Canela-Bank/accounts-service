package com.canela.service.accountmgmt.repositories;

import com.canela.service.accountmgmt.Entities.SavingsAccountEntity;
import org.springframework.data.repository.CrudRepository;

public interface SavingAccountRepository extends CrudRepository<SavingsAccountEntity, String> {
}
