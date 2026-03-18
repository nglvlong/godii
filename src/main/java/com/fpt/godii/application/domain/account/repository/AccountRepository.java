package com.fpt.godii.application.domain.account.repository;

import com.fpt.godii.application.domain.account.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>, AccountCriteriaRepository {

    Optional<Account> findByEmail(String email);

}
