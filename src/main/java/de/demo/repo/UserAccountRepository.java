package de.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import de.demo.entity.user.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
	
	UserAccount findByUsername(String username);
	
}
	