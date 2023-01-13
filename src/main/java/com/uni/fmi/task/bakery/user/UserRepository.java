package com.uni.fmi.task.bakery.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	@Query("SELECT user "
			+ "FROM User user "
			+ "WHERE user.username = :username "
			+ "AND user.password = :password ")
	User findUserByUsernameAndPassword
					(String username, String password);
	
	User findByUsername(String username);

}
