package com.devops.coe.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationUserRepository  extends JpaRepository<ApplicationUsers, Long>{
	ApplicationUsers findByUsername(String username);

}
