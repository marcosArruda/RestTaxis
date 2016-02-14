package com.taxis.repositories;

import com.taxis.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("accountRepository")
public interface AccountRepository extends JpaRepository<Account, Long> {
	Account findOneByEmail(String email);
}