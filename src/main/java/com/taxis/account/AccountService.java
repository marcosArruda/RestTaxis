package com.taxis.account;

import java.util.Collections;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AccountService implements UserDetailsService {
	
	@Autowired
	private com.taxis.account.AccountRepository accountRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostConstruct	
	protected void initialize() {
		save(new com.taxis.account.Account("user", "demo", "ROLE_USER"));
		save(new com.taxis.account.Account("admin", "admin", "ROLE_ADMIN"));
	}

	@Transactional
	public com.taxis.account.Account save(com.taxis.account.Account account) {
		account.setPassword(passwordEncoder.encode(account.getPassword()));
		accountRepository.save(account);
		return account;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.taxis.account.Account account = accountRepository.findOneByEmail(username);
		if(account == null) {
			throw new UsernameNotFoundException("user not found");
		}
		return createUser(account);
	}
	
	public void signin(com.taxis.account.Account account) {
		SecurityContextHolder.getContext().setAuthentication(authenticate(account));
	}
	
	private Authentication authenticate(com.taxis.account.Account account) {
		return new UsernamePasswordAuthenticationToken(createUser(account), null, Collections.singleton(createAuthority(account)));		
	}
	
	private User createUser(com.taxis.account.Account account) {
		return new User(account.getEmail(), account.getPassword(), Collections.singleton(createAuthority(account)));
	}

	private GrantedAuthority createAuthority(com.taxis.account.Account account) {
		return new SimpleGrantedAuthority(account.getRole());
	}

}
