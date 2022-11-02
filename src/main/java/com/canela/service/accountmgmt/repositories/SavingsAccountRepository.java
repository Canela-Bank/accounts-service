package com.canela.service.accountmgmt.repositories;

import com.canela.service.accountmgmt.Entities.SavingsAccountEntity;
import org.springframework.data.repository.CrudRepository;

public interface SavingsAccountRepository extends CrudRepository<SavingsAccountEntity, String> {
}
