package com.taxis.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<com.taxis.account.Account, Long> {
	com.taxis.account.Account findOneByEmail(String email);
}